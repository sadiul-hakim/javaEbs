package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MeterInfo extends JFrame implements ActionListener{
    private JPanel rightPanel;
    private JComboBox<String> locationField,typeField,phaseField,billField;
    private JButton submit;
    private JLabel lblmeterno,daysField,noteField,meterNumber;
    private String meterno;
    MeterInfo(String meterno){
        this.meterno=meterno;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,700,500);
        setLayout(new BorderLayout());
        //right panel
        //panel
        rightPanel=new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(173,216,230));
        add(rightPanel,"Center");
        
        //label
        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(100,10,200,25);
        heading.setFont(new Font("Arial",Font.BOLD,18));
        rightPanel.add(heading);
        
        JLabel meternumber=new JLabel("Meter Number");
        meternumber.setBounds(100,80,200,25);
        meternumber.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(meternumber);
        
        JLabel lblmeter=new JLabel("Meter Location");
        lblmeter.setBounds(100,120,200,25);
        lblmeter.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(lblmeter);
        
        
        JLabel meterType=new JLabel("Meter Type");
        meterType.setBounds(100,160,200,25);
        meterType.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(meterType);
        
        JLabel phaseCode=new JLabel("Phase Code");
        phaseCode.setBounds(100,200,200,25);
        phaseCode.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(phaseCode);
        
        JLabel billType=new JLabel("Bill Type");
        billType.setBounds(100,240,200,25);
        billType.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(billType);
        
        JLabel days=new JLabel("Days");
        days.setBounds(100,280,200,25);
        days.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(days);
        
        JLabel note=new JLabel("Note");
        note.setBounds(100,320,200,25);
        note.setFont(new Font("Fira Code",Font.PLAIN,12));
        rightPanel.add(note);
        
        //text fields
        meterNumber=new JLabel(this.meterno);
        meterNumber.setBounds(240,80,200,25);
        meterNumber.setFont(new Font("Fira Code",Font.BOLD,13));
        rightPanel.add(meterNumber);
        
        locationField=new JComboBox<>(new String[]{"Outside","Inside"});
        locationField.setBounds(240,120,200,20);
        rightPanel.add(locationField);
        
        typeField=new JComboBox<>(new String[]{"Electric Meter","Solar Meter","Smart Meter"});
        typeField.setBounds(240,160,200,20);
        rightPanel.add(typeField);
        
        phaseField=new JComboBox<>(new String[]{"011","022","033","044","055","066","077","088","099"});
        phaseField.setBounds(240,200,200,20);
        rightPanel.add(phaseField);
        
        billField=new JComboBox<>(new String[]{"Normal","Industrial"});
        billField.setBounds(240,240,200,20);
        rightPanel.add(billField);
        
        daysField=new JLabel("30 Days");
        daysField.setBounds(240,280,200,20);
        rightPanel.add(daysField);
        
        noteField=new JLabel("By Default Bill is Calculated for 30 days.");
        noteField.setBounds(240,320,200,20);
        rightPanel.add(noteField);
        
        //button
        submit=new JButton("Submit");
        submit.setBounds(140,390,110,30);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        rightPanel.add(submit);
        
        submit.addActionListener(this);

        
        
        //left side
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=il.getImage().getScaledInstance(150,300,Image.SCALE_SMOOTH);
        il=new ImageIcon(i2);
        JLabel image=new JLabel(il);
        add(image,"West");
        
        
        getContentPane().setBackground(Color.white);
        
    }
    public static void main(String[] args) {
        MeterInfo frame=new MeterInfo("");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String meter_no=meterno;
            String location=locationField.getSelectedItem().toString();
            String type=typeField.getSelectedItem().toString();
            String phaseCode=phaseField.getSelectedItem().toString();
            String bill=billField.getSelectedItem().toString();
            String days="30";
            
            
            String query="""
                          insert into meter_info values('%s','%s','%s','%s','%s','%s');
                          """.formatted(meter_no,location,type,phaseCode,bill,days);
            
            
            try{
                Conn c=new Conn();
                c.command.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(null,"Meter Information added successfully");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
    }
}
