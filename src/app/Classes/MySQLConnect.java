package app.Classes;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MySQLConnect {
    public static boolean status;
    private static Connection con;


    public static Connection ConnectDB() {

        String username = "root";
        String password = "admin";
        String url = "jdbc:mysql://127.0.0.1:3306/?user=root";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try{
             con  = DriverManager.getConnection(url,username,password);

            status = true;
            return con;

        }catch(SQLException ex){
            ex.printStackTrace();
            status = false;
            return null;
        }
    }


    static ArrayList getCustomers(){

        ArrayList<String> list = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            String sql = "SELECT accountID FROM account";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                list.add(rs.getString("accountID"));
            }

            rs.close();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null,"Load Unsuccessful!");
        }

        return list;
    }

}
