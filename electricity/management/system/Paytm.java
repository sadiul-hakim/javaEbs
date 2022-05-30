
package electricity.management.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class Paytm  extends JFrame implements ActionListener{
    private String meter_no;
    private JButton back;
    public Paytm(String meterno) {
        meter_no=meterno;
        
        JEditorPane j=new JEditorPane();
        j.setEditable(false);
        
        try{
            j.setPage("https://paytm.com/");
            
        }catch(Exception ex){
            j.setContentType("text/html");
            String content="""
                           <html>
                                <head>
                                    <title>
                                        No Content
                                    </title>
                                </head>
                                
                                <body>
                                    <h1>No Content Found</h1>
                                </body>
                           
                           </html>
                           """;
            
            j.setText(content);
        }
        
        JScrollPane pane=new JScrollPane(j);
        add(pane);
        setBounds(400,150,800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        
        back=new JButton("Back");
        back.setBounds(640,20,80,30);
        add(back);
    }
    
    public static void main(String[] args) {
        new Paytm("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        setVisible(false);
        PayBill bill=new PayBill(meter_no);
        bill.setVisible(true);
    }
}
