package electricity.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class BillDetails extends JFrame {
    private String meterno;
    private JButton print;
    private JTable table;
    public BillDetails(String meter_no) {
        this.meterno=meter_no;
        setBounds(400,150,700,650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        table=new JTable();
        JScrollPane pane=new JScrollPane(table);
        pane.setBounds(0,0,700,500);
        add(pane);
        
        print=new JButton("Print");
        print.setBounds(30,520,100,20);
        
        print.addActionListener(((e) -> {
            try{
                table.print();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        } ));
        
        add(print);

        try{
            Conn c=new Conn();
            ResultSet data=c.command.executeQuery("select * from bill where meter_no = '%s' ;".formatted(meterno));
            table.setModel(DbUtils.resultSetToTableModel(data));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        BillDetails frame=new BillDetails("");
        frame.setVisible(true);
    }
}
