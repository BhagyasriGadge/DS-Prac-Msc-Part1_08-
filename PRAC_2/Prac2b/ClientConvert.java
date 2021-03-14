import java.rmi.*; 
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;

public class ClientConvert
{
    public static void main(String args[]) throws RemoteException
    {
        ClientConvert c = new ClientConvert();
        c.connectRemote();
    }
    private void connectRemote() throws RemoteException
    {
        try
            {
                String s1;
                Registry reg = LocateRegistry.getRegistry("localhost",5678);
                InterConvert h1 = (InterConvert)reg.lookup("DS");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter a number : \t");
				String no = br.readLine();
                s1=h1.convertDigit(no);
                System.out.println(s1);
            } 
		catch(NotBoundException|IOException e)
        {
            System.out.println("exception"+e);
        }
            
    }            
}
