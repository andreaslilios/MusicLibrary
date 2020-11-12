
import java.beans.Statement;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

public class DataBase extends UnicastRemoteObject implements Operations2 {

    private static String descr;
    private static String genr;
    private static String yearr;
    private static String sng;
    private static ResultSet albums;
    private static ResultSet songs;
    private static int columnsNumber;
    private static int columnsNumber2;
    private static ArrayList<String> descList = new ArrayList<String>();
    private static ArrayList<String> genList = new ArrayList<String>();
    private static ArrayList<String> yearList = new ArrayList<String>();
    private static ArrayList<String> songList = new ArrayList<String>();

    public DataBase() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {

            String url1 = "//localhost/DataBase";
            //ΕΔΩ ΕΧΟΥΜΕ ΤΙΣ ΠΟΛΙΤΙΚΕΣ ΑΣΦΑΛΕΙΑΣ ΟΙ ΟΠΟΙΕΣ ΔΕΝ ΜΑΣ ΕΠΙΤΡΕΠΟΥΝ ΤΗΝ ΕΚΚΙΝΗΣΗ ΤΗΣ ΕΦΑΡΜΟΓΗΣ

//            System.setProperty("java.security.policy", "file:./security.policy");
//
//            if (System.getSecurityManager() == null) {
//                System.setSecurityManager(new SecurityManager());
//            }
            DataBase server = new DataBase();
            Registry r = LocateRegistry.createRegistry(12345);
            r.rebind(url1, server);
            System.out.println("DataBase is ok!");

            String url = "jdbc:sqlite:C:/sqlite/test.db";

            Class.forName("org.sqlite.JDBC");
//sundesi me tin vasi dedomenon kai dimiourgia vasis se periptosi pou den uparxei
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            java.sql.Statement stat = conn.createStatement();
            System.out.println("Database connection established");
            // diagrafi pinaka 'people' se periptosi pou iparxei
            stat.executeUpdate("DROP table if exists ML;");
            stat.executeUpdate("DROP table if exists SONG;");

//dimiougia pinaka me 4 stiles
            stat.executeUpdate("CREATE table ML (descr String private key, genre String, year String, songs String);");
            stat.executeUpdate("INSERT INTO ML (descr, genre, year, songs) VALUES ('oi kyriakes','laika','2004','mana synnefa zwh')");
            stat.executeUpdate("INSERT INTO ML (descr, genre, year, songs) VALUES ('red','pop','2014','memory let me now')");

            stat.executeUpdate("CREATE table SONG (title String private key, singer String, duration String);");
            stat.executeUpdate("INSERT INTO SONG (title, singer, duration) VALUES ('mana','mitropanos', '3')");
            stat.executeUpdate("INSERT INTO SONG (title, singer, duration) VALUES ('zwh','parios','4')");

            albums = stat.executeQuery("SELECT * from ML");
//            songs = stat.executeQuery("SELECT * from SONG");
            ResultSetMetaData rsmd = albums.getMetaData();
            columnsNumber = rsmd.getColumnCount();
//            ResultSetMetaData rsmd2 = songs.getMetaData();
//            columnsNumber2 = rsmd2.getColumnCount();
            while (albums.next()) {
//Print one row
                for (int i = 1; i <= columnsNumber; i++) {

                    System.out.print(albums.getString(i) + " "); //Print one element of a row
                }
                //παιρνω μια-μια τις εγγραφες και τις εμφανιζω 
                descr = albums.getString(1);
                genr = albums.getString(2);
                yearr = albums.getString(3);
                System.out.println(descr + genr + yearr + songList);
// φτιαχνω 4 λιστες για τα 4 πεδια του πινακα (μου χρησιμευουν σε παρακατω ελεγχους)
                descList.add(descr);
                genList.add(genr);
                yearList.add(yearr);
                songList.add(albums.getString(4));
                System.out.println();//Move to the next line to print the next row.
            }
            DataBase server2 = new DataBase();

            //conn.close();
        } catch (RemoteException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Τρέχουμε στην βάση, εαν υπάρχει ο πελάτης που μας έκανε αίτημα, του επιστρέφουμε τον αριθμό λογαριασμού του.

    public ArrayList<String> display2() throws RemoteException {
        String count = null;
        String row = null;
        ArrayList<String> provoli = new ArrayList<String>();
        provoli.add("");
        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            java.sql.Statement stat = conn.createStatement();
             albums = stat.executeQuery("SELECT * from ML");
//            songs = stat.executeQuery("SELECT * from SONG");
            ResultSetMetaData rsmd = albums.getMetaData();
            columnsNumber = rsmd.getColumnCount();
//            ResultSetMetaData rsmd2 = songs.getMetaData();
//            columnsNumber2 = rsmd2.getColumnCount();
            while (albums.next()) {
                row= albums.getString(1)+"/"+albums.getString(2)+"/"+albums.getString(3)+ "/" + albums.getString(4)+"\n";
                //παιρνω μια-μια τις εγγραφες και τις εμφανιζω 
                provoli.add(row);
                
                
//            ResultSet albums = stat.executeQuery("SELECT * from ML");
//            ResultSet rows = stat.executeQuery("SELECT COUNT(*) from ML");
//            String lastrow = rows.getString(1);
//            int size = Integer.parseInt(lastrow);
//
//            ResultSetMetaData rsmd = albums.getMetaData();
//            columnsNumber = rsmd.getColumnCount();
//            for(int j=0;j<size; j++) {
////Print one row
//                for (int i = 1; i <= columnsNumber; i++) {
//
//                    row = albums.getString(i) + " "; //Print one element of a row
//                    System.out.println(row);
//                    System.out.print(albums.getString(i) + " ");
//                }
//
//                provoli.add(row);
//                System.out.println(provoli);
//
//               // return provoli;
//
//            }
        } 
        
    }   catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return provoli;
        }
    // Αφού γίνει η αναζήτηση του λογαριασμού του συγκεκριμένου πελάτη, 
    //προσθέτουμε στο υπόλοιπο το ποσό που κατέθεσε ο πελάτης.Επιστρέφουμε ανάλογο μήνυμα.        // Αφού γίνει η αναζήτηση του λογαριασμού του συγκεκριμένου πελάτη, 
    //προσθέτουμε στο υπόλοιπο το ποσό που κατέθεσε ο πελάτης.Επιστρέφουμε ανάλογο μήνυμα.

    public synchronized String AddAlbum2(String description, String genre, String year, String song) throws RemoteException {

        String minima = "no";
        Connection conn;
        descList.add(description);
        genList.add(genre);
        yearList.add(year);
        songList.add(song);

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            java.sql.Statement stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO ML (descr, genre, year, songs) VALUES "
                    + "('" + description + "','" + genre + "', '" + year + "','" + song + "')");

            //java.sql.Statement stat = conn.createStatement();
            ResultSet ee = stat.executeQuery("SELECT * from ML WHERE descr='" + description + "'");
            System.out.println(ee.getString(1) + ee.getString(2) + ee.getString(3) + ee.getString(4));
            minima = ee.getString(1) + ",\t" + ee.getString(2) + ",\t" + ee.getString(3) + ",\t" + ee.getString(4);
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return minima;
    }

    //Αφού γίνει η αναζήτηση του λογαριασμού του συγκεκριμένου πελάτη, ελέγχουμε εαν μπορεί να γίνει ανάληψη ποσού, 
    //αν ναι αφαιρούμε απο το υπόλοιπο το ποσό.Και στις δύο περιπτώσεις επιστρέφουμε το ανάλογο μήνυμα.
    public String update2(String des) throws RemoteException {

        String minima = "no";

        return minima;

    }

    //Αναζητούμε το λογαριασμό του πελάτη και επιστρέφουμε μήνυμα με το υπόλοιπο του λογαριασμού του.
    public synchronized int delete2(String des) throws RemoteException {

        Connection conn;
        int result = 0;

        for (int i = 0; i < descList.size(); i++) {
            if (des.equalsIgnoreCase(descList.get(i))) {

                try {
                    conn = DriverManager.getConnection("jdbc:sqlite:test.db");
                    java.sql.Statement stat = conn.createStatement();
                    stat.executeUpdate("DELETE from ML  WHERE descr ='" + des + "'");
                    result = 1;
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return result;

    }

}
