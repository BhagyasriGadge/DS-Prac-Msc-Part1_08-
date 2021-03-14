import java.net.*;
import java.io.*;

public class TCPServerChat{
	public static void main(String[] Args){
		try{
			ServerSocket ss=new ServerSocket(8001);
			System.out.println("Server is ready to Chat");
			Socket s=ss.accept();
			BufferedReader infu=new BufferedReader(new InputStreamReader(System.in));
			DataInputStream in=new DataInputStream(s.getInputStream());
			DataOutputStream ot=new DataOutputStream(s.getOutputStream());
			String receive;
			String send;
			while((receive = in.readLine()) !=null ){
				if(receive.equals("STOP") || receive.equals("Stop") || receive.equals("stop")){
					break;
				}
				System.out.println("Client says: "+receive);
				System.out.println("Server says :)");
				send = infu.readLine();
				ot.writeBytes(send+"\n");
			}
			infu.close();
			in.close();
			ot.close();
		}catch(Exception e){
				System.out.println(e.toString());
		}
	}
}