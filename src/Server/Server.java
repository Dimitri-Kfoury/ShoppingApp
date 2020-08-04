package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port = 5432;
    private final int POOL_SIZE = 10;
    private ExecutorService executorService;
    protected Map<String,Integer> activeSessions;


    public Server()
    {
        try {
            ServerSocket ss = new ServerSocket(port);
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                    .availableProcessors() * POOL_SIZE);
            System.out.println("Waiting for client to connect");

            while(true)
            {
                Socket tcpConn = ss.accept();
                executorService.execute(new ServerSession(tcpConn));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
