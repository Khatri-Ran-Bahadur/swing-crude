/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclassofajava;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author RN
 */
public class ViewUser extends JFrame implements ActionListener{

    private static Connection con=null;
     PreparedStatement ps;
     ResultSet rs;
     JTable table;
     int id;
     
     JLabel lbl_username,lbl_password,lbl_address;
     JTextField txt_username,txt_password,txt_address;
     
     ViewUser(){
        setTitle("User Details");
        con=DbConnect.DbConnect();
        setSize(600,400);
        
        setUpdateForm();
        
        setLayout(null);
        table=new JTable();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 200, 400, 136);
        add(scrollPane);
        scrollPane.setViewportView(table);
        
        table.addMouseListener(new MouseAdapter() 
        { 
            public void mouseClicked(MouseEvent me) 
            { 
                int row = table.getSelectedRow();            
                String tableClick = (table.getModel().getValueAt(row, 0).toString());
                id=Integer.parseInt(tableClick);
                fillData(id);
            } 
        });
        
        UpdateTable();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        
     }
   
     private void setUpdateForm(){
          lbl_username=new JLabel("Username");
        lbl_password=new JLabel("Password");
        lbl_address=new JLabel("Address");
        txt_username=new JTextField();
        txt_password=new JTextField();
        txt_address=new JTextField();
        JButton button=new JButton("Update");
        JButton dbutton=new JButton("Delete");
         
        lbl_username.setBounds(80, 20, 100, 30);
        lbl_password.setBounds(80, 60, 100, 30);
        lbl_address.setBounds(80, 100, 100, 30);
        
        txt_username.setBounds(180, 20, 200, 30);
        txt_password.setBounds(180, 60, 200, 30);
        txt_address.setBounds(180, 100, 200, 30);
        button.setBounds(180, 140, 80, 25);
        dbutton.setBounds(280, 140, 80, 25);

        add(lbl_username);
        add(lbl_password);
        add(lbl_address);
        add(txt_username);
        add(txt_password);
        add(txt_address);
        add(button);
        add(dbutton);
        
        button.addActionListener(this);
        dbutton.addActionListener(this);
     }
    
    private void UpdateTable(){
        try {              
              String mysql="SELECT `id` as 'ID:', `username` as 'Username', `address` as 'Address' FROM `s_user`";
              //JOptionPane.showMessageDialog(rootPane, mysql);
              ps=con.prepareStatement(mysql);
              rs=ps.executeQuery();
              table.setModel(DbUtils.resultSetToTableModel(rs));        
              

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    
    private void fillData(int id){
         String sql="SELECT * from s_user where id="+id;
                 try{
                     ps=con.prepareStatement(sql);
                     rs=ps.executeQuery();
                     if(rs.next()){
                         txt_username.setText(rs.getString("username"));
                         txt_password.setText(rs.getString("password"));
                         txt_address.setText(rs.getString("address"));
                     }
                 }catch(Exception ex){
                     JOptionPane.showMessageDialog(rootPane, ex);
                 }

     }
    
     
    @Override
	public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Update")){
                 String sql="UPDATE `s_user` SET `username`=?,`password`=?,`address`=? WHERE id="+id;
                 try{
                     ps=con.prepareStatement(sql);
                     ps.setString(1, txt_username.getText());
                     ps.setString(2, txt_password.getText());
                     ps.setString(3, txt_address.getText());
                     //System.out.println(ps.execute());
                     
                     if(ps.execute()){
                         JOptionPane.showMessageDialog(rootPane, "Sorry Can not be update the user");                         
                     }else{
                         JOptionPane.showMessageDialog(rootPane, "Successfully Updated");
                         UpdateTable();
                     }
                 }catch(Exception ex){
                     JOptionPane.showMessageDialog(rootPane, ex);
                 }
            }
           if(e.getActionCommand().equals("Delete")){
                if(id==0){
                    JOptionPane.showMessageDialog(this, "Please Choose the User", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                     int c=JOptionPane.showConfirmDialog(this, "Do you want to delete User","Delete",JOptionPane.YES_NO_OPTION);
                     if(c==0){
                         String query="DELETE FROM `s_user` WHERE id="+id;
                         try {
                             ps=con.prepareStatement(query);
                             if(ps.execute()){
                                 JOptionPane.showMessageDialog(this, "Can not be Deleted Account");
                             }else{
                                 JOptionPane.showMessageDialog(this, "Delete User Successfully");
                                 UpdateTable();
                             }
                         }catch(Exception ex){
                             
                         }
                     }
                 }
           }
               
	}
    
   
    
    public static void main(String args[]){
        new ViewUser();
    }
    
}
