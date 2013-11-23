package JavaRMI;
import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.rmi.RemoteException;


/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */

public class ServerRMI{
    static JFrame frame;
    static JTextArea text;
    static JScrollPane textScroll;
    static String ipAddr;
    static String port;
    public static void main(String args[]) {
        try{
            frame = new JFrame();
            frame.setTitle(".: IdeaBroker :.");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setBounds(0,0,300,300);

            text = new JTextArea();
            text.setText(" Welcome!");
            text.setEditable(false);
            text.setForeground(Color.darkGray);
            text.setBackground(Color.lightGray);
            text.setWrapStyleWord(true);
            text.setLineWrap(true);

            textScroll = new JScrollPane(text,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            frame.add(textScroll);
            frame.repaint();
            frame.setVisible(true);
            String aux = JOptionPane.showInputDialog(null,"Insert the IP of the database:", "192.168.56.101");
            try {
                InetAddress.getByName(aux);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error finding the IP of the Database");
                System.exit(-1);
            }
            ipAddr = aux;
            port = JOptionPane.showInputDialog(null,"Insert the port:","1521");

            ImplementedFeatures server = new ImplementedFeatures();
            server.ConnectDatabase();
            server.CreateRMIServer();
        }catch(RemoteException e){
            ServerRMI.text.append("\nError connecting with the database/creating the server!\n"+e);
            System.exit(-1);
        }
    }
}

