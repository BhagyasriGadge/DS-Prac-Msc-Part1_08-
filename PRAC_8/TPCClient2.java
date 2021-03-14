import java.io.*; 
import java.net.*;

class TPCClient2
{
	public static void main(String agrs[]) throws Exception
	{
		InetAddress ia;
		ia = InetAddress.getLocalHost();
		Client c = new Client(ia);
		
		//Sending data to client 2
		c.setSendPort(9003); //recport 8002
		c.setRecPort(8002); //sendport 9003
		c.recData();
		c.sendData();
		c.recData();
	}
}
class Client{
	InetAddress ia;
	int sendPort,recPort;
	Client(InetAddress ia){
		this.ia = ia;
	}
	public void setSendPort(int sendPort){
		this.sendPort = sendPort;
	}
	
	public void setRecPort(int recPort){
		this.recPort = recPort;
	}
	
	public void sendData() throws Exception{
		BufferedReader br;
		DatagramSocket ds;
		DatagramPacket dp;
		String data="";
		
		System.out.println("Enter the Response 'VOTE_COMMIT' || 'VOTE_ABORT' ");
		br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine();
		System.out.println("Data is "+data);
		
		ds = new DatagramSocket(sendPort);
		dp = new DatagramPacket(data.getBytes(),data.length(),ia,sendPort-1000);
		ds.send(dp);
		ds.close();
	}
	
	public void recData() throws Exception{
		byte buf[] = new byte[256];
		DatagramPacket dp;
		DatagramSocket ds;
		
		ds=new DatagramSocket(recPort);
		dp=new DatagramPacket(buf,buf.length);
		ds.receive(dp);
		ds.close();
		String msgStr=new String(dp.getData(),0,dp.getLength());
		System.out.println(msgStr);
	}
}