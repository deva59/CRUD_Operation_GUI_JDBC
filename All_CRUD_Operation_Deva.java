
import java.sql.*;
import java.util.Scanner;

public class All_CRUD_Operation_Deva {
    static Scanner sc;

    public static void main(String[] args) {
        int Option = 0;
        sc = new Scanner(System.in);

        do {
            System.out.println("1.Create Database");
            System.out.println("2.Insert Data");
            System.out.println("3.Show Data");
            System.out.println("4.Update Data");
            System.out.println("5.Delete Data");
            System.out.println("6.Exit");
            System.out.println("Choose Your Option");

            Option = sc.nextInt();

            switch (Option) {
                case 1:
                    createTable();
                    break;
                case 2:
                    insertData();
                    break;
                case 3:
                    showData();
                    break;
                case 4:
                    updateData();
                    break;
                case 5:
                    deleteData();
                    break;
                case 6:
                    System.out.println("Exiting... Thank You!");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (Option != 6);

    }

    //------------------------------------------- CREATE TABLE ---------------------------------------------------------
    private static void createTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic", "root", "Ekrekha@2001");
            Statement st = connection.createStatement();
            String Query = "Create table DsClinic(Did int primary key,Dname varchar(40),Dmbno int not null,city varchar(50))";
            st.executeUpdate(Query);
               System.out.println("success");
        } catch (Exception e) {
            System.out.println("I am in exception");
        }
    }


    //-----------------------------------------------INSERT DATA --------------------------------------------------------

    private static void insertData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic", "root", "Ekrekha@2001");
            String Query = "insert into DsClinic values(?,?,?,?)";
            PreparedStatement pp = connection.prepareStatement(Query);

            System.out.println("Enter The Doctor Id: ");
            int Did = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.println("Enter The Doctor Name: ");
            String Dname = sc.nextLine(); // use nextLine() for full name

            System.out.println("Enter The Doctor Mobile: ");
            int Dmbno = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.println("Enter The Doctor City: ");
            String city = sc.nextLine(); // use nextLine() for full city name

            pp.setInt(1, Did);
            pp.setString(2, Dname);
            pp.setInt(3, Dmbno);
            pp.setString(4, city);

            int result = pp.executeUpdate();

            if (result > 0) {
                System.out.println("Record Inserted Successfully");
            } else {
                System.out.println("Record Insertion Failed");
            }
        } catch (Exception e) {
            e.printStackTrace(); // show actual error
        }
    }

    //--------------------------------------------- SHOW DATA ----------------------------------------------------------

    private static void showData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic", "root", "Ekrekha@2001");
            PreparedStatement ps = connection.prepareStatement("Select * from DsClinic");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("Did") + " " + rs.getString("Dname") + " " + rs.getInt("Dmbno") + " " + rs.getString("city"));
            }
        } catch (Exception e) {
        }
    }


    //----------------------------------------------- Update Data ------------------------------------------------------

    private static void updateData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic", "root", "Ekrekha@2001");
            String Query = "update DsClinic set Dname=?, Dmbno=?, city=? where Did=?";
            PreparedStatement ps = connection.prepareStatement(Query);

            System.out.println("Enter Doctor Id to Update: ");
            int Did = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.println("Enter Doctor name to Update: ");
            String Dname = sc.nextLine();

            System.out.println("Enter Doctor Mobile to Update: ");
            int Dmbno = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            System.out.println("Enter Doctor City to Update: ");
            String city = sc.nextLine();

            ps.setString(1, Dname);
            ps.setInt(2, Dmbno);
            ps.setString(3, city);
            ps.setInt(4, Did); // important

            int x = ps.executeUpdate();

            System.out.println(x + " Record(s) Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //--------------------------  Delete Data --------------------------------------------------------------------------

   private static void deleteData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clinic", "root", "Ekrekha@2001");
            PreparedStatement ps = connection.prepareStatement("Delete from DsClinic where Did=?");
            System.out.println("Enter Doctor Id for Delete Data ");
            int Did = sc.nextInt();

            ps.setInt(1, Did);
            ps.executeUpdate();
            System.out.println("Delete The Query");
        }
        catch (Exception e){

    }
    }



}