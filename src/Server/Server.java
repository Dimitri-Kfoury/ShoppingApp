package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static Map<Integer, String> activeSessions;


    public Server() {
        try {
            int port = 8888;
            ServerSocket ss = new ServerSocket(port);
            int POOL_SIZE = 10;
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
            System.out.println("Waiting for client to connect");

            while (true) {
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

    public static void removeActiveSession(Integer sessionId) {
        activeSessions.remove(sessionId);

    }

    public static Map<Integer, String> getActiveSessions() {
        return activeSessions;
    }
}
