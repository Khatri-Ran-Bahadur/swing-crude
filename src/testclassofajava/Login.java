/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclassofajava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
     JLabel lbl_username,lbl_password;
     JTextField txt_username,txt_password;
     private static Connection con=null;
     PreparedStatement ps;
     ResultSet rs;
    Login(){
        
        con=DbConnect.DbConnect();
        lbl_username=new JLabel("Username");
        lbl_password=new JLabel("Password");
        txt_username=new JTextField();
        txt_password=new JTextField();
        JButton button=new JButton("Login");
        
        lbl_username.setBounds(80, 70, 100, 30);
        lbl_password.setBounds(80, 110, 100, 30);
        txt_username.setBounds(180, 70, 200, 30);
        txt_password.setBounds(180, 110, 200, 30);
        button.setBounds(180, 160, 80, 25);

        add(lbl_username);
        add(lbl_password);
        add(txt_username);
        add(txt_password);
        add(button);
        
        button.addActionListener(this);
        
        setTitle("Login");
        setSize(500,300);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
        @Override
	public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Login")){
                  String sql="select * from s_user where username=? and password=?";
                  try{
                      ps=con.prepareStatement(sql);
                      ps.setString(1, txt_username.getText());
                      ps.setString(2, txt_password.getText());
                      rs=ps.executeQuery();
                      if(rs.next()){
                          this.hide();
                          Main main=new Main();
                          main.setVisible(true);
                          
                      }else{
                          JOptionPane.showMessageDialog(rootPane, "Username and Password are Wrong !!");
                      }
                  }catch(Exception ex){
                      
                  }
                    
                }
               
	}
    
    public static void main(String args[]){
        new Login();
    }
    
    
}
