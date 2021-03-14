import java.net.*;
import java.io.*;

public class TCPClientChat{
	public static void main(String[] Args){
		try{
			Socket cs=new Socket("Localhost",8001);
			BufferedReader infu=new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream ot=new DataOutputStream(cs.getOutputStream());
			DataInputStream in=new DataInputStream(cs.getInputStream());
			String send;
			System.out.println("Type STOP/Stop/stop if want to close chat!!");
			System.out.println("Client says :)");
			while((send = infu.readLine()) !=null ){
				ot.writeBytes(send+"\n");
				if(send.equals("STOP") || send.equals("Stop") || send.equals("stop")){
					break;
				}
				System.out.println("Server says: "+in.readLine());
				System.out.println("Client says :)");
				}
			infu.close();
			in.close();
			ot.close();
			cs.close();
		}catch(Exception e){
				System.out.println(e.toString());
		}
	}
}
				
			