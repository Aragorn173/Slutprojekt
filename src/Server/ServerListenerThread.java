package Server;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListenerThread implements Runnable{

private BufferedReader in;
private ServerController out;


    public ServerListenerThread(BufferedReader in, ServerController out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String msg = null;
        while(true) {
            try {
                msg = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.newmessage(msg);
        }
    }
    public void stop() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
