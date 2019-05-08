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
import javax.swing.JTextField;

public class UserAdd extends JFrame implements ActionListener {

     JLabel lbl_username,lbl_password,lbl_address;
     JTextField txt_username,txt_password,txt_address;
     private static Connection con=null;
     PreparedStatement ps;
     ResultSet rs;
    UserAdd(){
        
        con=DbConnect.DbConnect();
        lbl_username=new JLabel("Username");
        lbl_password=new JLabel("Password");
        lbl_address=new JLabel("Address");
        txt_username=new JTextField();
        txt_password=new JTextField();
        txt_address=new JTextField();
        JButton button=new JButton("Submit");
        
        lbl_username.setBounds(80, 70, 100, 30);
        lbl_password.setBounds(80, 110, 100, 30);
        lbl_address.setBounds(80, 150, 100, 30);
        txt_username.setBounds(180, 70, 200, 30);
        txt_password.setBounds(180, 110, 200, 30);
        txt_address.setBounds(180, 150, 200, 30);
        button.setBounds(180, 200, 80, 25);

        add(lbl_username);
        add(lbl_password);
        add(lbl_address);
        add(txt_username);
        add(txt_password);
        add(txt_address);
        add(button);
        
        button.addActionListener(this);
        
        setTitle("Add New User");
        setSize(450,400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
        @Override
	public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Submit")){
                 String sql="INSERT INTO `s_user`(`username`, `address`, `password`) VALUES (?,?,?)";
                 try{
                     ps=con.prepareStatement(sql);
                     ps.setString(1, txt_username.getText());
                     ps.setString(2, txt_password.getText());
                     ps.setString(3, txt_address.getText());
                     //System.out.println(ps.execute());
                     
                     if(ps.execute()){
                         JOptionPane.showMessageDialog(rootPane, "Sorry Can not be added the user");                         
                     }else{
                         JOptionPane.showMessageDialog(rootPane, "Successfully Added");
                     }
                 }catch(Exception ex){
                     JOptionPane.showMessageDialog(rootPane, ex);
                 }
            }
               
	}
    
    public static void main(String args[]){
        new UserAdd();
    }
    
}
