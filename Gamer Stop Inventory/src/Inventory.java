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
		title.setBounds(400,25,666,75);
		
		JButton e=new JButton("Employee");	//two different buttons
		JButton c=new JButton("Customer");
		
		e.setBounds(200,150,100,50); //where each button is positioned
		c.setBounds(700,150,100,50);
		
		frame.add(title);
		frame.add(e); //adding each of the components to the frame
		frame.add(c);
		
		c.addActionListener(new ActionListener(){ //if you press the customer button...
			public void actionPerformed(ActionEvent e){
				try {
					start(); //goes to the start method
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		e.addActionListener(new ActionListener(){ //if you press the employee button
			public void actionPerformed(ActionEvent e){
				
				final JPasswordField value = new JPasswordField(); // 
			    value.setBounds(100,75,100,30);   
			    
			    JLabel l1=new JLabel("Username:");    
			    l1.setBounds(20,20, 80,30);   
			    
			    JLabel l2=new JLabel("Password:");    
			    l2.setBounds(20,75, 80,30);    
			    
			    JButton b = new JButton("Login");  
			    b.setBounds(100,120, 80,30);    
			    
			    final JTextField text = new JTextField();  
			    text.setBounds(100,20, 100,30);
			    
			    frame.add(value); frame.add(l1); frame.add(l2); frame.add(b); frame.add(text);  
			    
			    b.addActionListener(new ActionListener() {  
			    	public void actionPerformed(ActionEvent e) {  
			    		
			    		String data = "Username " + text.getText();  
			    		data += ", Password: "+ new String(value.getPassword());          
			    	}  
			    });   
			}
		});
		
	}
	
	
	public static void start() throws IOException{
		JFrame c_frame=new JFrame("Customer Inventory Search");
		c_frame.setSize(1000, 500);
		c_frame.setVisible(true);
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
		FileWriter fw=new FileWriter("E:\\School\\Programming\\Java\\TalkBot\\Saved Words.txt", true);
		PrintWriter pw=new PrintWriter(fw, true);
		pw.println(word_and_def); //File Writing Method so I don't have to type this every time
		pw.close();
		fw.close();
		System.out.println("Successfully Saved to Dictionary");
	}
	
	public static void FileReader(String word) throws IOException{
		Scanner scan=new Scanner(new File("E:\\School\\Programming\\Java\\TalkBot\\Saved Words.txt"));
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
			System.out.println("There are no entries containing your search, would you like to make a "
					+ "new one? (yes/no)");
			
			String yes_no=in.next();
			
			if(yes_no.equals("yes")||
					yes_no.equals("Yes")||
					yes_no.equals("YES")||//Several options for user type
					yes_no.equals("y")||
					yes_no.equals("Y")){
				
				word_def_check(word);
				new_wrd_not_found();//sends to new method in case they made a mistake and can loop
				
			}else{
				System.exit(0);
			}
			
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

