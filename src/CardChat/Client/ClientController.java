package CardChat.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientController extends JFrame {
    ClientModel model;
    ClientView view;

    public ClientController(ClientModel m, ClientView v) {
        this.model = m;
        this.view = v;
        this.setTitle("Client");
        v.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setMsg(view.getInput());
                if (model.getMsg().length() > 0) {
                    model.addMsgToChat(model.getName() + ": " + model.getMsg());
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
                model.receiveChallenge(model.getMsg(), model.getName());

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
       String ip = JOptionPane.showInputDialog("What IP do you want to connect to?");
        ClientModel m = new ClientModel(ip, 1731);

        ClientView v = new ClientView();
        ClientController thisIsTheProgram = new ClientController(m,v);
        thisIsTheProgram.setVisible(true);
        m.setName(JOptionPane.showInputDialog("Name?"));


        m.getStreams();
        ClientListenerThread l = new ClientListenerThread(m.in, thisIsTheProgram);
        Thread listener = new Thread(l);
        listener.start();
        m.runProtocol();
        listener.stop();
        m.shutDown();
    }

    public void newMessage(String msg) {
        model.addMsgToChat(msg);
        view.setChat(model.getChat());
    }
}