import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatClient {

	static JFrame chatWindow = new JFrame("Chat Application");
	static JTextArea chatArea = new JTextArea(22,37);
	static JTextField text = new JTextField(37);
	static JLabel blankLabel = new JLabel("             ");
	static JButton sendButton = new JButton("Send");
	static BufferedReader in;
	static PrintWriter out;
	static JLabel nameLabel = new JLabel("          ");
	static String userNames = new String(); //*
	static JTextArea userList = new JTextArea(10,10); //*

	
	ChatClient(){
		
		chatWindow.setLayout(new FlowLayout());
		chatWindow.add(nameLabel);
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(blankLabel);
		chatWindow.add(text);
		chatWindow.add(sendButton);
		chatWindow.add(userList); //*
		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatWindow.setSize(475,700);
		chatWindow.setVisible(true);
		
		text.setEditable(false);
		chatArea.setEditable(false);
		
		sendButton.addActionListener(new Listener());
		text.addActionListener(new Listener());
		
		
	}
	
	void startChat() throws Exception {
		
		String ipAddress = JOptionPane.showInputDialog(chatWindow,"Enter IP Address:","IP Address required!", JOptionPane.PLAIN_MESSAGE);
		
		Socket soc = new Socket(ipAddress, 9806);
		
		in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		out = new PrintWriter(soc.getOutputStream(),true);
		
		while(true){
			
			String str = in.readLine();
			if(str.equals("NAMEREQUIRED")){
				
				String name  = JOptionPane.showInputDialog(chatWindow,"Enter unique name:", "Name Required!", JOptionPane.PLAIN_MESSAGE);
				out.println(name);
			}else if(str.equals("NAMEALREADYEXISTS")){
				String name = JOptionPane.showInputDialog(chatWindow,"Enter another name:", "Name Required", JOptionPane.WARNING_MESSAGE);
				out.println(name);
				
			}else if(str.startsWith("NAMEACCEPTED")){
				
				text.setEditable(true);
				nameLabel.setText("You are logged in as: " + str.substring(12)+" at: "+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
				
				
				
			}else{
				chatArea.append(str + "\n"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date())+"\n");

			}
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ChatClient client = new ChatClient();
		client.startChat();

	}

}

class Listener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		ChatClient.out.println(ChatClient.text.getText());
		ChatClient.text.setText("");
		
	}
}
