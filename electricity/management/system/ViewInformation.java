package electricity.management.system;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.*;


public class ViewInformation extends JFrame implements ActionListener{
    private JButton cancel;
    private String meterno;
    ViewInformation(String meterno) {
        this.meterno=meterno;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150,50,850,650);
        getContentPane().setBackground(Color.white);
        
        setLayout(null);
        
        JLabel heading =new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("Fira Code",Font.PLAIN,20));
        add(heading);
        
        JLabel lblname =new JLabel("Name");
        lblname.setBounds(70,80,100,20);
        add(lblname);
        
        JLabel name =new JLabel("");
        name.setBounds(250,80,100,20);
        add(name);
        
        JLabel lblmeter =new JLabel("Meter Number");
        lblmeter.setBounds(70,140,100,20);
        add(lblmeter);
        
        JLabel meter =new JLabel("");
        meter.setBounds(250,140,100,20);
        add(meter);
        
        JLabel lbladdress =new JLabel("Address");
        lbladdress.setBounds(70,200,100,20);
        add(lbladdress);
        
        JLabel address =new JLabel("");
        address.setBounds(250,200,200,20);
        add(address);
        
        JLabel lblcity =new JLabel("City");
        lblcity.setBounds(70,260,100,20);
        add(lblcity);
        
        JLabel city =new JLabel("");
        city.setBounds(250,260,100,20);
        add(city);
        
        JLabel lblstate =new JLabel("State");
        lblstate.setBounds(500,80,100,20);
        add(lblstate);
        
        JLabel state =new JLabel("");
        state.setBounds(650,80,100,20);
        add(state);
        
        JLabel lblemail =new JLabel("Email");
        lblemail.setBounds(500,140,100,20);
        add(lblemail);
        
        JLabel email =new JLabel("");
        email.setBounds(650,140,150,20);
        add(email);
        
        JLabel lblphone =new JLabel("Phone");
        lblphone.setBounds(500,200,100,20);
        add(lblphone);
        
        JLabel phone =new JLabel("");
        phone.setBounds(650,200,100,20);
        add(phone);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(350,340,100,25);
        add(cancel);
        
        cancel.addActionListener(this);
        
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2=il.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        
        JLabel image=new JLabel(il);
        image.setBounds(20,350,600,300);
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
    
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==cancel){
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        ViewInformation viewInformation=new ViewInformation("");
        viewInformation.setVisible(true);
    }
    
}
