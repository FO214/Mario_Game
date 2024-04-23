// Fahmi Omer
// June 23, 2022
// Final Game Project
// Create a game using various programming knowledge learned over the course


//Import Lines for Code

import hsa_ufa.Console;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;


			public class mario_gameplay {
				
				
				//Initialize static variables 
				static byte run_phase;
				static short mario_x;
				static short mario_y;
				static byte help_counter;
				static byte highscore_counter;
				static byte name_counter;
				static String username;
				
				//Create console 
				static Console c = new Console(1258, 1125,"Super Mario Fam");
				
				
				
				//Mario run animation method
				public static void mario_run() throws InterruptedException, IOException {
					//Import files for use
					  File picture1 = new File("src/Mario_Back.png");
					  Image backdrop = ImageIO.read(picture1);
				      File mario_1 = new File("src/Mario_Run_3.png");
				      Image Run_1 = ImageIO.read(mario_1);
				      File mario_2 = new File("src/Mario_Run_2.png");
				      Image Run_2 = ImageIO.read(mario_2);
				      File mario_3 = new File("src/Mario_Run_1.png");
				      Image Run_3 = ImageIO.read(mario_3);
				      //Movement animation while loop
					while (mario_x < 200) {
				    	 synchronized (c) {
				    	 c.clear();
				    	 c.drawImage(backdrop,0,0);
				    	 if (run_phase == 0) {
				    	 c.drawImage(Run_1,mario_x,mario_y);
				    	 run_phase = 1;
				    	 }
				    	 
				    	 
				    	 else if (run_phase == 1) {
				    	 c.drawImage(Run_2,mario_x,mario_y);
				    	 run_phase = 2;
				    	 }
				    	 
				    	 else {
				    	 c.drawImage(Run_3,mario_x,mario_y);
				    	 run_phase = 0;
				    	 }
				    	 }
				         Thread.sleep(90);              
				         mario_x+= 4;                      
				      }  
				}
				
				
				
				//Main screen buttons method 
				public static void buttons() throws IOException {
					//input files and enable mouse 
					File goomba_1 = new File("src/goomba_frame_1.png");
				     Image Goomba_1 = ImageIO.read(goomba_1);
					c.enableMouse();
				      c.enableMouseMotion();
				      
				      //While loop for each button to display a goomba beside it and cover it up when off
				  	  while (true) { 
				  		  if (c.getMouseX() > 400 && c.getMouseX() < 894 && c.getMouseY() > 465 && c.getMouseY() < 500) {
				  			  c.drawImage(Goomba_1, 330, 448);
				  			  if (c.getMouseButton(0)) {
				  				  help_counter = 0;
				  				  break;
				  			  }
				  		  } 
				  		  else {
				  			  c.setColor(new Color(92, 148, 252));
				  			  c.fillRect(330, 448, 64, 64);
				  		  }
				  		if (c.getMouseX() > 470 && c.getMouseX() < 834 && c.getMouseY() > 533 && c.getMouseY() < 568) {
				  			c.drawImage(Goomba_1, 330, 516);
				  			if (c.getMouseButton(0)) {
					  			  help_counter = 1;
					  			  break;
				  		  }
				  		  } else {
				  			  c.setColor(new Color(92, 148, 252));
				  			  c.fillRect(330, 516, 64, 64);
	
				  	  } 
				  		if (c.getMouseX() > 484 && c.getMouseX() < 814 && c.getMouseY() > 600 && c.getMouseY() < 636) {
				  			c.drawImage(Goomba_1, 330, 583);
				  			if (c.getMouseButton(0)) {
					  			  highscore_counter = 1;
					  			  break;
				  		  }
				  		  } else {
				  			  c.setColor(new Color(92, 148, 252));
				  			  c.fillRect(330, 583, 64, 64);
	
				  	  }
				  	  }
				}
				
				
				
				//helpscreen and highscore screen buttons method 
				public static void highscore_button() throws IOException {
					//Import all files needed
					File picture1 = new File("src/Mario_Back.png");
					Image backdrop = ImageIO.read(picture1);
					File inFile = new File("src/Highscores.txt");
				  	Scanner fReader = new Scanner(inFile);
				  	File inFile2 = new File("src/Highscores_Names.txt");
				  	Scanner fReader2 = new Scanner(inFile2);
				  	File goomba_1 = new File("src/goomba_frame_1.png");
				     Image Goomba_1 = ImageIO.read(goomba_1);
				     
				     //create highscore information arrays 
					String highscores[] = {null, null, null};
				  	String highscores_names[] = {null, null, null};
				  	
				  	for (int i = 0; i<=2; i++) {
				  		highscores[i] = fReader.nextLine();
				  		highscores_names[i] = fReader2.nextLine();
				  	}
				  	
				  	  
				  	  if (highscore_counter == 1) {
				  		  
				  		  
				  		  //reset background with written lines 
				  		  c.clear();
				  		  c.drawImage(backdrop,0,0);
				  		  c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
					  	  c.setColor(Color.WHITE);
					  	  c.drawString("Top Three High Scores", 280, 330);
					  	  c.drawString("Continue to Level", 675, 875);
					  	  c.drawString("Help Screen", 120, 875);
					  	  c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 30));
					  	  
					  	  int scores_y = 430;
						  	
					  	  
					  	  for (int i = 0; i<=2; i++) {
					  		  c.drawString(highscores_names[i], 200, scores_y);
					  		  c.drawString(highscores[i], 1000, scores_y);
					  		  c.drawString("........................", 400, scores_y);
					  		  scores_y+=70;
					  	  }
					  	  
					      //create clickable buttons 
				  		  while (true) {
				  			if (c.getMouseX() > 675 && c.getMouseX() < 1225 && c.getMouseY() > 840 && c.getMouseY() < 875) {
					  			  c.drawImage(Goomba_1, 598, 823);
					  			  if (c.getMouseButton(0)) {
					  				  break;
					  			  }
					  		  } 
					  		  else {
					  			  c.setColor(new Color(92, 148, 252));
					  			  c.fillRect(598, 823, 64, 64);
					  		  }
				  			
				  			if (c.getMouseX() > 120 && c.getMouseX() < 474 && c.getMouseY() > 837 && c.getMouseY() < 875) {
					  			  c.drawImage(Goomba_1, 43, 820);
					  			  if (c.getMouseButton(0)) {
					  				help_counter = 1;
					  				  break;
					  			  }
					  		  } 
					  		  else {
					  			  c.setColor(new Color(92, 148, 252));
					  			  c.fillRect(43, 820, 64, 64);
					  		  }
				  		  }
				  	  }
				  	  //close open objects 
				  	fReader.close();
				  	fReader2.close();
				}
				
				
				
				//Name input method
				public static void username_input() throws IOException {
					//Import all files required
					File picture1 = new File("src/Mario_Back.png");
					Image backdrop = ImageIO.read(picture1);
				  	File goomba_1 = new File("src/goomba_frame_1.png");
				    Image Goomba_1 = ImageIO.read(goomba_1);
				     
					name_counter = 0;
				  	  username = null;
				  	  
				  	  while (name_counter == 0) {
				  	  //Set backgrund for username 
				  	  c.clear();
			  		  c.drawImage(backdrop,0,0);
			  		  c.setColor(Color.WHITE);
			  		  c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 30));
				  	  c.drawString("Enter Your Username", 400, 230);
				  	  
			  		  username = c.readLine();
			  		  
			  		  c.clear();
			  		  c.drawImage(backdrop,0,0);
			  		  c.setColor(Color.WHITE);
				  	  c.drawString("Enter Your Username", 400, 230);
			  		  
			  		  c.drawString("You Entered", 500, 300);
			  		  c.drawString(username, 480, 430);
			  		  c.drawString("Continue or Remake", 400, 500);
			  		  c.drawString("Continue", 148, 800);
			  		  c.drawString("Remake", 950, 800);
			  		  
			  		  //Review for approval for nickname 
				  		while (true) {
				  			if (c.getMouseX() > 148 && c.getMouseX() < 343 && c.getMouseY() > 770 && c.getMouseY() < 800) {
					  			  c.drawImage(Goomba_1, 78, 753);
					  			  if (c.getMouseButton(0)) {
					  				  name_counter = 1;
					  				  break;
					  			  }
					  		  } 
					  		  else {
					  			  c.setColor(new Color(92, 148, 252));
					  			  c.fillRect(78, 753, 64, 64);
					  		  } 
				  			
				  			if (c.getMouseX() > 950 && c.getMouseX() < 1110 && c.getMouseY() > 770 && c.getMouseY() < 800) {
					  			  c.drawImage(Goomba_1, 880, 753);
					  			  if (c.getMouseButton(0)) {
					  				  break;
					  			  }
					  		  } 
					  		  else {
					  			  c.setColor(new Color(92, 148, 252));
					  			  c.fillRect(880, 753, 64, 64);
					  		  }
				  		  }
				  		  
				  		  
					  	  }
				}
				
				
				
				
				
				
				
				//Start of main program and end of preset methods 
				public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
					
				      
				      //Import background
				      File picture1 = new File("src/Mario_Back.png");
				  	  Image backdrop = ImageIO.read(picture1);
				  	  File picture6 = new File("src/SMB1_World_-1_p1.jpg");
				  	  Image level_1 = ImageIO.read(picture6);
				  	  c.drawImage(backdrop,0,0);
				  	  
				  	  
				      //Import all mario sprites and text files 
				      
				  	  File outFile = new File("src/Highscores.txt");
				  	  File inFile = new File("src/Highscores.txt");
				  	  Scanner fReader = new Scanner(inFile);
				  	  File outFile2 = new File("src/Highscores_Names.txt");
				  	  File inFile2 = new File("src/Highscores_Names.txt");
				  	  Scanner fReader2 = new Scanner(inFile2);
				  	  
				  	  
				  	  
				      File mario_1 = new File("src/Mario_Run_3.png");
				      Image Run_1 = ImageIO.read(mario_1);
				      File mario_2 = new File("src/Mario_Run_2.png");
				      Image Run_2 = ImageIO.read(mario_2);
				      File mario_3 = new File("src/Mario_Run_1.png");
				      Image Run_3 = ImageIO.read(mario_3);
				      File mario_stand = new File("src/mario sprites (1).png");
				      Image Mario_standing = ImageIO.read(mario_stand);
				      File goomba_1 = new File("src/goomba_frame_1.png");
				      Image Goomba_1 = ImageIO.read(goomba_1);
				      
				  	  File picture2 = new File("src/Mario_Title.png");
				  	  Image title = ImageIO.read(picture2);
				  	  
				  	  
				  	  
				  	  //Set Variable Values
				  	  run_phase = 0;
				      mario_x = -50;
				      mario_y = 895; 
				      int score = 0;
				      
				      //While Loop for Mario walking animation
				      mario_run();
				      
				      //Reset Background
				      c.clear();
				      c.drawImage(backdrop,0,0);
				      c.drawImage(Mario_standing,mario_x,mario_y-2);
				      

				      //Set Title Variables 
				  	  short title_x = 300;
				  	  short title_y = -400;
				  	  
				  	  
				  	  //While Loop for title animations
				  	  while (title_y < 150) {
				  		
				  		synchronized(c) {
				    	 c.setColor(new Color(92, 148, 252));
				    	 c.fillRect(title_x, title_y-10, 660, 300);
				    	 c.drawImage(title,title_x,title_y);
				  		}
				  		
				    	 Thread.sleep(70);
				    	 title_y+=10;
				    	                     
				      }
				  	
				  	
				  	//write in all lines with imported font
				  	
				  	  c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
				  	  c.setColor(Color.WHITE);
				  	  c.drawString("1  PLAYER  GAME", 400, 500);
				  	  c.drawString("How To Play", 470, 568);
				   	  c.drawString("Press 1 PLAYER GAME to continue", 130, 850);
				  	  c.drawString("MARIO", 100, 40);
				      c.drawString("000000", 100, 82);
				      c.drawString("WORLD", 800, 40);
				      c.drawString("1-1", 834, 82);
				      c.drawString("TIME", 1040, 40);
				      c.drawString("Highscores", 484, 636);
				      c.setColor(new Color(243, 199, 207));
				      c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 26));
				      c.drawString("1985 NINTENDO", 622, 444);
				      
				      
				      //Name in bottom left of console
				      
				      c.setColor(Color.BLACK);
				  	  c.setCursor(60,158);
				      c.print("Coded By: Fahmi Omer",20);
				      
				      
				      
				      //Import and use sound file
				      
				      File audioFile = new File("src/595665__chungus43a__super-mario-bros.wav");
				  	  Clip sound1 = AudioSystem.getClip();
				  	  sound1.open(AudioSystem.getAudioInputStream(audioFile));
				  	
				      sound1.start();
				      
				      help_counter = 0;
				      highscore_counter = 0;
				      
				      //Mouse and keyboard input for start
				      buttons();
				  	  
				  	  
				  	  //Create arrays with highscore data and names
				  	String highscores[] = {null, null, null};
				  	String highscores_names[] = {null, null, null};
				  	
				  	for (int i = 0; i<=2; i++) {
				  		highscores[i] = fReader.nextLine();
				  		highscores_names[i] = fReader2.nextLine();
				  	}
				  	
				  	  //Highscore screen animation
				  	  highscore_button();
				  	  
				  	  //help screen 
				  	  
				  	  if (help_counter == 1) {
				  		  //Set background for help screen
				  		  c.clear();
				  		  c.drawImage(backdrop,0,0);
				  		  c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
					  	  c.setColor(Color.WHITE);
					  	  c.drawString("Help Screen", 20, 50);
					  	  c.drawString("Controls", 75, 170);
					  	  c.drawString("Hold D key to move forward", 170, 230);
					  	  c.drawString("Hold A key to move backwards", 150, 300);
					  	  c.drawString("Press W key to Jump", 260, 370);
					  	  c.drawString("Rules", 120, 450);
					      c.drawString("Don't touch the Goomba", 240, 510);
					      c.drawString("Jump On the Goomba To Win", 200, 580);
					      c.drawString("Touch the goal post to win", 185, 650);
					      c.drawString("Continue to Level", 675, 875);
					      
					      //create advancement button 
				  		  while (true) {
				  			if (c.getMouseX() > 675 && c.getMouseX() < 1225 && c.getMouseY() > 840 && c.getMouseY() < 875) {
					  			  c.drawImage(Goomba_1, 598, 823);
					  			  if (c.getMouseButton(0)) {
					  				  break;
					  			  }
					  		  } 
					  		  else {
					  			  c.setColor(new Color(92, 148, 252));
					  			  c.fillRect(598, 823, 64, 64);
					  		  }
				  		  }
				  	  }
				  	  
				      
				  	  //Name input and recording screen
				  	  
				  	  username_input();
			  		  
				  	  
				  	  //Screen for level 0-1, practice level
				  	  c.clear();
				  	  c.drawImage(level_1,0,0);
				  	  Thread.sleep(3000);
				  	  
				  	  //Set Variable Values
				  	  run_phase = 0;
				  	  mario_x = -50;
				      mario_y = 895; 
				      
				      
				      //While Loop for Mario walking animation
				      mario_run();  
				      
				      
				      //Stand Mario up straight
				      c.clear();
				      c.drawImage(backdrop,0,0);
				      c.drawImage(Mario_standing,mario_x,mario_y-2);
				      
				      
				      //Write instruction to the console for user
				      c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
				  	  c.setColor(Color.WHITE);
				  	  c.drawString("Hold D key to move forward", 170, 230);
				  	  c.drawString("Hold A key to move backwards", 150, 300);
				  	  c.drawString("Press W key to Jump", 260, 370);
				      c.drawString("Don't touch the Goomba", 240, 510);
				      c.drawString("Jump On the Goomba To Win", 200, 580);
				      c.drawString("Touch the goal post to win", 185, 650);
				  	  			  	  

				  	  //initialize images and variables for back 
				  	  
				  	  File mario_back_1 = new File("src/Mario_Back_Run_3.png");
				      Image Run_back_1 = ImageIO.read(mario_back_1);
				      File mario_back_2 = new File("src/Mario_back_Run_2.png");
				      Image Run_back_2 = ImageIO.read(mario_back_2);
				      File mario_back_3 = new File("src/Mario_Back_Run_1.png");
				      Image Run_back_3 = ImageIO.read(mario_back_3);
				      File mario_jump = new File("src/Mario_Jump.png");
				      Image Mario_Jump = ImageIO.read(mario_jump);
				      File mario_jump_2 = new File("src/Mario_Jump_2.png");
				      Image Mario_Jump_2 = ImageIO.read(mario_jump_2);
				      File goomba_2 = new File("src/goomba_frame_2.png");
				      Image Goomba_2 = ImageIO.read(goomba_2);
				      File mario_death = new File("src/186601641_190f63041e_b-removebg-preview (1).png");
				      Image Mario_death = ImageIO.read(mario_death);
				      File flagpole = new File("src/image-removebg-preview.png");
				      Image Flagpole = ImageIO.read(flagpole);
				      
				      //SPREAD OUT GOOMBAS MORE OR ADD MORE GOOMBAS 
				      int goomba_x[] = {1250, 2000, 2500, 2600, 3000, 4000, 4300, 5000, 5250, 6000, 6500, 6700, 7000, 7500, 7700}; //Starting X values for each goomba
				      short goomba_y = 910;
				      byte goomba_run_phase = 0;
				  	  byte back_run_phase = 0;
				  	  short gravity = -90;
				  	  byte break_phase = 0;
				  	  int back_dropx[] = {0, 1258, 2516, 3774, 5032};
				  	  byte loop_counter = 0;
				  	  int Flag_x = 3800;
				  	  short Flag_y = 273;
				  	  

				  	//Code for input 'D', 'W', 'A' key, and goomba movement animation
				  	while (true) {
				  		 
				  		 if (c.isKeyDown('D')) { //Code for when the D key is held down 
				    	 synchronized (c) {
				    		 c.clear();
				    		 for(int i = 0; i <= back_dropx.length-1; i++) { //make repeat for every backdrop
				    			 c.drawImage(backdrop,back_dropx[i],0);
				    		 }
				    		 c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
				    		 c.drawString("Score", 20, 50);
					  		 c.drawString(String.valueOf(score) + " pts", 20, 95);
					  		c.drawString("WORLD", 800, 50);
						      c.drawString("1-1", 834, 92);
					  	 if (run_phase == 0) {
				    	 c.drawImage(Run_1,mario_x,mario_y);
				    	 run_phase = 1;
				    	 }
				    	 
				    	 else if (run_phase == 1) {
				    	 c.drawImage(Run_2,mario_x,mario_y);
				    	 run_phase = 2;
				    	 }
				    	 
				    	 else {
				    	 c.drawImage(Run_3,mario_x,mario_y);
				    	 run_phase = 0;
				    	 }
				    	 
				    	 if (goomba_run_phase == 0) {
				    		 for(int i = 0; i <= goomba_x.length-1; i++) {  //make repeat for every goomba
					    	 c.drawImage(Goomba_1,goomba_x[i],goomba_y);
				    		 }
					    	 goomba_run_phase = 1;
					    	 }
					    	 
					    	 
					    	 else if (goomba_run_phase == 1) {
					    	 for(int i = 0; i <= goomba_x.length-1; i++) {  //make repeat for every goomba
					    	 c.drawImage(Goomba_2,goomba_x[i],goomba_y);
					    	 }
					    	 goomba_run_phase = 0;
					    	 }
			  		
				    	 if (c.isKeyDown('W')) { // code for when the W is pressed down while the D key is held down 
						  		synchronized (c) {
							    c.clear();
							    for(int i = 0; i <= back_dropx.length-1; i++) { //make repeat for every backdrop
					    			 c.drawImage(backdrop,back_dropx[i],0);
					    		 }
							    c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
							    c.drawString("Score", 20, 50);
						  		 c.drawString(String.valueOf(score) + " pts", 20, 95);
						  		c.drawString("WORLD", 800, 50);
							      c.drawString("1-1", 834, 92);
							    c.drawImage(Mario_Jump,mario_x,mario_y-9);
						  		if (goomba_run_phase == 0) {
						  			for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
							    	 c.drawImage(Goomba_1,goomba_x[i],goomba_y);
						  			}
							    	 goomba_run_phase = 1;
							    	 }
							    	 
							    	 
							    	 else if (goomba_run_phase == 1) {
							    	 for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
							    	 c.drawImage(Goomba_2,goomba_x[i],goomba_y);
							    	 }
							    	 goomba_run_phase = 0;
							    	 }
						  	
						  	}
						  		Thread.sleep(90);
						  		mario_y+=gravity;
						  		gravity+=10;
						  		if (mario_y >= 895) { 
							  		gravity = 0;
							  		}
						  		for(int i = 0; i <= back_dropx.length-1; i++) {
						  			if (back_dropx[back_dropx.length-1] > 3500) { //Setting limits for background so mario doesn't go offscreen
						  				back_dropx[i]-= 3;
						  				 
						         }  
						  		}
					  			if (back_dropx[back_dropx.length-1] > 3500) { //Setting limits for background so mario doesn't go offscreen
					  			Flag_x-=3; // Incrementing Values
					  			}
						  		
						}
				    	 }
				         Thread.sleep(90);              
				         for(int i = 0; i <= back_dropx.length-1; i++) {
				        	 if (back_dropx[back_dropx.length-1] > 1000) { //Setting limits for background so mario doesn't go offscreen
				        	 back_dropx[i]-= 10; // Incrementing Values
				        	 
				        	 }
				        	 
				         } 
				         if (back_dropx[back_dropx.length-1] > 1000) { //Setting limits for background so mario doesn't go offscreen
				        	 Flag_x-=10; // Incrementing Values
				        	 }
				         for(int i = 0; i <= goomba_x.length-1; i++) {
				         goomba_x[i]-=21; // Incrementing Values
				         }
				      
				  	} else if (c.isKeyDown('A')) {
				  		synchronized (c) {
					    	 c.clear();
					    	 
					    	 for(int i = 0; i <= back_dropx.length-1; i++) { //make repeat for every backdrop
				    			 c.drawImage(backdrop,back_dropx[i],0);
				    		 }
					    	 c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
					    	 c.drawString("Score", 20, 50);
					  		 c.drawString(String.valueOf(score) + " pts", 20, 95);
					  		c.drawString("WORLD", 800, 50);
						      c.drawString("1-1", 834, 92);
					    	 if (back_run_phase == 0) {
					    	 c.drawImage(Run_back_1,mario_x,mario_y);
					    	 back_run_phase = 1;
					    	 }
					    	 
					    	 
					    	 else if (back_run_phase == 1) {
					    	 c.drawImage(Run_back_2,mario_x,mario_y);
					    	 back_run_phase = 2;
					    	 }
					    	 
					    	 else {
					    	 c.drawImage(Run_back_3,mario_x,mario_y);
					    	 back_run_phase = 0;
					    	 }
					    	 if (goomba_run_phase == 0) {
					    		 for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
						    	 c.drawImage(Goomba_1,goomba_x[i],goomba_y);
					    		 }
						    	 goomba_run_phase = 1;
						    	 }
						    	 
						    	 
						    	 else if (goomba_run_phase == 1) {
						    	 for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
						    	 c.drawImage(Goomba_2,goomba_x[i],goomba_y);
						    	 }
						    	 goomba_run_phase = 0;
						    	 }
					    	 if (c.isKeyDown('W')) {
							  		synchronized (c) {
								    c.clear();
								    for(int i = 0; i <= back_dropx.length-1; i++) { //make repeat for every backdrop
						    			 c.drawImage(backdrop,back_dropx[i],0);
						    		 }
								    c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
								    c.drawString("Score", 20, 50);
							  		 c.drawString(String.valueOf(score) + " pts", 20, 95);	
							  		c.drawString("WORLD", 800, 50);
								      c.drawString("1-1", 834, 92);
								    c.drawImage(Mario_Jump_2,mario_x,mario_y-9);
							  		if (goomba_run_phase == 0) {
							  			for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
								    	 c.drawImage(Goomba_1,goomba_x[i],goomba_y);
							  			}
								    	 goomba_run_phase = 1;
								    	 }
								    	 
								    	 
								    	 else if (goomba_run_phase == 1) {
								    	 for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
								    	 c.drawImage(Goomba_2,goomba_x[i],goomba_y);
								    	 }
								    	 goomba_run_phase = 0;
								    	 }
							  	
							  	}
							  		Thread.sleep(90);
							  		mario_y+=gravity;
							  		gravity+=10;
							  		if (mario_y >= 895) { 
								  		gravity = 0;
								  		}
							}
					    	 for(int i = 0; i <= back_dropx.length-1; i++) { //Setting limits for background so mario doesn't go offscreen
					    		 if (back_dropx[0] < -10) {
					        	 back_dropx[i]+= 3; // Incrementing Values 
					        	 
					    		 }
					    	 }
					    		 if (back_dropx[0] < -10) { //Setting limits for background so mario doesn't go offscreen
					    		 Flag_x+=3; // Incrementing Values
					    		 }
					          
					    	 }
					         Thread.sleep(90); 
					         for(int i = 0; i <= back_dropx.length-1; i++) {
					        	 if (back_dropx[0] < -10) { //Setting limits for background so mario doesn't go offscreen
					        	 back_dropx[i]+= 10; // Incrementing Values
					        	 
					        	 }
					         }
					        	 if (back_dropx[0] < -10) { //Setting limits for background so mario doesn't go offscreen
					        	 Flag_x+=10; // Incrementing Values
					        	 }
					         
					         for(int i = 0; i <= goomba_x.length-1; i++) { 
					         goomba_x[i]-=4; // Incrementing Values
					         }
					         
					         
				  	} else if (c.isKeyDown('W')) {
				  		synchronized (c) {
					    c.clear();
					    for(int i = 0; i <= back_dropx.length-1; i++) { //make repeat for every backdrop
			    			 c.drawImage(backdrop,back_dropx[i],0);
			    		 }
					    c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
					    c.drawString("Score", 20, 50);
				  		 c.drawString(String.valueOf(score) + " pts", 20, 95);
				  		c.drawString("WORLD", 800, 50);
					      c.drawString("1-1", 834, 92);
					    c.drawImage(Mario_Jump,mario_x,mario_y-9);
				  		if (goomba_run_phase == 0) {
				  			for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
					    	 c.drawImage(Goomba_1,goomba_x[i],goomba_y);
				  			}
					    	 goomba_run_phase = 1;
					    	 }
					    	 
					    	 
					    	 else if (goomba_run_phase == 1) {
					    	 for(int i = 0; i <= goomba_x.length-1; i++) { //make repeat for every goomba
					    	 c.drawImage(Goomba_2,goomba_x[i],goomba_y);
					    	 }
					    	 goomba_run_phase = 0;
					    	 }
				  	
				  	}
				  		Thread.sleep(90);
				  		mario_y+=gravity; // Incrementing Values
				  		gravity+=10; // Incrementing Values
				  		for(int i = 0; i <= goomba_x.length-1; i++) {
				  		goomba_x[i]-=10; // Incrementing Values
				  		}
				  		if (mario_y >= 895) {
					  		gravity = 0;
					  		}
				}
				  	if (mario_y != 895) {
			  			mario_y+=gravity; // Incrementing Values
			  			gravity+=10; // Incrementing Values
			  		}
				  	if (mario_y >= 895) {
				  		gravity = -90; // Incrementing Values
				  	}
				  	
				  	//collision detection for mario hitting goomba and mario hitting flag 
				  	for(int i = 0; i <= goomba_x.length-1; i++) {
				  	if (mario_x < goomba_x[i] + 52 && mario_y < goomba_y + 16 && goomba_x[i]+12 < mario_x + 52 && goomba_y < mario_y + 82) {
				  		synchronized (c) {
						    c.clear();
						    for(int i1 = 0; i1 <= back_dropx.length-1; i1++) { //make repeat for every backdrop
				    			 c.drawImage(backdrop,back_dropx[i1],0);
				    		 }
						    c.drawImage(Flagpole,Flag_x,Flag_y); //Drawing the flagpole in desired location after each clear
						    c.drawString("Score", 20, 50);
					  		 c.drawString(String.valueOf(score) + " pts", 20, 95);
					  		c.drawString("WORLD", 800, 50);
						      c.drawString("1-1", 834, 92);
						    c.drawImage(Mario_Jump,mario_x,mario_y-9);
					  		if (goomba_run_phase == 0) {
					  			for(int i1 = 0; i1 <= goomba_x.length-1; i1++) { //make repeat for every goomba
						    	 c.drawImage(Goomba_1,goomba_x[i1],goomba_y);
					  			}
						    	 goomba_run_phase = 1;
						    	 }
						    	 
						    	 
						    	 else if (goomba_run_phase == 1) {
						    	 for(int i1 = 0; i1 <= goomba_x.length-1; i1++) { //make repeat for every goomba
						    	 c.drawImage(Goomba_2,goomba_x[i1],goomba_y);
						    	 }
						    	 goomba_run_phase = 0;
						    	 }
					  	
					  	}
					  		Thread.sleep(90);
					  		mario_y+=gravity; // Incrementing Values
					  		gravity+=10; // Incrementing Values
					  		for(int i1 = 0; i1 <= goomba_x.length-1; i1++) {
					  		goomba_x[i1]-=10; // Incrementing Values
					  		}
					  		if (mario_y >= 895) {
						  		gravity = 0;
						  		}
					  		
					  		Thread.sleep(20);
					  		score+=100;
					  		goomba_x[i] = 100000;
				  		
					  		//Collision detection for mario and goombas, or mario and flag 
				  	} else if (mario_x < goomba_x[i] + 64 && mario_y < goomba_y + 64 && goomba_x[i] < mario_x + 52 && goomba_y +16< mario_y + 82) {
				  		break_phase = 1;
				  		loop_counter = 1;
				  		break;
				  	} else if (mario_x < Flag_x + 98 && mario_y < Flag_y + 698 && Flag_x + 90 < mario_x + 52 && Flag_y + 52 < mario_y + 82) {
				  		break_phase = 0;
				  		score+=500;
				  		loop_counter = 1;
				  		break;
				  	}
				  	
				  	}
				  	if (loop_counter == 1) {
				  		break;
				  	}
				  	
				  	
			}  //end of while 
				  	
				  	
				  	byte score_counter = 0;
				  	
				  	//Open writing files to clear 
				  	BufferedWriter fWriter = new BufferedWriter(new FileWriter(outFile));
				  	BufferedWriter fWriter2 = new BufferedWriter(new FileWriter(outFile2));
				  	
				  	for (int i = 0; i<=2; i++) {
				  		if (score > Integer.valueOf(highscores[i])) {
				  			
				  			c.clear();
					  		c.drawImage(backdrop,0,0);
					  		c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
						  	c.setColor(Color.WHITE);
						  	//upload newfound highscores to current array 
				  			if (i == 0) {
							  	c.drawString("New Highscore!", 390, 500);
							  	Thread.sleep(4000);
				  				
				  				highscores[i+2] = highscores[i+1];
				  				highscores[i+1] = highscores[i];
				  				highscores[i] = String.valueOf(score);
				  				
				  				highscores_names[i+2] = highscores_names[i+1];
				  				highscores_names[i+1] = highscores_names[i];
				  				highscores_names[i] = username;
				  				
				  			} else if (i == 1) {
							  	c.drawString("New Second Place!", 370, 500);
							  	Thread.sleep(4000);
							  	
				  				highscores[i+1] = highscores[i];
				  				highscores[i] = String.valueOf(score);
				  				
				  				highscores_names[i+1] = highscores_names[i];
				  				highscores_names[i] = username;
				  			} else {
							  	c.drawString("New Third Place!", 380, 500);
							  	Thread.sleep(4000);
							  	
				  				highscores[i] = String.valueOf(score);
				  				
				  				highscores_names[i] = username;
				  			}
				  			
				  			score_counter++;
				  		}
				  		if (score_counter > 0) {
				  			break;
				  		}
				  	}
				  	
				  	//write highscores to storing file 
				  	fWriter.write(highscores[0]);
				  	fWriter2.write(highscores_names[0]);
				  	
				  	for (int i = 1; i<=2; i++) {
				  		fWriter.write("\n" + highscores[i]);
				  		fWriter2.write("\n" + highscores_names[i]);
				  	}
				  	
				  	
				  
				  	//Win and loss screen for hitting goomba and hitting flag 
				  	c.clear();
				    	c.setFont(new Font("Super Mario World Text Box", Font.PLAIN, 35));
					  	  c.setColor(Color.BLACK);
					  	  
					  	  if (break_phase == 0) {
					  		  c.drawString("You Win!", 400, 500);
					  	  } else if (break_phase == 1) {
					  		  c.drawString("You Lose.", 400, 500);
					  		  c.drawImage(Mario_death, 500, 700);
					  	  }
					  	  
					  	  //close all open objects 
					  	fWriter.close();
					  	fReader.close();
					  	fWriter2.close();
					  	fReader2.close();
					  	  
				    }
			}
			
		  	
		  

