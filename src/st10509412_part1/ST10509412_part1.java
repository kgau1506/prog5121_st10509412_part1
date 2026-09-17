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
            System.out.println("Please enter your username: ");
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
            System.out.println("Please enter your password: ");
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
            System.out.println("Please enter your cell phone number: ");
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

        userDetails.close();
    }
}
    
     


