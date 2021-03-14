import java.net.*; 
import java.io.*; 
public class TPCServer
{
    public static void main(String agrs[]) throws Exception
	{
		BufferedReader br;
		InetAddress ia;
		ia = InetAddress.getLocalHost();
		Server s = new Server(ia);
		System.out.println("Server in sending mode...");
		
		//Sending data to client1
		s.setSendPort(9000); //recport 8000
		s.setRecPort(8001); //recport 9001
		
		System.out.println("Send request data to client1..");
		br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println("Data is "+str);
		s.sendData();
		System.out.println("Waiting for response from client1...");
		s.recData();
		
		//Sending data to client2
		s.setSendPort(9002); //recport 8002
		s.setRecPort(8003); //sendport 9003
		
		System.out.println("Send request data to client2..");
		br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		System.out.println("Data is "+s1);
		s.sendData();
		System.out.println("Waiting for response from client2...");
		s.recData();
		
		//Sending the final result to client1
		s.setSendPort(9000);
		s.sendData();
		
		//Sending the final result to client2
		s.setSendPort(9002);
		s.sendData();
    }
}

class Server{
	InetAddress ia;
	int sendPort,recPort;
	int ssend = 0;
	int scounter = 0;
	Server(InetAddress ia){
		this.ia = ia;
	}
	
	public void setSendPort(int sendPort){
		this.sendPort = sendPort;
	}
	
	public void setRecPort(int recPort){
		this.recPort = recPort;
	}
	
	public void sendData() throws Exception{
		DatagramSocket ds;
		DatagramPacket dp;
		String data="";
		
		if(scounter<2 && ssend<2){
			data="VOTE_REQUEST";
		}
		
		if(scounter<2 && ssend>1){
			data="GLOBAL_ABORT ";
			data=data + "TRANSACTION ABORTED";
		}
		
		if(scounter==2 && ssend>1){
			data="GLOBAL_COMMIT ";
			data=data + "TRANSACTION COMMITTED";
		}
		
		ds = new DatagramSocket(sendPort);
		dp = new DatagramPacket(data.getBytes(),data.length(),ia,sendPort-1000);
		ds.send(dp);
		ds.close();
		ssend++;
	}
	
	public void recData() throws Exception{
		byte buf[] = new byte[256];
		DatagramPacket dp = null;
		DatagramSocket ds = null;
		String msgStr="";
		try{
			ds=new DatagramSocket(recPort);
			dp=new DatagramPacket(buf,buf.length);
			ds.receive(dp);
			ds.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		msgStr=new String(dp.getData(),0,dp.getLength());
		System.out.println("String = "+msgStr);
		if(msgStr.equalsIgnoreCase("VOTE_COMMIT")){
			scounter++;
		}
		System.out.println("Counter value= "+scounter+" n Send value= "+ssend);
	}
}