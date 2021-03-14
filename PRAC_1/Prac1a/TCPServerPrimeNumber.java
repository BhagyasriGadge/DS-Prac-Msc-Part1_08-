import java.net.*;
import java.io.*;

public class TCPServerPrimeNumber{
	public static void main(String[] Args){
		try{
			ServerSocket ss=new ServerSocket(8001);
			System.out.println("Server Started");
			Socket s=ss.accept();
			DataInputStream in=new DataInputStream(s.getInputStream());
			int x=in.readInt();
			DataOutputStream otc=new DataOutputStream(s.getOutputStream());
			int y=x/2;
			if(x==1){
				otc.writeUTF(x+" is neither Prime nor composite");
				System.exit(0);
			}
			if(x==2 || x==3){
				otc.writeUTF(x+" is Prime Number");
				System.exit(0);
		
			}
			for(int i = 2; i <=y ;i++){
				if(x % i != 0){
					otc.writeUTF(x+" is Prime Number");
				}
				else{
					otc.writeUTF(x+" is not Prime Number");
				}
			}
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}