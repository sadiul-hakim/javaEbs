
package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateInformation extends JFrame implements ActionListener{
    private JButton update,cancel;
    private String meterno;
    private JLabel name,meter;
    private JTextField address,city,state,email,phone;
    public UpdateInformation(String meterno) {
        this.meterno=meterno;
        setBounds(150,100,1000,500);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        
        //labels
        JLabel heading =new JLabel("Update Customer Information");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("Fira Code",Font.PLAIN,20));
        add(heading);
        
        JLabel lblname =new JLabel("Name");
        lblname.setBounds(30,70,50,20);
        add(lblname);
        
        name =new JLabel("");
        name.setBounds(140,70,120,20);
        add(name);
        
        JLabel lblmeter =new JLabel("Meter Number");
        lblmeter.setBounds(30,110,100,20);
        add(lblmeter);
        
        meter =new JLabel("");
        meter.setBounds(140,110,120,20);
        add(meter);
        
        JLabel lbladdress =new JLabel("Address");
        lbladdress.setBounds(30,150,50,20);
        add(lbladdress);
        
        address =new JTextField();
        address.setBounds(140,150,120,20);
        add(address);
        
        JLabel lblcity =new JLabel("City");
        lblcity.setBounds(30,190,50,20);
        add(lblcity);
        
        city =new JTextField();
        city.setBounds(140,190,120,20);
        add(city);
        
        JLabel lblstate =new JLabel("State");
        lblstate.setBounds(30,230,50,20);
        add(lblstate);
        
        state =new JTextField();
        state.setBounds(140,230,120,20);
        add(state);
        
        JLabel lblemail =new JLabel("Email");
        lblemail.setBounds(30,270,50,20);
        add(lblemail);
        
        email =new JTextField();
        email.setBounds(140,270,120,20);
        add(email);
        
        JLabel lblphone =new JLabel("Phone");
        lblphone.setBounds(30,310,50,20);
        add(lblphone);
        
        phone =new JTextField();
        phone.setBounds(140,310,120,20);
        add(phone);
        
        update=new JButton("Update");
        update.setBounds(30,350,100,30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        add(update);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(150,350,100,30);
        cancel.setBackground(Color.red);
        cancel.setForeground(Color.white);
        add(cancel);
        
        update.addActionListener(this);
        cancel.addActionListener(this);
        
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2=il.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        JLabel image=new JLabel(il);
        image.setBounds(500,70,400,300);
        add(image);
        
        try{
            Conn c=new Conn();
            
            ResultSet data=c.command.executeQuery("select * from customer where meter_no = '%s';".formatted(meterno));
            
            while(data.next()){
                name.setText(data.getString("name"));
                meter.setText(data.getString("meter_no"));
                address.setText(data.getString("address"));
                city.setText(data.getString("city"));
                state.setText(data.getString("state"));
                email.setText(data.getString("email"));
                phone.setText(data.getString("phone"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        UpdateInformation frame=new UpdateInformation("");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getSource()==update){
            String address=this.address.getText();
            String city=this.city.getText();
            String state=this.state.getText();
            String email=this.email.getText();
            String phone=this.phone.getText();
            
            String query="""
                         update customer
                         set address ='%s',city = '%s',state = '%s',email = '%s' , phone = '%s'
                         where meter_no='%s';
                         """.formatted(address,city,state,email,phone,this.meterno);
            JOptionPane.showMessageDialog(null,"Customer Details updated successfully.");
            setVisible(false);
            try{
                Conn c=new Conn();
                c.command.executeUpdate(query);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }else if(e.getSource()==cancel){
            setVisible(false);
        }
    }
   
}
