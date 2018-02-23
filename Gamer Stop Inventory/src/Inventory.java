/*
 * Simple UI for documenting an inventory for a fake
 * game store that can be used as an employee or just a customer
 * stores the name, price, system etc
 * 
 * @author Ben and Adam Smith
 * @version 1.0
 * @Since January 19, 2018
 * 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Inventory {
	
	public static Scanner in=new Scanner(System.in);
	public static String srch_in;
	public static String return_word_and_def;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame("Gamer Stop Inventory"); //UI start 
		frame.setSize(1000, 500);
		frame.setLayout(null);	
		frame.setVisible(true);
		
		JLabel title= new JLabel("Are you a customer or an Employee?");
		title.setBounds(333,25,666,75);
		
		JButton e=new JButton("Employee");	//two different buttons
		JButton c=new JButton("Customer");
		
		e.setBounds(225,125,100,50); //where each button is positioned
		c.setBounds(550,125,100,50);
		
		frame.add(title); frame.add(e); frame.add(c);//adding each of the components to the frame
		
		c.addActionListener(new ActionListener(){ //if you press the customer button...
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
				try {
					start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //goes to the start method
			}
		});
		
		e.addActionListener(new ActionListener(){ //if you press the employee button
			public void actionPerformed(ActionEvent e){
				
				frame.setVisible(false);//gets rid of old window
				
				JFrame login_frame = new JFrame("Employee Login");
				login_frame.setSize(300, 300);
				
				login_frame.setLayout(null); //new window setup
			    login_frame.setVisible(true);
				
				final JPasswordField pass = new JPasswordField(); //Password field text entry
			    pass.setBounds(100,75,100,30);   
			    
			    final JTextField user = new JTextField();  
			    user.setBounds(100,20, 100,30); //username entry
			    
			    JLabel l1=new JLabel("Username:");    
			    l1.setBounds(20,20, 80,30);   //just username title
			    
			    JLabel l2=new JLabel("Password:");    
			    l2.setBounds(20,75, 80,30);    //password title
			    
			    JButton b = new JButton("Login");  
			    b.setBounds(100,120, 90,30);   //login button 
			    
			    JButton r=new JButton("Register");
			    r.setBounds(100, 160, 90, 30);
			    
			    
			    
			    login_frame.add(pass); login_frame.add(l1); login_frame.add(l2); login_frame.add(b); login_frame.add(user); login_frame.add(r);//add all components to frame
			    
			    
			    b.addActionListener(new ActionListener() {  
			    	public void actionPerformed(ActionEvent e) {  
			    		String username = user.getText();  //gets input from the frame and saves into variables
			    		String password = pass.getText();

			    		try {
							if(FileReader_user_pass(username, password)==true){ //if the inputted username and password are in the saved list
								System.out.println("Welcome"+username);
								JLabel welcome=new JLabel("Welcome "+username); //says welcome [username]
								welcome.setBounds(100, 200, 80, 30);
								login_frame.add(welcome);
							}else{
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();//idk why this is here, but it doesnt work without it
						}
			    	}  
			    });  
			    
			    r.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent a){
			    		
			    	}
			    });
			}
		});
		
	}
	
	
	public static void start() throws IOException{
		JFrame c_frame=new JFrame("Customer Inventory Search");
		c_frame.setSize(1000, 500);
		c_frame.setLayout(null);
		c_frame.setVisible(true);
		
		JLabel c_start=new JLabel("Search for a Title by Name or Console");//title at top of frame
		c_start.setBounds(333,25,666,75);
		
		String[] console={"Select Console","PS4","XBox One", "Switch"}; //list of consoles
		JComboBox consoles=new JComboBox(console);
		consoles.setBounds(250, 120, 125, 30);
		
		JLabel title_label=new JLabel("Game Title:");//title
		title_label.setBounds(410, 120, 100, 30);
		
		JTextField title=new JTextField(); //search entry
		title.setBounds(480, 120, 250, 30);
		
		JButton search=new JButton("Search");
		search.setBounds(400, 180, 80, 30); //enter button basically
		
		c_frame.add(c_start); c_frame.add(consoles); c_frame.add(title_label); c_frame.add(title); c_frame.add(search); //add all components to the frame
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				String game_title=title.getText(); //puts searched title into the variable
				
			}
		});
		consoles.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent b) {       
	        	Object data=consoles.getItemAt(consoles.getSelectedIndex());  //gets which console they chose and saves it into an object
	        }  
		});           
		
		
		
	}
	
	public static void FileWriter(String word_and_def) throws IOException{
		FileWriter fw=new FileWriter("C:\\Users\\bs034696\\Documents\\GitHub\\Gamer-Stop-Inventory\\Gamer Stop Inventory\\src\\User_pass.txt.txt", true); //writes new registered employees
		PrintWriter pw=new PrintWriter(fw, true);
		pw.println(word_and_def); //File Writing Method so I don't have to type this every time
		pw.close();
		fw.close();
		
	}
	
	public static boolean FileReader_user_pass(String user, String pass) throws IOException{
		Scanner scan=new Scanner(new File("C:\\Users\\bs034696\\Documents\\GitHub\\Gamer-Stop-Inventory\\Gamer Stop Inventory\\src\\User_pass.txt.txt")); //reads username and password lists
		while(scan.hasNextLine()){
			final String find_line=scan.nextLine();
			if(find_line.toLowerCase().contains(user.toLowerCase())){//checks if the word (lower case) is in any words or 
				if(find_line.toLowerCase().contains(pass.toLowerCase())){
					scan.close();
					return true;
				}
			}
		}
		scan.close();
		return false;
	}
	
	public static boolean FileReader_game_titles(String title, String console) throws IOException{ //reads list of game titles
		Scanner scan=new Scanner(new File("C:\\Users\\bs034696\\Documents\\GitHub\\Gamer-Stop-Inventory\\Gamer Stop Inventory\\src\\Game_titles.txt"));
		while(scan.hasNextLine()){
			final String find_line=scan.nextLine();
			if(find_line.toLowerCase().contains(title.toLowerCase())){//checks if the word (lower case) is in any words or 
				if(find_line.toLowerCase().contains(console.toLowerCase())){
					scan.close();
					return true;
				}
			}
		}
		scan.close();
		return false;
	}
	
	
	public static void word_def_check(String word) throws IOException{
		System.out.print("Enter your definition for: "+word+"\n");//already enters the word that they were searching for
		System.lineSeparator();
		Scanner scn=new Scanner(System.in);
		String def=scn.nextLine();
		System.lineSeparator();
		System.out.println("Is this the definition for '"+word+"' that you want?: " +def);//bug makes this print and skip def scan??
		System.lineSeparator();
		String yes_no=in.next();
		
		if(yes_no.equals("yes")||
				yes_no.equals("Yes")||
				yes_no.equals("YES")||//Several options for user type
				yes_no.contains("y")||
				yes_no.equals("Y")){
			
			String both=word+", "+def;
			FileWriter(both);
			new_wrd_not_found(); //combines word and definition
		}else{
			word_def_check(word);//resets method	
		}
	}
	
	public static void new_wrd_not_found() throws IOException {//all of the formatting is already done
		
		System.out.println("Would you like to search for or create a new word? (yes/no)");
		
		String yes_no=in.next();
		
		if(yes_no.equals("yes")||
				yes_no.equals("Yes")||
				yes_no.equals("YES")||
				yes_no.equals("y")||
				yes_no.equals("Y")){
			
			start();
			
		}else{
			System.exit(0);
		}
	}
	

}

