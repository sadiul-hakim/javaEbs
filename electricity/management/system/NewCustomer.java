package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener{
    private JPanel rightPanel;
    private JTextField nameField,addressField,stateField,cityField,emailField,phoneField;
    private JButton next,cancel;
    private JLabel lblmeterno;
    NewCustomer(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,700,500);
        setLayout(new BorderLayout());
        //right panel
        //panel
        rightPanel=new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(173,216,230));
        add(rightPanel,"Center");
        
        //label
        JLabel heading=new JLabel("New Customer");
        heading.setBounds(100,10,200,25);
        heading.setFont(new Font("Arial",Font.BOLD,18));
        rightPanel.add(heading);
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(100,80,200,25);
        lblname.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblname);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,120,200,25);
        lblmeter.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblmeter);
        
        lblmeterno=new JLabel("");
        Random rand=new Random();
        long longRand=rand.nextLong() % 100_00_00;
        lblmeterno.setText(""+Math.abs(longRand));
        lblmeterno.setBounds(240,120,200,25);
        lblmeterno.setFont(new Font("Arial",Font.BOLD,14));
        rightPanel.add(lblmeterno);
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(100,160,200,25);
        lbladdress.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lbladdress);
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(100,200,200,25);
        lblcity.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblcity);
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(100,240,200,25);
        lblstate.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblstate);
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(100,280,200,25);
        lblemail.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblemail);
        
        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(100,320,200,25);
        lblphone.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblphone);
        
        //text fields
        nameField=new JTextField();
        nameField.setBounds(240,80,200,20);
        rightPanel.add(nameField);
        
        addressField=new JTextField();
        addressField.setBounds(240,160,200,20);
        rightPanel.add(addressField);
        
        cityField=new JTextField();
        cityField.setBounds(240,200,200,20);
        rightPanel.add(cityField);
        
        stateField=new JTextField();
        stateField.setBounds(240,240,200,20);
        rightPanel.add(stateField);
        
        emailField=new JTextField();
        emailField.setBounds(240,280,200,20);
        rightPanel.add(emailField);
        
        phoneField=new JTextField();
        phoneField.setBounds(240,320,200,20);
        rightPanel.add(phoneField);
        
        //button
        next=new JButton("Next");
        next.setBounds(140,390,110,30);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        rightPanel.add(next);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(260,390,110,30);
        rightPanel.add(cancel);
        
        //left side
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=il.getImage().getScaledInstance(150,300,Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        JLabel image=new JLabel(il);
        add(image,"West");
        
        next.addActionListener(this);
        cancel.addActionListener(this);
        
        getContentPane().setBackground(Color.white);
        
    }
    public static void main(String[] args) {
        NewCustomer frame=new NewCustomer();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next){
            String name=nameField.getText();
            String meterno=lblmeterno.getText();
            String address=addressField.getText();
            String city=cityField.getText();
            String state=stateField.getText();
            String email=emailField.getText();
            String phone=phoneField.getText();
            
            String query1="""
                          insert into customer values('%s','%s','%s','%s','%s','%s','%s');
                          """.formatted(name,meterno,address,city,state,email,phone);
            String query2="""
                           insert into login values('%s','','%s','','');
                          """.formatted(meterno,name);
            
            try{
                Conn c=new Conn();
                c.command.executeUpdate(query1);
                c.command.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                
                MeterInfo info=new MeterInfo(meterno);
                info.setVisible(true);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }else if(e.getSource()==cancel){
        
            setVisible(false);
        
        }
    }
}
