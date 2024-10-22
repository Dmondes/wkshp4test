import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static void main(String[] args) {
    
    try {
        ServerSocket server = new ServerSocket(12345);
        Console con = System.console();
        System.out.println("Server started, awaiting connection ...");
        Socket socket = server.accept();
        System.out.println("Connection established ... Awaiting message from client");
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String message = dis.readUTF();
        System.out.println(message);
        

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        String input = con.readLine("Reply: ");
        dos.writeUTF(input);
        dos.flush();
        dos.close();
        dis.close();
        socket.close();
        server.close();


    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}