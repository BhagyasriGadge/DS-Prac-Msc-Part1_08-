import java.rmi.*; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.HashMap; 
import java.util.Map; 

public class ServerConvert extends UnicastRemoteObject implements InterConvert{
	
	public static Map<Integer, String> asciiNumbers;
	static{
		asciiNumbers = new HashMap<>();
		asciiNumbers.put(48, "zero");
		asciiNumbers.put(49, "one");
		asciiNumbers.put(50, "two");
		asciiNumbers.put(51, "three");
		asciiNumbers.put(52, "four");
		asciiNumbers.put(53, "five");
		asciiNumbers.put(54, "six");
		asciiNumbers.put(55, "seven");
		asciiNumbers.put(56, "eight");
		asciiNumbers.put(57, "nine");
	}
    
    public ServerConvert() throws RemoteException
	{
	 }
    public String convertDigit(String number) throws RemoteException
    {
        String result = "";
        for(int i = 0; i < number.length() ; i++){
			int digitAscii = number.charAt(i);
			if(asciiNumbers.containsKey(digitAscii)){
				result += asciiNumbers.get(digitAscii) + " ";
			}
		}
        return result;
    }
    
    public static void main(String args[]) throws RemoteException
    {
        try
        {
          ServerConvert s1= new ServerConvert();
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