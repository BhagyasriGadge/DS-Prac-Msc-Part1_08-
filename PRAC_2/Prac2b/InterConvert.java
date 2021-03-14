import java.rmi.*; 
public interface InterConvert extends Remote
{
	public String convertDigit(String number) throws RemoteException;
} 