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

public class inventory {
	
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
				title.setVisible(false);
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
			    b.setBounds(100,120, 80,30);   //login button 
			    
			    JButton r=new JButton("Register");
			    r.setBounds(100, 140, 80, 30);
			    
			    
			    
			    login_frame.add(pass); login_frame.add(l1); login_frame.add(l2); login_frame.add(b); login_frame.add(user); //add all components to frame
			    
			    
			    b.addActionListener(new ActionListener() {  
			    	public void actionPerformed(ActionEvent e) {  
			    		String username = user.getText();  
			    		String password = pass.getText();
			    		if(username.equals(FileReader(username))){
			    			
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
		
		JLabel c_start=new JLabel("Search for a Title by Name or Console");
		c_start.setBounds(333,25,666,75);
		
		
		c_frame.add(c_start);
		
		System.out.println("Enter a word or search for one,\nType \'Search\' or \'s\' to Search"
				+ " or type \'New Title\' or \'nt\' to enter a Title and information.");
		
		String Srch_NW=in.next();//change to swing GUI eventually...
		if(Srch_NW.equals("s")||
			Srch_NW.equals("S")||
			Srch_NW.equals("Search")|| //several different options for user type
			Srch_NW.equals("search")){
			
			System.out.println("Search for Title:");
			srch_in=in.next();
			FileReader(srch_in);
			
			
		}else if(Srch_NW.equals("nw")||
				Srch_NW.equals("NW")||
				Srch_NW.equals("New Word")||//here also
				Srch_NW.equals("New word")||
				Srch_NW.equals("new word")){
			
			System.out.println("Enter new title: ");
			String nw=in.nextLine();
			word_def_check(nw);
			
		}else{
			start();
		}
	}
	
	public static void FileWriter(String word_and_def) throws IOException{
		FileWriter fw=new FileWriter("C:\\Users\\bs034696\\Documents\\GitHub\\Gamer-Stop-Inventory\\Gamer Stop Inventory\\src\\User_Pass.txt", true);
		PrintWriter pw=new PrintWriter(fw, true);
		pw.println(word_and_def); //File Writing Method so I don't have to type this every time
		pw.close();
		fw.close();
		
	}
	
	public static void FileReader(String word, String pass) throws IOException{
		Scanner scan=new Scanner(new File("C:\\Users\\bs034696\\Documents\\GitHub\\Gamer-Stop-Inventory\\Gamer Stop Inventory\\src\\User_Pass.txt"));
		String word_list[]=new String[1000]; //reads the file up to 1000 lines (words&definitions)
		int i=0;
		while(scan.hasNextLine()){
			final String find_line=scan.nextLine();
			if(find_line.toLowerCase().contains(word.toLowerCase())){//checks if the word (lower case) is in any words or 
				word_list[i]=find_line;								//definitions (also lower case)
				i++;
			}
			
		}
		
		if(i-1==-1){ //checks if there are any entries containing the search
			System.out.println("The username or password you have entered do not match, try again");
			
			
		}else{
			
			System.out.println("The following contain the word(s) that you searched for:");
			for(int j=0;j<=i-1;){		//iterates and prints through how many were found minus one for the NullPointer
				System.out.println("\n"+word_list[j]);
				j++;
			}
			new_wrd_not_found();
		}
		scan.close();
		start();
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

