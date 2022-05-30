
package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class DepositDetails extends JFrame implements ActionListener{
    private JComboBox<String> meterNumber;
    private JComboBox<String> month;
    private JTable table;
    private JScrollPane scroll;
    private JButton search,print;
    DepositDetails(){
        super("Deposit Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,700,600);
        
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
        //meter number 
        JLabel lblmeternumber=new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
        meterNumber=new JComboBox<>();
        meterNumber.setBounds(180,20,150,20);
        add(meterNumber);
        meterNumber.setEditable(true);
        
         //month 
        JLabel lblmonth=new JLabel("Search By Month");
        lblmonth.setBounds(350,20,150,20);
        add(lblmonth);
        
        search=new JButton("Search");
        print=new JButton("Print");
        
        search.setBounds(20,60,80,20);
        print.setBounds(120,60,80,20);
        
        add(search);
        add(print);
        
        search.addActionListener(this);
        print.addActionListener(this);
        
        month=new JComboBox<>(new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"});
        month.setBounds(470,20,150,20);
        add(month);
        
        try{
            Conn c=new Conn();
            ResultSet data=c.command.executeQuery("select meter_no from customer;");
            
            while(data.next()){
               meterNumber.addItem(data.getString("meter_no"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        table = new JTable();
        table.setEnabled(false);
        scroll=new JScrollPane(table);
        scroll.setBounds(0,100,700,500);
        add(scroll);
        
        
        try{
            Conn c=new Conn();
            ResultSet data=c.command.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(data));
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
        DepositDetails frame = new DepositDetails();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        if(e.getSource()==search){
            String query="""
                         select * from bill where meter_no = '%s' and month = '%s';
                         """.formatted(meterNumber.getSelectedItem().toString(),month.getSelectedItem().toString());
            try{
                Conn c=new Conn();
                ResultSet data=c.command.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(data));
            }catch(Exception ex){
            
                ex.printStackTrace();
            
            }
        }else{
            try{
                table.print();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    
    }

    
}
