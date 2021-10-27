package app.Classes;

import app.Gui.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Admin extends JFrame implements ActionListener {

    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private static final Color regularColor = Color.BLUE;
    private static final Font font = new Font("SansSerif", Font.BOLD, 16);
    private static final Font font2 = new Font("SansSerif", Font.BOLD, 22);
    private static final Font font3 = new Font("SansSerif",  Font.ITALIC, 14);
    private String select=null;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button;
    private JLabel last;
    private int newID;
    private ChequingAccount user;
    private ArrayList<String> myList;
    private IdleListener timer;


    public Admin() {
        super("**** Admin Console  ****");
        timer = new IdleListener(180);

        initialize();

        timer.startTimer();


        newID = createID(myList);

        user = new ChequingAccount(newID);

    }


    private void getUserData(int ID){

        user.setID(ID);
        textField1.setText(user.getName());
        textField2.setText(user.getLastName());
        textField3.setText(Integer.toString(user.getSIN()));
        textField4.setText(user.getBirthDate());
        textField5.setText(Integer.toString(user.getBalLeft()));
        textField6.setText(Integer.toString(user.getBalRight()));
        textField7.setText(user.getCurrency());
        last.setText(user.getLastActivity());

    }


    @SuppressWarnings("unchecked")
    private void initialize() {
        setSize(500, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel infoPanel = new JPanel();
        JPanel balancePanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2,10,10));
        balancePanel.setLayout(new GridLayout(1, 3,10,10));


        JPanel topPanel = new JPanel();
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon = UserInterface.createImageIcon("/images/topAdmin.png");
        logo.setIcon(icon);

        logo.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));
        topPanel.add(logo);
        topPanel.setBackground(backgroundColor);
        topPanel.setForeground(foregroundColor);



        JButton logout = new JButton();
        ImageIcon l1 = UserInterface.createImageIcon("/images/logout.png");
        ImageIcon l2 = UserInterface.createImageIcon("/images/logout2.png");
        logout.setIcon(l1);
        logout.setPressedIcon(l2);
        logout.setBackground(backgroundColor);
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setFocusPainted(false);
        logout.setSize(new Dimension(10, 10));
        logout.setActionCommand("LOGOUT");
        logout.addActionListener(this);



        JButton delete = new JButton();
        ImageIcon d1 = UserInterface.createImageIcon("/images/delete.png");
        ImageIcon d2 = UserInterface.createImageIcon("/images/delete2.png");
        delete.setIcon(d1);
        delete.setPressedIcon(d2);
        delete.setBackground(backgroundColor);
        delete.setBorderPainted(false);
        delete.setContentAreaFilled(false);
        delete.setFocusPainted(false);
        delete.setSize(new Dimension(10, 10));
        delete.setActionCommand("DELETE");
        delete.addActionListener(this);



        JPanel p = new JPanel();

        myList= MySQLConnect.getCustomers();

        JComboBox list = new JComboBox(myList.toArray());
        list.addItem("-- Create New --");

        list.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Please Select " +
                "a app.Classes.Customer to Edit")));
        list.setPreferredSize(new Dimension(300,60));
        list.setSelectedIndex(myList.size());
        list.setActionCommand("LIST");
        list.addActionListener(this);
        select = (String) list.getSelectedItem();

        p.add(list);
        p.add(logout);


        JPanel panel1 = new JPanel();
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(200,40));
        textField1.setFont(font);
        textField1.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Name:")));
        textField1.setForeground(foregroundColor);

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(200,40));
        textField2.setFont(font);
        textField2.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Last Name:")));
        textField2.setForeground(foregroundColor);
        panel1.add(textField1);
        panel1.add(textField2);


        JPanel panel2 = new JPanel();
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(200,40));
        textField3.setFont(font);
        textField3.setBorder(BorderFactory.createTitledBorder(new TitledBorder("SIN:")));
        textField3.setForeground(foregroundColor);

        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(200,40));
        textField4.setFont(font);
        textField4.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Birth Date:")));
        textField4.setForeground(foregroundColor);
        panel2.add(textField3);
        panel2.add(textField4);



        JPanel panel3 = new JPanel();
        textField5 = new JTextField();
        textField5.setPreferredSize(new Dimension(150,40));
        textField5.setFont(font);
        textField5.setHorizontalAlignment(SwingConstants.RIGHT);
        textField5.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Balance:")));
        textField5.setForeground(foregroundColor);
        JLabel dot = new JLabel(".");
        dot.setFont(font2);
        textField6 = new JTextField();
        textField6.setPreferredSize(new Dimension(100,40));
        textField6.setFont(font);
        textField6.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Balance:")));
        textField6.setForeground(foregroundColor);
        textField7 = new JTextField();
        textField7.setPreferredSize(new Dimension(90,40));
        textField7.setFont(font);
        textField7.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Currency:")));
        textField7.setForeground(foregroundColor);

        panel3.add(textField5);
        panel3.add(dot);
        panel3.add(textField6);
        panel3.add(textField7);



        JPanel panel4 = new JPanel();
        button = new JButton();
        ImageIcon s1 = UserInterface.createImageIcon("/images/save.png");
        ImageIcon s2 = UserInterface.createImageIcon("/images/save2.png");
        button.setIcon(s1);
        button.setPressedIcon(s2);
        button.setBackground(Color.BLUE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setSize(new Dimension(10, 10));
        button.setActionCommand("SAVE");
        button.addActionListener(this);

        panel4.add(button);
        panel4.add(delete);


        JPanel lastActivity = new JPanel();
        JLabel la = new JLabel("Last Activity: ");
        la.setFont(font3);
        last = new JLabel("N/A");
        last.setFont(font3);
        lastActivity.add(la);
        lastActivity.add(last);


        JPanel midPanel = new JPanel();
        JPanel bothPanel = new JPanel();
        bothPanel.setLayout(new GridLayout(2,1));

        infoPanel.add(p);
        infoPanel.add(panel1);
        infoPanel.add(panel2);

        balancePanel.add(panel3);

        bothPanel.add(panel4);
        bothPanel.add(lastActivity);

        midPanel.add(infoPanel);
        midPanel.add(balancePanel);
        midPanel.add(bothPanel);


        this.add(topPanel,BorderLayout.PAGE_START);
        this.add(midPanel,BorderLayout.CENTER);
    }


    private static int createID(ArrayList<String> list){

        int newID;
        Random random = new Random();
        newID = random.nextInt(1000000);

        ArrayList<Integer> newList = new ArrayList<>();

        for(String s : list) {
            try {
                newList.add(Integer.parseInt(s));
            } catch(NumberFormatException nfe) {
                System.out.println("Error -> Could not parse " + nfe);
            }
        }
            while (newList.contains(newID)) {
                newID = random.nextInt(1000000);
            }

       return newID;
    }

    private void updateInfo(ChequingAccount user) throws Exception{
        Boolean[] isSuccess = new Boolean[7];

        isSuccess[0] = user.setName(textField1.getText());
        isSuccess[1] = user.setLastName(textField2.getText());

        try {
            isSuccess[2] = user.setSIN(Integer.parseInt(textField3.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter a valid SIN");
        }

        isSuccess[3] = user.setBirthDate(textField4.getText());

        try {
            isSuccess[4] = user.setBalLeft(Integer.parseInt(textField5.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter an Integer");
        }

        try {
            isSuccess[5] = user.setBalRight(Integer.parseInt(textField6.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter an Integer");
        }

        isSuccess[6] = user.setCurrency(textField7.getText());


        for (Boolean element : isSuccess) {
            if (!element) {
                throw new Exception();
            }
        }
        JOptionPane.showMessageDialog(null, "New app.Interfaces.Account --> "+ user.getID() + " Added !");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.eventDispatched(e);

        eventHandler(e);
    }


    @SuppressWarnings("unchecked")
    private void eventHandler(ActionEvent e){

        JComboBox<String> cb;


        if ("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();
            button.setBackground(regularColor);

            if (e.getSource() instanceof JComboBox) {
                select = (String) cb.getSelectedItem();
                try{
                    getUserData(Integer.parseInt(select));
                }catch(Exception ex){
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    last.setText("N/A");
                }
            }
        }

        try {
            if ("SAVE".equals(e.getActionCommand())) {

                if(select.equals("-- Create New --")){
                    ChequingAccount newUser = new ChequingAccount(newID);
                    newUser.setID(newID);
                    updateInfo(newUser);
                    newUser.createAccount(newID);
                }
                else {
                    updateInfo(user);
                }
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error --> Cannot Write to the Database");
        }


        if("LOGOUT".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null,"Logged out successfully!");
            System.exit(0);
        }


        if ("DELETE".equals(e.getActionCommand())) {

            switch (select) {
                case "-- Create New --":
                    JOptionPane.showMessageDialog(null, "No User Selected!");
                    break;
                case "0":
                    JOptionPane.showMessageDialog(null, "Cannot Delete app.Classes.Admin!!");
                    break;
                default:
                    int userID = user.getID();
                    int response = JOptionPane.showConfirmDialog(null, "Delete " + user.findUserName(userID) + " ?");

                    if (response == JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(null, "ID "+ userID + " Has Been Successfully Deleted!");
                        user.deleteAccount(user.getID());
                    }
                    break;
            }

        }
}
}
