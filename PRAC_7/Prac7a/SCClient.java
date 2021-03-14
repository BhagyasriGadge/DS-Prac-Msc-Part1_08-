import java.net.*;
import java.io.*;
import java.util.Date;

public class SCClient{
	public static void main(String[] args){
	try{
		Socket cs = new Socket("LocalHost",8001);
		BufferedReader infu = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream ot = new DataOutputStream(cs.getOutputStream());
		String send;
		System.out.println("Type a message to send into sever");
		while((send = infu.readLine()) != null){
			Date date = new Date();
			long timeMilli = date.getTime();
			String t=String.valueOf(timeMilli);
			ot.writeBytes(send+","+t+"\n");
			if(send.equals("stop") || send.equals("STOP") || send.equals("Stop")){
				break;
			}
		}
		infu.close();
		ot.close();
		cs.close();
	}
	catch(Exception e){
		System.out.println(e.toString());
	}
}
}