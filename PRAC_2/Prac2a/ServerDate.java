import java.rmi.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.*;

public class ServerDate extends UnicastRemoteObject implements InterDate{
    
    public ServerDate() throws RemoteException
	{
	 }
    public String display() throws RemoteException
    {
        String str = "";
        Date d = new Date();
        str = d.toString();
        return str;
    }
    
    public static void main(String args[]) throws RemoteException
    {
        try
        {
          ServerDate s1= new ServerDate();
          Registry reg = LocateRegistry.createRegistry(5678);
          reg.rebind("DS",s1);
          System.out.println("Object registed....");
        }
        catch(RemoteException e)
        {
          System.out.println("exception"+e);
        }
    }    
}
