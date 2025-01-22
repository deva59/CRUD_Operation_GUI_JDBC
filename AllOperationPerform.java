import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.cj.jdbc.BlobFromLocator;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class AllOperationPerform extends JFrame{
    static Scanner sc;

    JLabel pid,pname, country;
    JTextField txtpid, txtpname, txtcountry;
    JButton insert, update, delete, rename, exit;


    public AllOperationPerform() {
        getContentPane().setBackground(new Color(128, 128, 128));
        pid = new JLabel("Enter ID ");
        pid.setBounds(0, 54, 246, 43);
        pid.setFont(new Font("Tahoma", Font.BOLD, 10));
        pid.setHorizontalAlignment(SwingConstants.CENTER);
        txtpid = new JTextField(30);
        txtpid.setBounds(279, 54, 294, 43);
        pname = new JLabel("Enter Player Name: ");
        pname.setBounds(0, 126, 246, 43);
        pname.setHorizontalAlignment(SwingConstants.CENTER);
        pname.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtpname = new JTextField(30);
        txtpname.setBounds(279, 126, 294, 43);
        country = new JLabel("Enter Country: ");
        country.setBounds(0, 194, 246, 43);
        country.setFont(new Font("Tahoma", Font.BOLD, 10));
        country.setHorizontalAlignment(SwingConstants.CENTER);
        txtcountry = new JTextField(30);
        txtcountry.setBounds(279, 194, 294, 43);
        insert = new JButton("Insert");

        insert.setBackground(new Color(255, 128, 128));
        insert.setBounds(10, 311, 120, 43);
        update = new JButton("Update");
        update.setBackground(new Color(128, 128, 0));
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateData();
            }

        });
        update.setBounds(398, 311, 120, 43);
        delete = new JButton("delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        delete.setBackground(new Color(128, 255, 128));
        delete.setBounds(138, 311, 120, 43);
        rename = new JButton("Show");
        rename.setBackground(new Color(128, 128, 255));
        rename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchData();
            }

        });
        rename.setBounds(268, 311, 120, 43);
        exit = new JButton("exit");
        exit.setBackground(new Color(255, 128, 0));
        exit.setBounds(528, 311, 120, 43);
        getContentPane().setLayout(null);

        getContentPane().add(pid);
        getContentPane().add(txtpid);
        getContentPane().add(pname);
        getContentPane().add(txtpname);
        getContentPane().add(country);
        getContentPane().add(txtcountry);
        getContentPane().add(insert);
        getContentPane().add(update);
        getContentPane().add(delete);
        getContentPane().add(rename);
        getContentPane().add(exit);

        setSize(666,508);
        setVisible(true);
        setTitle("CRUD OPERATION");

        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });


        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        setVisible(true);
    }


    //	  Insertion Operation Start
    private void insertData() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FIRSTTABLE", "root", "Ekrekha@2001");


            String query = "INSERT INTO player (pid, pname, country) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);


            int playerId = Integer.parseInt(txtpid.getText());
            String playerName = txtpname.getText();
            String playerCountry = txtcountry.getText();

            pstmt.setInt(1, playerId);
            pstmt.setString(2, playerName);
            pstmt.setString(3, playerCountry);


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Record inserted successfully!");
            }






            pstmt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    //	    Delete Operation Start
    private void deleteData() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FIRSTTABLE", "root", "Ekrekha@2001");


            String query = "delete from player where pid = ?" ;
            PreparedStatement pstmt = con.prepareStatement(query);


            int playerId = Integer.parseInt(txtpid.getText());



            pstmt.setInt(1, playerId);



            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
            }


            pstmt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



    //	    Fetch The Data
    private void fetchData() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FIRSTTABLE", "root", "Ekrekha@2001");


            String query = "SELECT pname, country FROM player WHERE pid = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            int playerId = Integer.parseInt(txtpid.getText());
            pstmt.setInt(1, playerId);


            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                String playerName = rs.getString("pname");
                String playerCountry = rs.getString("country");

                txtpname.setText(playerName);
                txtcountry.setText(playerCountry);

                JOptionPane.showMessageDialog(this, "Data fetched successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No record found for the given ID.");
            }


            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    //	    Update the Data
    private void updateData() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FIRSTTABLE", "root", "Ekrekha@2001");

            String query = "UPDATE player SET pname = ?, country = ? WHERE pid = ?";


            PreparedStatement pstmt = con.prepareStatement(query);


            String playerName = txtpname.getText();
            String playerCountry = txtcountry.getText();
            int playerId = Integer.parseInt(txtpid.getText());


            pstmt.setString(1, playerName);
            pstmt.setString(2, playerCountry);
            pstmt.setInt(3, playerId);


            int rowsUpdate = pstmt.executeUpdate();

            if (rowsUpdate > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with the given ID.");
            }


            pstmt.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }




}
