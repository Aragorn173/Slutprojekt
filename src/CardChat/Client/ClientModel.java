package CardChat.Client;

import CardChat.Server.human;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientModel {
    public static String getMsg;
    Socket socket;

    PrintWriter out;
    BufferedReader in;



    String msg = "";

    String chat = "";

    String name = "";

    public void setName(String name) { this.name = name;}

    public String getName() { return name; }

    public String getChat() {return chat;}

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void addMsgToChat(String msg) {
        chat += msg + "\n";
    }


    public void sendChallenge(String name) {
        setMsg(name + " HAS CHALLENGED YOU!");
        out.println(msg);
    }


    public void receiveChallenge(String msg, String name) {
        System.out.println(name);
        System.out.println(getMsg());
        if (getMsg() == name + " HAS CHALLENGED YOU!"){
            int acceptChallange = JOptionPane.showConfirmDialog(null, name + " Has challenged you!\n" + "Do you wish to accept?");

            if (acceptChallange == 0) {
                human player1 = new human(name, 100, 10);
                human player2 = new human("player2", 100, 10);
                player1.battle(player2);

            }
        }
    }


    public ClientModel(String ip, int port) {
        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.err.println("Failed to connect to server");
            e.printStackTrace();
        }
        System.out.println("Connection ready...");
    }

    public void getStreams() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Streams ready...");
    }

    public void runProtocol() {
        Scanner tgb = new Scanner(System.in);
        System.out.println("chatting...");
        while (!msg.equals("QUIT")) {
            msg = tgb.nextLine();
            out.println(name + ":"  + msg);
        }
    }

    public void sendMessage(String msg) {
        out.println(name + ": " + msg);
    }

    public void shutDown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}