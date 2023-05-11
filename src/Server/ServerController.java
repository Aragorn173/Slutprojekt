package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ServerController extends JFrame {
    ServerModel model;
    ServerView view;

    public ServerController(ServerModel m, ServerView v) {
        this.model = m;
        this.view = v;
        this.setTitle("Server");

        v.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setMsg(view.getInput());
                if (model.getMsg().length() > 0) {
                    model.addMsgToChat(model.getName() + ": " +model.getMsg());
                    view.setChat(model.getChat());
                    model.sendMessage(model.getMsg());
                    view.setInput("");
                }

            }
        });


        v.getChallengeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.sendChallenge(model.getName());
                model.receiveChallenge(model.getMsg());
            }

        });

        v.getEnter().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    model.setMsg(view.getInput());
                    if (model.getMsg().length() > 0) {
                        model.addMsgToChat(model.getName() + ": " + model.getMsg());
                        view.setChat(model.getChat());
                        model.sendMessage(model.getMsg());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    view.setInput("");
                }
            }
        });

        this.setContentPane(view.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        v.getSendButton();


    }



    public static void main(String[] args) {
        ServerModel m = new ServerModel(1731);
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);
        m.setName(JOptionPane.showInputDialog("Name?"));

        m.acceptClient();
        m.getStreams();
        ServerListenerThread l = new ServerListenerThread(m.in, thisIsTheProgram);
        Thread listener = new Thread(l);
        listener.start();
        m.runProtocol();
        listener.stop();
        m.shutdown();
    }

    public void newMessage(String msg) {
        model.addMsgToChat(msg);
        view.setChat(model.getChat());
    }
}