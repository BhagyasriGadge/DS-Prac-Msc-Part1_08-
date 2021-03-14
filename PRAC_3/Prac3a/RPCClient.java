import java.io.*; 
import java.net.*;
class RPCClient
{
    RPCClient()
    {
        try
        {
            InetAddress ia = InetAddress.getLocalHost(); 
            DatagramSocket dGsoc = new DatagramSocket(); 
            DatagramSocket dGsoc1 = new DatagramSocket(1300); 
            System.out.println("\nRPC Client\n");
            System.out.println("Enter method name from one of four operatons - 1 : add,2 : mul,3 : sub,4 : div and parameter = (Example = add num1 num2)\n");
            while (true)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                byte b[] = str.getBytes(); 
                DatagramPacket dp = new DatagramPacket(b,b.length,ia,1200);
                dGsoc.send(dp);
                dp = new DatagramPacket(b,b.length);
                dGsoc1.receive(dp);
                String s = new String(dp.getData(),0,dp.getLength());
                System.out.println("\nResult = " + s + "\n"); 
				
 
            }
			
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        new RPCClient();
    }
}
