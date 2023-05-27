using System.Text;
using System.Net.Sockets;
using System.Net;

Socket s = new Socket(AddressFamily.InterNetwork,SocketType.Dgram,ProtocolType.Udp);
s.Bind(new IPEndPoint(IPAddress.Parse("0.0.0.0"),40000));
byte[] buf = new byte[1024];

IPEndPoint ipe = new IPEndPoint(IPAddress.Any,60000);
EndPoint ep = (EndPoint)ipe;

while (true){
    s.Listen();
    Socket rs = s.Accept();
    int size = rs.ReceiveFrom(buf,ref ep);
    if (size>0){
    string r = Encoding.UTF8.GetString(buf,0,size);
        if (!string.IsNullOrEmpty(r)){
            Console.WriteLine();
        }
    }
    }

}