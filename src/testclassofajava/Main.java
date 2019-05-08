/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testclassofajava;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main extends JFrame  {

     private static Connection con=null;
     PreparedStatement ps;
     ResultSet rs;
     JMenuBar menuBar;
     JMenu file,edit,user,help;
     JMenuItem add,view;
     JPanel p=new JPanel();
     
    Main(){
        
        p.setBounds(5,5,1200,600);
        p.setBackground(Color.blue);
        add(p);
        
        JLabel l=new JLabel("Welcome Ran Bahadur kc");
        l.setForeground(Color.white);
        l.setFont(new Font("Courier New", Font.BOLD, 30));
        p.add(l);
        
        
        con=DbConnect.DbConnect();
        init();
        setSize(1200,600);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void init(){
        
        setTitle("Dasboard");
        file=new JMenu("File");
        edit=new JMenu("Edit");
        user=new JMenu("User");
        add=new JMenuItem("Add");
        view=new JMenuItem("View");
        help=new JMenu("Help");
        menuBar = new JMenuBar();
        
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(user);
        user.add(add);
        user.add(view);
        menuBar.add(help);
        setJMenuBar(menuBar);
        
        add.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
               UserAdd user=new UserAdd();
               user.setVisible(true);
        }
        });
        
        view.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
              ViewUser user=new ViewUser();
              user.setVisible(true);
        }
        });
       
        
       
                
    }

   
    public static void main(String args[]){
        new Main();
    }
    
}
