
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface Operations extends Remote{
    
   public ArrayList<String> display() throws RemoteException;
    public String AddAlbum(String description, String genre, String year, String song) throws RemoteException;
    public String update(String des) throws RemoteException;
    public int delete(String description) throws RemoteException;
    
}