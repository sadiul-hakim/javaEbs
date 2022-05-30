package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;


public class CalculateBill extends JFrame implements ActionListener,ItemListener{
    private JPanel rightPanel;
    private JTextField units;
    private JButton submit,cancel;
    private JLabel meterno,name,address,unitsConsumed,month,nameField,addressField;
    private JComboBox<String> meter,monthCombo;
    private static Conn c=new Conn();
    private static final int cost_per_units=9;
    private static final int meter_rent=47;
    private static final int service_charge=22;
    private static final int service_tax=57;
    CalculateBill(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,700,450);
        setLayout(new BorderLayout());
        //right panel
        //panel
        rightPanel=new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(173,216,230));
        add(rightPanel,"Center");
        
        //label
        JLabel heading=new JLabel("Calculate Electricity Bill");
        heading.setBounds(100,10,300,25);
        heading.setFont(new Font("Arial",Font.BOLD,18));
        rightPanel.add(heading);
        
        meterno=new JLabel("Meter No");
        meterno.setBounds(100,80,200,25);
        meterno.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(meterno);
        
        name=new JLabel("Name");
        name.setBounds(100,120,200,25);
        name.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(name);
        
        address=new JLabel("Address");
        address.setBounds(100,160,200,25);
        address.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(address);
        
        unitsConsumed=new JLabel("Units Consumed");
        unitsConsumed.setBounds(100,200,200,25);
        unitsConsumed.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(unitsConsumed);
        
        month=new JLabel("Month");
        month.setBounds(100,240,200,25);
        month.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(month);
        
        //text fields
        meter=new JComboBox<>();
        try{
            
            ResultSet data=c.command.executeQuery("select meter_no from customer;");
            while(data.next()){
                meter.addItem(data.getString("meter_no"));
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        meter.setBounds(240,80,200,20);
        rightPanel.add(meter);
        
        meter.addItemListener(this);
        
        nameField=new JLabel();
        nameField.setBounds(240,120,200,20);
        rightPanel.add(nameField);
        
        addressField=new JLabel();
        addressField.setBounds(240,160,200,20);
        rightPanel.add(addressField);
        
        try{
                String number=meter.getSelectedItem().toString();
                ResultSet data=c.command.executeQuery("select * from customer where meter_no='"+number+"';");

                while(data.next()){
                    nameField.setText(data.getString("name"));
                    addressField.setText(data.getString("address"));
            }
        }catch(Exception ex){
                    ex.printStackTrace();
        }
        
        units=new JTextField();
        units.setBounds(240,200,200,20);
        rightPanel.add(units);
        
        monthCombo=new JComboBox<>(new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"});
        monthCombo.setBounds(240,240,200,20);
        rightPanel.add(monthCombo);
        
        //button
        submit=new JButton("Submit");
        submit.setBounds(140,320,110,30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        rightPanel.add(submit);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(260,320,110,30);
        rightPanel.add(cancel);
        
        //left side
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2=il.getImage().getScaledInstance(150,270,Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        JLabel image=new JLabel(il);
        add(image,"West");
        
        submit.addActionListener(this);
        cancel.addActionListener(this);
        
        getContentPane().setBackground(Color.white);
        
    }
    public static void main(String[] args) {
        CalculateBill frame=new CalculateBill();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
           String meterno=meter.getSelectedItem().toString();
           //String name=nameField.getText();
           //String address=addressField.getText();
           String units=this.units.getText();
           String month=monthCombo.getSelectedItem().toString();
           
           int totalBill=(Integer.parseInt(units) * cost_per_units ) + meter_rent + service_charge + service_tax;
            System.out.println(totalBill);
            
            String query="""
                          insert into bill values('%s','%s','%s','%s','Not Paid');
                          """.formatted(meterno,month,units,String.valueOf(totalBill));
            try{
                Conn c=new Conn();
                c.command.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully.");
                setVisible(false);
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }else if(e.getSource()==cancel){
        
            setVisible(false);
        
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getSource()==meter){
                try{
                String number=meter.getSelectedItem().toString();
                ResultSet data=c.command.executeQuery("select * from customer where meter_no='"+number+"';");

                while(data.next()){
                    nameField.setText(data.getString("name"));
                    addressField.setText(data.getString("address"));
                }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

    }
}
