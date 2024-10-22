import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface Client {
    public static void main(String[] args) {
        try{
            Console con = System.console();
            Socket socket = new Socket("localhost", 12345);
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            String input = con.readLine("Enter: ");
            dos.writeUTF(input);
            dos.flush();

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String message = dis.readUTF();
            System.out.println(message);
            dis.close();
            dos.close();
            socket.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
