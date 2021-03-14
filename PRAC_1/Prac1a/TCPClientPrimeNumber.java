import java.net.*;
import java.io.*;

public class TCPClientPrimeNumber{
	public static void main(String[] Args){
		try{
			Socket cs=new Socket("Localhost",8001);
			BufferedReader infu = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("enter a number :");
			int a = Integer.parseInt(infu.readLine());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			out.writeInt(a);
			DataInputStream in = new DataInputStream(cs.getInputStream());
			System.out.println(in.readUTF());
			cs.close();
		}
		catch(Exception e){
		System.out.println(e.toString());	
		}
	}
}