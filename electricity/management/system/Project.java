
package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Project extends JFrame implements ActionListener{
    private Container c;
    private JMenuItem newCustomer;
    private JMenuItem customerDetails;
    private JMenuItem depositDetails;
    private JMenuItem calculatebill;
    private JMenuItem updateInformation;
    private JMenuItem viewInformation;
    private JMenuItem payBill;
    private JMenuItem billDetails;
    private JMenuItem generateBill;
    private JMenuItem notepad;
    private JMenuItem calculator;
    private JMenuItem exit;
    private String user;
    private String meter_no;
    Project(String type,String meterno){
        user=type;
        meter_no=meterno;
        c=this.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2=il.getImage().getScaledInstance(1280,830,Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        JLabel image=new JLabel(il);
        add(image);
        
        
        //Menu Bar
        JMenuBar mb=new JMenuBar();
        setJMenuBar(mb);
        
        setLayout(new FlowLayout());
        
        //Menu
        //master
        JMenu master=new JMenu("Master");
        master.setForeground(Color.blue);
        
        
        //information
        JMenu information=new JMenu("Information");
        information.setForeground(Color.red);
       
        
        //information
        JMenu user=new JMenu("User");
        user.setForeground(Color.blue);
        
        
        //report
        JMenu report=new JMenu("Report");
        report.setForeground(Color.blue);
        
        
        //utility
        JMenu utility=new JMenu("Utility");
        utility.setForeground(Color.red);
       
        
        //Menu Item
        //master menu
        //new customer item
        newCustomer=new JMenuItem("New Customer");
        newCustomer.setFont(new Font("Fira Code",Font.PLAIN,12));
        newCustomer.setBackground(Color.white);
        ImageIcon icon1=new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1=icon1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon1=new ImageIcon(image1);
        newCustomer.setIcon(icon1);
        newCustomer.setMnemonic('D');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        master.add(newCustomer);
        newCustomer.addActionListener(this);
        
        //customer details item
        customerDetails=new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("Fira Code",Font.PLAIN,12));
        customerDetails.setBackground(Color.white);
        ImageIcon icon2=new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2=icon2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon2=new ImageIcon(image2);
        customerDetails.setIcon(icon2);
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
        master.add(customerDetails);
        customerDetails.addActionListener(this);
        
        //deposit details item
        depositDetails=new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("Fira Code",Font.PLAIN,12));
        depositDetails.setBackground(Color.white);
        ImageIcon icon3=new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3=icon3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon3=new ImageIcon(image3);
        depositDetails.setIcon(icon3);
        depositDetails.setMnemonic('E');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        master.add(depositDetails);
        depositDetails.addActionListener(this);
        
        //calculate bill item
        calculatebill=new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("Fira Code",Font.PLAIN,12));
        calculatebill.setBackground(Color.white);
        ImageIcon icon4=new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4=icon4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon4=new ImageIcon(image4);
        calculatebill.setIcon(icon4);
        calculatebill.setMnemonic('B');
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        master.add(calculatebill);
        
        calculatebill.addActionListener(this);
        
        //information menu
        //update information
        updateInformation=new JMenuItem("Update");
        updateInformation.setFont(new Font("Fira Code",Font.PLAIN,12));
        updateInformation.setBackground(Color.white);
        ImageIcon icon5=new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image5=icon5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon5=new ImageIcon(image5);
        updateInformation.setIcon(icon5);
        updateInformation.setMnemonic('U');
        updateInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
        information.add(updateInformation);
        
        updateInformation.addActionListener(this);
        
        //view information
        viewInformation=new JMenuItem("View");
        viewInformation.setFont(new Font("Fira Code",Font.PLAIN,12));
        viewInformation.setBackground(Color.white);
        ImageIcon icon6=new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6=icon6.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon6=new ImageIcon(image6);
        viewInformation.setIcon(icon6);
        viewInformation.setMnemonic('V');
        viewInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        information.add(viewInformation);
        
        viewInformation.addActionListener(this);
        //user menu
        //pay bill
        payBill=new JMenuItem("Pay Bill");
        payBill.setFont(new Font("Fira Code",Font.PLAIN,12));
        payBill.setBackground(Color.white);
        ImageIcon icon7=new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7=icon7.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon7=new ImageIcon(image7);
        payBill.setIcon(icon7);
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        user.add(payBill);
        
        payBill.addActionListener(this);
        //bill details
        billDetails=new JMenuItem("Bill Details");
        billDetails.setFont(new Font("Fira Code",Font.PLAIN,12));
        billDetails.setBackground(Color.white);
        ImageIcon icon8=new ImageIcon(ClassLoader.getSystemResource("icon/icon8.png"));
        Image image8=icon8.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon8=new ImageIcon(image8);
        billDetails.setIcon(icon8);
        billDetails.setMnemonic('H');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        user.add(billDetails);
        
        billDetails.addActionListener(this);
        //report menu
        //generate bill
        generateBill=new JMenuItem("Generate Bill");
        generateBill.setFont(new Font("Fira Code",Font.PLAIN,12));
        generateBill.setBackground(Color.white);
        ImageIcon icon9=new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9=icon9.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon9=new ImageIcon(image9);
        generateBill.setIcon(icon9);
        generateBill.setMnemonic('G');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
        report.add(generateBill);
        
        generateBill.addActionListener(this);
        //utility menu
        //notepad
        notepad=new JMenuItem("Notepad");
        notepad.setFont(new Font("Fira Code",Font.PLAIN,12));
        notepad.setBackground(Color.white);
        ImageIcon icon10=new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10=icon10.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon10=new ImageIcon(image10);
        notepad.setIcon(icon10);
        notepad.setMnemonic('N');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        utility.add(notepad);
        
        notepad.addActionListener(this);
        //notepad
        calculator=new JMenuItem("Calculator");
        calculator.setFont(new Font("Fira Code",Font.PLAIN,12));
        calculator.setBackground(Color.white);
        ImageIcon icon11=new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image11=icon11.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon11=new ImageIcon(image11);
        calculator.setIcon(icon11);
        calculator.setMnemonic('I');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        utility.add(calculator);
        
        calculator.addActionListener(this);
        //notepad
        exit=new JMenuItem("Exit");
        exit.setFont(new Font("Fira Code",Font.PLAIN,12));
        exit.setBackground(Color.white);
        ImageIcon icon12=new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12=icon12.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon12=new ImageIcon(image12);
        exit.setIcon(icon12);
        exit.setMnemonic('W');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
        utility.add(exit);
     
        exit.addActionListener(this);
        
        
        
        if(this.user.equals("Admin")){
            mb.add(master);
        }else{
            mb.add(user);
            mb.add(information);
            mb.add(report);
        }
                
        mb.add(utility);
        
     setVisible(true);   
     
     
        
    }
       public static void main(String[] args){
           
           new Project("","");
           
       }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getSource()==calculatebill){
            CalculateBill bill=new CalculateBill();
            bill.setVisible(true);
        
        }else if(e.getSource()==newCustomer){
            NewCustomer customer=new NewCustomer();
            customer.setVisible(true);
        
        }else if(e.getSource()==customerDetails){
            CustomerDetails frame = new CustomerDetails();
            frame.setVisible(true);
        }else if(e.getSource()==depositDetails){
            
            DepositDetails frame = new DepositDetails();
            frame.setVisible(true);
            
        }else if(e.getSource() ==updateInformation){
            
            UpdateInformation updateInformation=new UpdateInformation(meter_no);
            updateInformation.setVisible(true);
            
        }else if(e.getSource()==viewInformation){
            ViewInformation viewInformation=new ViewInformation(meter_no);
            viewInformation.setVisible(true);
        }else if (e.getSource()==payBill){
            PayBill payBill=new PayBill(meter_no);
            payBill.setVisible(true);
        }else if(e.getSource()==billDetails){
            BillDetails billDetails=new BillDetails(this.meter_no); 
            billDetails.setVisible(true);
        }else if(e.getSource()==generateBill){
            GenerateBill generateBill=new GenerateBill(meter_no);
            generateBill.setVisible(true);
        }else if(e.getSource()==notepad){
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(e.getSource()==calculator){
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(e.getSource()==exit){
            setVisible(false);
            new Login();
        }
    
    }
}
