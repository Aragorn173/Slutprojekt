package Client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListenerThread implements Runnable{

    private BufferedReader in;
    private ClientController out;


    public ClientListenerThread(BufferedReader in, ClientController out) {
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
            out.newMessage(msg);
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

