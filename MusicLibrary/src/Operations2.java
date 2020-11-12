
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface Operations2 extends Remote {
    public ArrayList<String> display2() throws RemoteException;

    public String AddAlbum2(String description, String genre, String year, String song) throws RemoteException;

    public String update2(String des) throws RemoteException;

    public int delete2(String description) throws RemoteException;
    
}
