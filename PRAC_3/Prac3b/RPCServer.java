import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

final class RPCServer
{
    DatagramSocket dGsoc; 
    DatagramPacket dGpac;
    String str,methodName,result;
    int val;
    RPCServer()
    {
        try
        {
            dGsoc=new DatagramSocket(1200); 
            byte b[]=new byte[4096];
            while(true)
            {
                dGpac=new DatagramPacket(b,b.length);
                dGsoc.receive(dGpac);
                str=new String(dGpac.getData(),0,dGpac.getLength());
                if(str.equalsIgnoreCase("q"))
                { 
                    System.exit(1); 
                }
                else
                { 

                    StringTokenizer st = new StringTokenizer(str," "); 
		
                    int i=0;
                    
                    while(st.hasMoreTokens())
                    {
                        String token=st.nextToken();
                        methodName=token;
                        val = Integer.parseInt(st.nextToken());
                    }
                }
                System.out.println(str);
                
                InetAddress ia = InetAddress.getLocalHost();
                if(methodName.equalsIgnoreCase("square"))
                {
                    result= "" + square(val);
                }
                else if(methodName.equalsIgnoreCase("squareRoot"))
                {
                    result= "" + squareRoot(val);
                }
                else if(methodName.equalsIgnoreCase("cube"))
                {
                    result= "" + cube(val);
                }
                else if(methodName.equalsIgnoreCase("cubeRoot"))
                {
                    result= "" + cubeRoot(val);
                }
                byte b1[]=result.getBytes();
            
                DatagramSocket dGsoc1 = new DatagramSocket();
                
                DatagramPacket dGpac1 = new DatagramPacket(b1,b1.length,InetAddress.getLocalHost(), 1300);
                
                System.out.println("result : "+result+"\n"); dGsoc1.send(dGpac1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public int square(int val)
    {
        return val*val; 
    }
    public double squareRoot(int val)
    {
		float temp, sqrt;
		sqrt = val / 2;
		temp = 0;

		while(sqrt != temp){
        temp = sqrt;
        sqrt = ( val/temp + temp) / 2;
		}
		return sqrt;
    }
    public double cube(int val)
    {
        return Math.pow(val,3);
    }
    public double cubeRoot(int val)
    {
        return Math.pow(val, 1.0/3.0);
    }
    
    public static void main(String[] args)
    {
        new RPCServer();
    }
}
