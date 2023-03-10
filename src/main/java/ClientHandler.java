import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                System.out.println("Client: " + input);
                out.println("Server: " + input);
            }
        } catch (IOException e) {
            System.out.println("Error in client handler: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error while closing socket: " + e.getMessage());
            }
        }
    }
}

