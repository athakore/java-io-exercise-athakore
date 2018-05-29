import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomServer {
	static String fileName = "test.jpg";
	String line = "";
	static String input= "I have an apple!";
	
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(13337)) {
			Socket client = server.accept();
			System.out.println("Connection established!");
			BufferedInputStream clientBuffer = new BufferedInputStream(new DataInputStream(client.getInputStream()));
			try(BufferedOutputStream fileBuffer = new BufferedOutputStream(new FileOutputStream(new File("server" + fileName)))) {
				int i;
				while((i = clientBuffer.read()) != -1) {
					fileBuffer.write(i);
				}	
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
