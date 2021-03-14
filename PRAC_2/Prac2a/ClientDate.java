import java.rmi.*; 
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class ClientDate
{
    public static void main(String args[]) throws RemoteException
    {
        ClientDate c = new ClientDate();
        c.connectRemote();
    }
    private void connectRemote() throws RemoteException
    {
        try
            {
                String s1;
                Registry reg = LocateRegistry.getRegistry("localhost",5678);
                InterDate h1 = (InterDate)reg.lookup("DS");
                s1=h1.display();
                System.out.println(s1);
            } 
		catch(NotBoundException|RemoteException e)
        {
            System.out.println("exception"+e);
        }
            
    }            
}
