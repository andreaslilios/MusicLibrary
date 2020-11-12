
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicLibrary extends UnicastRemoteObject implements Operations {

    
    private static Operations look_op;
    private static Operations2 look_op2;
    private static ArrayList<String> records = new ArrayList<String>();

    public MusicLibrary() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {

            //ΕΔΩ ΕΧΟΥΜΕ ΤΙΣ ΠΟΛΙΤΙΚΕΣ ΑΣΦΑΛΕΙΑΣ ΟΙ ΟΠΟΙΕΣ ΔΕΝ ΜΑΣ ΕΠΙΤΡΕΠΟΥΝ ΤΗΝ ΕΚΚΙΝΗΣΗ ΤΗΣ ΕΦΑΡΜΟΓΗΣ
//            System.setProperty("java.security.policy", "file:./security.policy");
//
//            if (System.getSecurityManager() == null) {
//                System.setSecurityManager(new SecurityManager());
//            }
            String url1 = "//localhost/MusicLibrary";

            // Δημιουργια Server 
            MusicLibrary server = new MusicLibrary();
            Registry r = LocateRegistry.createRegistry(1234);
            r.rebind(url1, server);
            System.out.println("MusicLibrary is ok!");

            try {
                //Δημιουργια Συνδεσης με DataBase
                Registry regi = LocateRegistry.getRegistry("127.0.0.1", 12345);
                look_op2 = (Operations2) regi.lookup("//localhost/DataBase");
                System.out.println("OK CLIENT");

            } catch (NotBoundException ex) {
                Logger.getLogger(MusicLibrary.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(MusicLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(MusicLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> display() throws RemoteException {
        // συνδεση μεσω αντικειμενου διεπαφης Operation2 για επικυρωση
        ArrayList<String> result = look_op2.display2();
        
        return result;
    }

    public String AddAlbum(String description, String genre, String year, String song) throws RemoteException {
        // συνδεση μεσω αντικειμενου διεπαφης Operation2 για καταθεση
        String result = look_op2.AddAlbum2(description,genre,year,song);
        

        return result;
    }

    public String update(String des) throws RemoteException {
        // συνδεση μεσω αντικειμενου διεπαφης Operation2 για αναληψη
        String news = look_op2.update2(des);
        return news;
    }

    public int delete(String des) throws RemoteException {
        // συνδεση μεσω αντικειμενου διεπαφης Operation2 για ενημερωση
        int result = look_op2.delete2(des);
        return result;
    }
}
