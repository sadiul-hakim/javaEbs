package electricity.management.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;


public class Signup extends JFrame implements ActionListener,FocusListener,ItemListener {
    private JButton back;
    private JButton create;
    private Container c;
    private JComboBox<String> accountType;
    private JTextField username;
    private JTextField name;
    private JTextField password;
    private JTextField meter;
    Signup(){
        c=this.getContentPane();
        c.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(450,150,700,400);
        c.setBackground(Color.white);
        JPanel panel=new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173,216,230)));
        panel.setBackground(Color.white);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));

        JLabel heading=new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.gray);
        heading.setFont(new Font("Fira Code",Font.PLAIN,12));
        panel.add(heading);

        accountType=new JComboBox<>(new String[]{"Admin","Customer"});
        accountType.setBounds(260,50,150,20);
        accountType.setForeground(Color.green);
        panel.add(accountType);
        accountType.addItemListener(this);

        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.gray);
        lblmeter.setFont(new Font("Fira Code",Font.PLAIN,12));
        panel.add(lblmeter);

        meter=new JTextField();
        meter.setBounds(260,90,150,20);
        panel.add(meter);
        meter.setEnabled(false);
        meter.addFocusListener(this);

        JLabel lblusername=new JLabel("User Name");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.gray);
        lblusername.setFont(new Font("Fira Code",Font.PLAIN,12));
        panel.add(lblusername);

        username=new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);


        JLabel lblname=new JLabel("Name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.gray);
        lblname.setFont(new Font("Fira Code",Font.PLAIN,12));
        panel.add(lblname);

        name=new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);

        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.gray);
        lblpassword.setFont(new Font("Fira Code",Font.PLAIN,12));
        panel.add(lblpassword);

        password=new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);

        create=new JButton("Create");
        create.setBackground(Color.black);
        create.setForeground(Color.white);
        create.setBounds(140,260,120,25);
        panel.add(create);

        create.addActionListener(this);

        back=new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(280,260,120,25);
        panel.add(back);
        back.addActionListener(this);

        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=il.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);

        JLabel image=new JLabel(il);
        image.setBounds(415,30,250,250);
        panel.add(image);

        c.add(panel);


    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            setVisible(false);
            new Login();
        }else if(e.getSource()==create){
            String type=accountType.getSelectedItem().toString();
            String un=username.getText();
            String n=name.getText();
            String m=meter.getText();
            String p=password.getText();
            
            try{
                Conn c=new Conn();
                String query="";
                if(type.equals("Admin")){
                    query="""
                             insert into login (username,password,user)
                          values( '%s' ,'%s' , '%s');
                             """.formatted(un,p,type);
                }else{
                    query="""
                          update login
                          set username='%s' , password = '%s' , user = '%s'
                          where meter_no = '%s';
                          """.formatted(un,p,type,m);
                    
                }
                c.command.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created successfully");
                setVisible(false);
                new Login();
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        Signup signup=new Signup();
        signup.setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void focusLost(FocusEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        String meterno=meter.getText();
        String type=accountType.getSelectedItem().toString();
        
        
        String query="""
                     select * from login where meter_no = '%s';
                     """.formatted(meterno);
        
        try{
            
            Conn c=new Conn();
            ResultSet data=c.command.executeQuery(query);
            
            while(data.next()){
                this.name.setText(data.getString("name"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        if(e.getSource()==accountType){
            int index=accountType.getSelectedIndex();
            if(index==1){
                meter.setEnabled(true);
                name.setEnabled(false);
            }else{
                name.setEnabled(true);
                meter.setText("");
                meter.setEditable(false);
            }
            
        }
    
    }
}
