import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class CustomClient {
	static String input = "I have a pen!";
	static String fileName = "test.jpg";
	
	public static void main(String[] args) {
		try (Socket server = new Socket("localhost", 13337)) {
			BufferedOutputStream serverBuffer = new BufferedOutputStream(new DataOutputStream(server.getOutputStream()));
			try(BufferedInputStream fileBuffer = new BufferedInputStream(new FileInputStream(new File(fileName)))) {
				int i;
				while((i = fileBuffer.read()) != -1) {
					serverBuffer.write(i);
				}
				serverBuffer.flush();
			} catch(IOException e) {
				e.printStackTrace();
			}
//			serverBuffer.write(input.getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
