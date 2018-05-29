import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		InetAddress ip;
		String fileName = "";
		String line = "";
//		try {
//			ip = InetAddress.getLocalHost();
//			System.out.println("Current IP address : " + ip.getHostAddress());
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		try (ServerSocket server = new ServerSocket(13337)) {
			Socket client = server.accept();
			System.out.println("Docking complete.");
//			DataInputStream clientInput = new DataInputStream(client.getInputStream());
			BufferedReader clientBuffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			fileName = clientBuffer.readLine();
			File f = new File(fileName +".txt");
			if(f.exists())
				System.out.println(fileName +".txt already exists!");
			else
				createFile(fileName);
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(f));
			while((line = clientBuffer.readLine()) != null) {
				fileWriter.write(line);
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void createFile(String f) {
		try {
			File file = new File(f + ".txt");
			if(file.createNewFile())
				System.out.println("File " + f +".txt was created!");
			else
				System.out.println("Gosh daag it, nate!");
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

}
