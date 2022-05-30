
package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;


public class PayBill extends JFrame implements ItemListener,ActionListener{
    private JLabel name,meternumber,units,totalBill,status;
    private JComboBox<String> month;
    private String meter_no;
    private JButton pay,back;
    private static  Conn c=new Conn();
    PayBill(String meter_no){
        this.meter_no=meter_no;
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200,100,900,600);
        
        JLabel heading =new JLabel("Electricity Bill");
        heading.setBounds(250,10,400,80);
        heading.setFont(new Font("Fira Code",Font.PLAIN,20));
        add(heading);
        
        JLabel lblmeternumber =new JLabel("Meter Number");
        lblmeternumber.setBounds(35,80,100,20);
        add(lblmeternumber);
        
        meternumber =new JLabel("");
        meternumber.setBounds(150,80,120,20);
        add(meternumber);
        
        JLabel lblname =new JLabel("Name");
        lblname.setBounds(35,120,100,20);
        add(lblname);
        
        name =new JLabel("");
        name.setBounds(150,120,120,20);
        add(name);
        
        JLabel lblmonth =new JLabel("Month");
        lblmonth.setBounds(35,160,100,20);
        add(lblmonth);
        
        month=new JComboBox<>(
                new String[]{
                    "January",
                    "February",
                    "March",
                    "April",
                    "May",
                    "June",
                    "July",
                    "August",
                    "September",
                    "October",
                    "November",
                    "December"
                }
        );
        month.setBounds(150,160,100,30);
        add(month);
        month.addItemListener(this);
        
        JLabel lblunits =new JLabel("Units");
        lblunits.setBounds(35,200,100,20);
        add(lblunits);
        
        units =new JLabel("");
        units.setBounds(150,200,120,20);
        add(units);
        
        JLabel lbltotalbill =new JLabel("Total Bill");
        lbltotalbill.setBounds(35,240,100,20);
        add(lbltotalbill);
        
        totalBill =new JLabel("");
        totalBill.setBounds(150,240,120,20);
        totalBill.setForeground(Color.red);
        add(totalBill);
        
        JLabel lblstatus =new JLabel("Status");
        lblstatus.setBounds(35,280,100,20);
        add(lblstatus);
        
        status =new JLabel("");
        status.setBounds(150,280,120,20);
        status.setForeground(Color.red);
        add(status);
        
        pay=new JButton("Pay");
        pay.setBounds(50,320,70,20);
        add(pay);
        pay.addActionListener(this);
        
        back=new JButton("Back");
        back.setBounds(150,320,70,20);
        add(back);
        back.addActionListener(this);
        
         try{
           
            ResultSet query1=c.command.executeQuery("select * from customer where meter_no = '%s';".formatted(meter_no));
            while(query1.next()){
                name.setText(query1.getString("name"));
                meternumber.setText(query1.getString("meter_no"));
            }
            
            ResultSet query2=c.command.executeQuery("select * from bill where meter_no = '%s' and month ='%s';".formatted(meter_no,month.getSelectedItem().toString()));
            while(query2.next()){
                units.setText(query2.getString("units"));
                totalBill.setText(query2.getString("totalBill"));
                status.setText(query2.getString("status"));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
         
         getContentPane().setBackground(Color.white);
         
         ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
         Image i2=il.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
         il=new ImageIcon(i2);
         
         JLabel image=new JLabel(il);
         image.setBounds(400,120,600,300);
         add(image);
        
    }
    public static void main(String[] args) {
        PayBill payBill=new PayBill("");
        payBill.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
        if(e.getSource()==month){
            String item=month.getSelectedItem().toString();
            try{
                
                ResultSet query2=c.command.executeQuery("select * from bill where meter_no = '%s' and month ='%s';".formatted(meter_no,item));
               
                
                while(query2.next()){
                    units.setText(query2.getString("units"));
                    totalBill.setText(query2.getString("totalBill"));
                    status.setText(query2.getString("status"));
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
                ex.printStackTrace();
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        if(e.getSource()==pay){
            try{
                Conn c=new Conn();
                c.command.executeUpdate("update bill set status = 'Paid' where meter_no='%s' and month = '%s';".formatted(meter_no,this.month.getSelectedItem().toString()));
//                JOptionPane.showMessageDialog(null, "Bill Paid");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            setVisible(false);
//            new Paytm(meter_no);
        }else{
            setVisible(false);
        }
    }
}
