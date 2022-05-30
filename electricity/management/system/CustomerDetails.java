
package electricity.management.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


public class CustomerDetails extends JFrame implements ActionListener{
     private JTable table;
     private JScrollPane scroll;
     private JButton print;
    CustomerDetails()  {
        super("Customer Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50,50,1200,700);
        setLayout(null);
        
        table = new JTable();
        table.setEnabled(false);
        scroll=new JScrollPane(table);
        scroll.setBounds(0,0,1200,600);
        add(scroll);
        
        try{
            Conn c=new Conn();
            ResultSet data=c.command.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(data));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        print=new JButton("Print");
        print.setBounds(20,620,80,20);
        print.addActionListener(this);
        add(print);
    }
    
    public static void main(String[] args) {
        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getSource()==print){
            try{
                table.print();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    
    }
}
