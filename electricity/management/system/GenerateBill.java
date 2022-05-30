
package electricity.management.system;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.*;


public class GenerateBill extends JFrame implements ActionListener{
    private JComboBox<String> month;
    private String meterno;
    private JTextArea area;
    private JButton bill;
    public GenerateBill(String meterno) {
        this.meterno=meterno;
        setLayout(new BorderLayout());
        setBounds(550,30,500,650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel heading=new JLabel("Generate Bill");
        JLabel meter_no=new JLabel(meterno);
        
        JPanel panel=new JPanel();
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
        
        area=new JTextArea(50,15);
        area.setText("\n\n-------------------Click no the-------------------\n\t Bill Button to get \n\t the bill of selected month.");
        area.setFont(new Font("Fira Code",Font.PLAIN,16));
        
        JScrollPane scroll=new JScrollPane(area);
        bill=new JButton("Bill");
        
        panel.add(heading);
        panel.add(meter_no);
        panel.add(month);
        
        add(scroll,"Center");
        add(panel,"North");
        add(bill,"South");
        
        bill.addActionListener(this);
        
    }
    
    public static void main(String[] args) {
        GenerateBill frame=new GenerateBill("");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        if(e.getSource()==bill){
            String month=this.month.getSelectedItem().toString();
            area.setText("\tBangladesh Polli bar bar chole jauya electricity.\nElectricity bill generator for the month\n\t\t of %s ,2022".formatted(month));

            
            String query="""
                         select * from customer where meter_no = '%s';
                         """.formatted(meterno);
            
            Conn c=new Conn();
           try{
                ResultSet data=c.command.executeQuery(query);
                
                if(data.next()){
                    String Text=
                            "\n\n Customer Name : %s \n Meter Number : %s \n Address : %s \n City : %s \n State : %s \n Email : %s \n Phone : %s \n"
                                    .formatted(
                                            data.getString("name")
                                            ,data.getString("meter_no")
                                            ,data.getString("address")
                                            ,data.getString("city")
                                            ,data.getString("state")
                                            ,data.getString("email")
                                            ,data.getString("phone")
                                    );
                    
                   area.append(Text);
                   
                }
                
                ResultSet meterdata=c.command.executeQuery("select * from meter_info where meter_no = '%s';".formatted(meterno));
                   if(meterdata.next()){
                       String newText=""" 
                         ------------------------             
                         Meter Location : %s
                         Meter Type     : %s
                         Phase Code     : %s
                         Bill Type      : %s
                         Days           : %s
                        """.formatted(
                                meterdata.getString("meter_location"),
                                meterdata.getString("meter_type"),
                                meterdata.getString("phase_code"),
                                meterdata.getString("bill_type"),
                                meterdata.getString("days")
                        );
                       
                       area.append(newText);
                       
                   }
                   
                   String taxText="""
                                   --------------------
                                   Cost Per Units :  9
                                   Meter rent     : 47
                                   Service Charge : 22
                                   Service Tax    : 57
                                  """;
                   
                   area.append(taxText);
                   
                   ResultSet billData=c.command.executeQuery("select * from bill where meter_no = '%s' and month = '%s';".formatted(meterno,month));
                   if(billData.next()){
                       String billText="""
                                        ----------------
                                        Current Month : %s
                                        Units Cosumed : %s
                                        Total Bill    : %s
                                       """.formatted(
                                               billData.getString("month"),
                                               billData.getString("units"),
                                               billData.getString("totalBill")
                                       );
                       area.append(billText);
                   }
           }catch(Exception ex){
               ex.printStackTrace();
           }
            
        }
    }
}
