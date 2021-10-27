package app.Gui;

import app.Classes.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;


public class UserInterface extends JPanel implements ActionListener {

    private JLabel picture;
    private JPasswordField pw;
    private JFormattedTextField username;
    private JProgressBar progress;
    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private int counter=0, i=0;
    private String select=null;
    private static final int maxNumberOfTries = 5;
    private static JFrame frame;
    private JLabel status;
    private ImageIcon online;
    private ImageIcon offline;


    private Connection con=null;

    private UserInterface() {
        super(new BorderLayout(1,1));
        initialize();
    }


    private void initialize(){

        final String[] str = { "Admin Login","Customer Login" };


        con = MySQLConnect.ConnectDB();


        status = new JLabel();
        status.setHorizontalAlignment(JLabel.LEFT);
        online = createImageIcon("/images/online.png");
        offline = createImageIcon("/images/offline.png");
        status.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));

        status();

        JPanel leftPanel = new JPanel();
        JPanel authPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();


        leftPanel.setBackground(backgroundColor);
        authPanel.setBackground(backgroundColor);
        rightPanel.setBackground(backgroundColor);
        leftPanel.setLayout(new GridLayout(1,1));
        authPanel.setLayout(new GridLayout(4,4));
        rightPanel.setLayout(new GridLayout(4,4));


        JComboBox<java.lang.String> list = new JComboBox<>(str);
        list.setSelectedIndex(1);
        select = (String)list.getSelectedItem();
        list.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Select Role:")));
        list.setBackground(backgroundColor);
        list.setForeground(foregroundColor);
        list.setActionCommand("LIST");
        list.addActionListener(this);


        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.RIGHT);
        updateLabel(str[list.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));
        picture.setPreferredSize(new Dimension(177, 122+10));


        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon = createImageIcon("/images/logo.png");
        logo.setIcon(icon);
        logo.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));


        pw = new JPasswordField(10);
        pw.setHorizontalAlignment(SwingConstants.CENTER);
        pw.setMinimumSize(new Dimension(400, 20));
        pw.setText("");
        pw.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Password:")));
        pw.setBackground(backgroundColor);
        pw.setForeground(foregroundColor);
        pw.setSelectedTextColor(backgroundColor);
        pw.setSelectionColor(foregroundColor);
        pw.setActionCommand("ENTER");
        pw.addActionListener(this);


        username = new JFormattedTextField();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Username:")));
        username.setBackground(backgroundColor);
        username.setForeground(foregroundColor);
        username.setSelectedTextColor(backgroundColor);
        username.setSelectionColor(foregroundColor);
        username.setActionCommand("ENTER");
        username.addActionListener(this);


        JButton login = new JButton();
        ImageIcon icon2 = createImageIcon("/images/login.png");
        ImageIcon icon3 = createImageIcon("/images/login2.png");
        login.setIcon(icon2);
        login.setPressedIcon(icon3);
        login.setBackground(backgroundColor);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setSize(new Dimension(10, 10));
        login.setActionCommand("LOGIN");
        login.addActionListener(this);


        progress = new JProgressBar();
        progress.setValue(0);
        progress.setBackground(backgroundColor);
        progress.setMaximum(100);
        progress.setPreferredSize(new Dimension(600,20));
        progress.setBorderPainted(false);



        JLabel pad1 = new JLabel("-----------------------------------------------------");

        leftPanel.add(logo,LEFT_ALIGNMENT);

        authPanel.add(list);
        authPanel.add(pad1);
        authPanel.add(username,BOTTOM_ALIGNMENT);
        authPanel.add(pw,BOTTOM_ALIGNMENT);
        rightPanel.add(status,LEFT_ALIGNMENT);
        rightPanel.add(picture, LEFT_ALIGNMENT);
        rightPanel.add(login,LEFT_ALIGNMENT);
        bottomPanel.add(progress);

        this.add(authPanel,BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(20,40,20,20));
    }

    private void close(){
        WindowEvent wce = new WindowEvent(frame,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
    }

    private void status(){
        if(MySQLConnect.status){
            status.setIcon(online);
        } else status.setIcon(offline);
    }

    public void actionPerformed(ActionEvent e) {

        eventHandler(e);

    }

    @SuppressWarnings("unchecked")
    private void eventHandler(ActionEvent e) {

        status();

        JComboBox<String> cb;

        // Event 1 ---> User Clicks on the Selection Box
        if ("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();

            if (e.getSource() instanceof JComboBox) {
                select = (String) cb.getSelectedItem();
                updateLabel(select);
            }
        }
        /*
        try{
            app.Classes.Admin admin = new app.Classes.Admin();
            admin.setVisible(true);
            close();
            con.close();  //close the connection
        }catch(Exception e3){
            e3.printStackTrace();
        }
        */


        if ("ENTER".equals(e.getActionCommand()) || "LOGIN".equals(e.getActionCommand())) {


            try {

                 if (isLoginCorrect() && counter <= maxNumberOfTries ) {
                     progressBar(true);

                     if (select.equals("Admin Login") && username.getText().equals("admin")) {



                         Admin admin = new Admin();
                         admin.setVisible(true);
                         close();
                         con.close();

                     }
                     else if (select.equals("Admin Login") && !username.getText().equals("admin")) {
                         cannotLogin();
                     }

                     else if(select.equals("Customer Login")&& !username.getText().equals("admin")){

                         Customer customer = new Customer(username.getText());
                         customer.setVisible(true);
                         close();
                         con.close();
                    }
                 }
                 else {
                    cannotLogin();
                 }

            } catch(Exception e3){
                 JOptionPane.showMessageDialog(null, "Error --> Login Error..");
                //e3.printStackTrace();

            }


        }


    }


    private void cannotLogin(){
        counter++;

        if(counter==maxNumberOfTries){
            counter=0;
            JOptionPane.showMessageDialog(this,
                    "Access Denied !(" + (counter) + " Remaining)");
            System.exit(-1);
        }
        progressBar(false);
        JOptionPane.showMessageDialog(this,
                "Invalid entry. Try again.(" + (maxNumberOfTries - counter) + " Remaining)",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
    }


    private void progressBar(boolean b){
        if(b) {
            progress.setForeground(Color.GREEN);
            while (i < 100) {
                i++;
                progress.setValue(i);
            }
        }
        else{
            progress.setForeground(Color.RED);
            while (i < 100) {
                i++;
                progress.setValue(i);
                }
            }
        }


    private boolean isLoginCorrect() {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs_Admin = stmt.executeQuery("SELECT Username FROM baza.admin WHERE idadmin = 1");
            String _username =null, _password = null;

            while(rs_Admin.next()){

                _username = rs_Admin.getString("Username");
            }

            ResultSet rs_Pass = stmt.executeQuery("SELECT Password FROM baza.admin WHERE idadmin = 1");
            while(rs_Pass.next()){

                _password = rs_Pass.getString("Password");
            }

              String a =  username.getText();
              String b =  pw.getText();

            if (_username.equals(a)  && _password.equals(b) )
                return true;

            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Error --> System is Offline..");
            }

        return false;
   }


    private void updateLabel(String name) {
        ImageIcon icon = createImageIcon("./images/" + name + ".png");
        picture.setIcon(icon);
        picture.setToolTipText("A drawing of a " + name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }


    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UserInterface.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


    private static void createAndShowGUI() {


        frame = new JFrame("Welcome to BotBank Login! - Frincu Razvan");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);


        JComponent newContentPane = new UserInterface();
        newContentPane.setBackground(backgroundColor);
        newContentPane.setPreferredSize(new Dimension(700,280));
        frame.setContentPane(newContentPane);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(UserInterface::createAndShowGUI);
    }
}