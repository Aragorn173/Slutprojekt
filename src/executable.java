import CardChat.Client.ClientController;
import CardChat.Server.ServerController;

public class executable {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerController.main(args);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ClientController.main(args);
            }
        }).start();
    }
}
