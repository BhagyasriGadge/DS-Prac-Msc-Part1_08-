import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SCServer {
	public static void main(String arg[]){
		try{
			ServerSocket ss=new ServerSocket(8001);
			System.out.println("Server is accept message....");
			Socket s=ss.accept();
			
			DataInputStream in=new DataInputStream(s.getInputStream());
			String receive;
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss.SSSS");
			
			while((receive = in.readLine()) != null){
				String[] message = receive.split(",");
				
				if(message[0].equals("stop") || message[0].equals("STOP") || message[0].equals("Stop")){
					break;
				}
				Date date = new Date();
				
				Long clienttime = Long.parseLong(message[1]);
				Long timeMilli = date.getTime();
				Long requiretime = timeMilli - clienttime;
				System.out.println("This is the message:" + message[0]);
				System.out.println("Server will not acccept the message if its taken more than 2 millisecond.");
				
				if(clienttime.equals(timeMilli)){
					String strDate = dateFormat.format(timeMilli);
					System.out.println("Message sending time and receiving time is same:" + strDate);
				}
				else if(requiretime > 2){
					String clienttim = dateFormat.format(clienttime);
					System.out.println("Messange sending time from client:"+ clienttim+"\n");
					String strDate = dateFormat.format(timeMilli);
					System.out.println("Messange received from client to:"+strDate+"\n");
					System.out.println("This message is rejected.");
				}
				else{
					String clienttim = dateFormat.format(clienttime);
					System.out.println("Messange sending time from client:"+ clienttim+"\n");
					String strDate = dateFormat.format(timeMilli);
					System.out.println("Messange received from client to:"+strDate+"\n");
					System.out.println("This message is accepted.");
				}
			}
			in.close();
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}