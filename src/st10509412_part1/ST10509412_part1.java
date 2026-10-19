/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10509412_part1;

/**
 *
 * @author mphos
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import static st10509412_part1.login.checkCellPhoneNo;
import static st10509412_part1.login.checkPasswordComplexity;
import static st10509412_part1.login.checkUserName;
public class ST10509412_part1 {
    
    public static void main(String[] args) {
       
         Scanner userDetails = new Scanner(System.in);
         
         // An Object for the login class
         login login = new login();
       
         // Declarations
        String name;
        String surname;
        String userName;
        String password;
        String cellPhoneNo;
        
        // =========================
        // REGISTRATION
        // =========================
        
        System.out.println("===== REGISTRATION =====");
        
        // Prompting the user for their name
        System.out.println("Please enter your name: ");
        name = userDetails.nextLine();
       
        // Prompting the user for their surname
        System.out.println("Please enter your surname: ");
        surname = userDetails.nextLine();
       
        // Prompting the user for their username
        while(true){
            System.out.println("Please enter your username( must contain an '_' and max 5 chars): ");
            
            userName = userDetails.nextLine();
           
            if (checkUserName(userName)){
                System.out.println("Username successfully captured.");
            break;
    }
            else{
                System.out.println("Username is not correctly formatted,"
                        +"please ensure that your username contains an underscore,"
                        +"and is no more than five characters in length.");
               
            }
        }
       
        // Prompting the user for their password
        while(true){
            System.out.println("Please enter your password (min 8 chars, "
            + "a capital letter, a number, and a special char:) ");
            
            password= userDetails.nextLine();
           
            if (checkPasswordComplexity(password)){
                System.out.println("Password successfully captured");
            break;
    }
            else{
                System.out.println("Password is not correctly formatted.");
                System.out.println("Please ensure that the password contains at least eight characters, "
                        + "a capital letter,a number and a special character.");
            }
        }
         
        // Prompting the user for their cellphone number
         while(true){
            System.out.println("Please enter your cell phone number (with +27:) ");
            
            cellPhoneNo = userDetails.nextLine();
           
            if (checkCellPhoneNo(cellPhoneNo)){
                System.out.println("Cellphone number successfully added.");
            break;
    }
            else{
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
               
            }
        }
         
         // Register user
         String registrationMessage = login.registerUser(userName, password, cellPhoneNo);
         
         System.out.println("You have registered successfully");
         System.out.println("Welcome " + name + " " + surname);
         
         // LOGIN
         System.out.println("\n===== LOGIN =====");

        // Check login details
        boolean loginSuccessful = false;
        while (!loginSuccessful){
            System.out.println("Please enter your username:");
            String loginUsername = userDetails.nextLine();
            
            System.out.println("Please enter your password:");
            String loginPassword = userDetails.nextLine();
            
             loginSuccessful = login.loginUser(loginUsername, loginPassword);
             
             if (!loginSuccessful){
                 System.out.println("Username or password incorrect, please try again.");
             }
        }
        
        // Display login status
        String loginMessage = login.returnLoginStatus(loginSuccessful,name,surname );

        System.out.println(loginMessage);
        
        runQuickChat(userDetails);
        

        userDetails.close();
    }
     
            private static void runQuickChat(Scanner input) {
                int choice = 0;

                while (choice != 3) {
                    
                    System.out.println("\n======================================");
                    System.out.println("          QUICKCHAT Options:");
                    System.out.println("======================================");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.print("Choose an option: ");

                try {
                    choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    
                        case 1:
                            sendMessages(input);
                        break;
                        
                       
                        case 2:
                            String sent = sendMessage.printMessages();
                            System.out.println(sent);
                        break;

                        
                        case 3:
                            
                            System.out.println("Thank you for using QuickChat. Goodbye!");
                        break;
                        
                        default:
                             System.out.println("Please choose 1, 2, or 3.");
            }
                        } catch (NumberFormatException e) {
                            
                                 System.out.println("Please enter a valid number.");
        }   
    }
}

                private static void sendMessages(Scanner input) {
                        int numberOfMessages;

                    while (true) {
                        
                         System.out.print("How many messages would you like to enter? ");

                    try {
                        
                        numberOfMessages = Integer.parseInt(input.nextLine());

                    if (numberOfMessages < 1) {
                        
                            System.out.println("Please enter at least 1 message.");
                    continue;
            }
                    break;
                    
                    } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid whole number.");
        }
    }

                    for (int i = 0; i < numberOfMessages; i++) {
                        System.out.println("\n======================================");
                        System.out.println("MESSAGE " + (i + 1));
                        System.out.println("======================================");

        
                sendMessage message = new sendMessage(i);
                        System.out.println("Message ID generated: " + message.getMessageID());

                    String recipient;
                    
                    while (true) {
                        
                            System.out.print("Enter recipient cell number: ");
                            recipient = input.nextLine();

                message.setRecipient(recipient);

                        if (message.checkRecipientCell()) {
                        System.out.println("Cell phone number successfully captured.");
                break;
            }

                        System.out.println(
                         "Invalid cellphone number. Use +27 followed by 9 digits "
                        + "with a valid South African mobile prefix.");
        }

                    String messageText;
                    
        while (true) {
            System.out.print("Enter your message (maximum 250 characters): ");
            messageText = input.nextLine();

            message.setMessageText(messageText);

            if (message.checkMessageLength()) {
                break;
            }

            if (messageText.trim().isEmpty()) {
                System.out.println("Message cannot be empty.");
            } else {
                System.out.println("Message exceeds 250 characters. Try again.");
            }
        }

        
            message.refreshMessageHash();

                System.out.println("Message ready to send.");
                System.out.println("Message Hash: " + message.getMessageHash());

                System.out.println("\nChoose what to do with this message:");
                System.out.println("1. Send Message");
                System.out.println("2. Disregard Message");
                System.out.println("3. Store Message to send later");
                System.out.print("Choose an option: ");


            String result = message.sentMessage(input);
            System.out.println(result);

        if ("Sent".equals(message.getMessageStatus())) {
            
            System.out.println("\n======================================");
            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Recipient: " + message.getRecipient());
            System.out.println("Message: " + message.getMessageText());
            System.out.println("======================================");
            
        } else if ("Stored".equals(message.getMessageStatus())) {
            
            System.out.println("Message saved for later.");
        }

        System.out.println("\nTotal messages sent: "
                + sendMessage.returnTotalMessages());
        }
    }
}
        
        



    

    
     


