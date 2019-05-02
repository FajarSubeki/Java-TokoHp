/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author fajarsubeki
 */
public class config {
    
    public static Connection conn;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static Connection Conn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_conter_hp", "root", "");
            st = conn.createStatement();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public static void main(String args[]){
        System.out.println(config.Conn());
    }

    public ResultSet getTampil(String hasil){
        try{
            rs = st.executeQuery(hasil);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Belum dapat menampilkan data");
        }
        return rs;
    }
    
}
