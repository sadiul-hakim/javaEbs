package electricity.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private JButton signUp;
    private JButton loginButton;
    private JButton cancelButton;
    private JComboBox<String> loginInAs;
    private JTextField usernameField;
    private JTextField passwordField;
    private ResultSet data;
  
    Login(){
        super("Login Page");
        
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(300,20,100,20);
        add(lblusername);


        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);

        JLabel lblloginas=new JLabel("Logging in as");
        lblloginas.setBounds(300,100,100,20);
        add(lblloginas);

        usernameField=new JTextField();
        usernameField.setBounds(400,20,150,20);
        add(usernameField);

        passwordField=new JTextField();
        passwordField.setBounds(400,60,150,20);
        add(passwordField);

        loginInAs=new JComboBox<>(new String[]{"Admin","Customer"});
        loginInAs.setBounds(400,100,150,20);
        add(loginInAs);

        ImageIcon loginImage=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image loginIcon=loginImage.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH);
        loginImage=new ImageIcon(loginIcon);
        loginButton=new JButton("Login");
        loginButton.setBounds(330,140,100,20);
        loginButton.setIcon(loginImage);
        add(loginButton);

        ImageIcon cancelImage=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelIcon=cancelImage.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH);
        cancelImage=new ImageIcon(cancelIcon);
        cancelButton=new JButton("Cancel");
        cancelButton.setBounds(450,140,100,20);
        cancelButton.setIcon(cancelImage);
        add(cancelButton);


        ImageIcon signupImage=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image signupIcon=signupImage.getImage().getScaledInstance(16,16,Image.SCALE_SMOOTH);
        signupImage=new ImageIcon(signupIcon);
        signUp=new JButton("signup");
        signUp.setBounds(390,170,100,20);
        signUp.setIcon(signupImage);
        add(signUp);

        signUp.addActionListener(this);

        ImageIcon leftIcon=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image leftImage=leftIcon.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
        leftIcon=new ImageIcon(leftImage);
        JLabel image=new JLabel(leftIcon);
        image.setBounds(0,0,250,250);
        add(image);
        
        loginButton.addActionListener(this);


        setSize(640,300);
        setLocation(400,200);
        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signUp){
            setVisible(false);
           Signup signup= new Signup();
           signup.setVisible(true);
        }else if(e.getSource()==loginButton){
            String userName=usernameField.getText();
            String password=passwordField.getText();
            String type=loginInAs.getSelectedItem().toString();
            
            try{
                Conn c=new Conn();
                String query="""
                             select * from login where username = '%s' and password = '%s' and user='%s';
                             """.formatted(userName,password,type);
                data=c.command.executeQuery(query);
                
                if(data.next())
                {
                    JOptionPane.showMessageDialog(null, "Logged in successfully.");
                    usernameField.setText("");
                    passwordField.setText("");
                    setVisible(false);
                    
                    new Project(data.getString("user"),data.getString("meter_no"));
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Credentials");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
