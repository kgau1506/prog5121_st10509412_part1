/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10509412_part1;

/**
 *
 * @author mphos
 */
public class login {
    
     // Declarations
        String name;
        private String surname;
        private String userName;
        private String password;
        private String cellPhoneNo;
        
        // ================================
        // CHECK USERNAME
        // ================================
        public static boolean checkUserName(String userName){
        return userName.contains("_")&& userName.length()<= 5;
    }
    
        // ================================
        // CHECK CELL PHONE NUMBER
        // ================================
    public static boolean checkCellPhoneNo(String cellPhoneNo){
        // South African number must start with start +27 and contain 9 digits
        String regex = "^\\+27[0-9]{9}$";

        return cellPhoneNo.matches(regex);
    }
   
    // ================================
    // CHECK PASSWORD COMPLEXITY
    // ================================
    public static boolean checkPasswordComplexity(String password){
        // Password must contain at least 8 characters
        if (password.length() < 8){
            return false;
        }
        boolean hasCapitalLetter=false;
        boolean hasSpecialCharacter=false;
        boolean hasNumber=false;
         
        for(int i = 0; i < password.length(); i++){
            char ch = password.charAt(i);
           
            if (Character.isUpperCase(ch)){
                hasCapitalLetter = true;  
            }
            if(Character.isDigit(ch)){
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(ch)){
                hasSpecialCharacter = true;
                
            }
        }
        return password.length() >= 8 
                && hasCapitalLetter 
                && hasSpecialCharacter 
                && hasNumber;
    }
        // Register User
        public String registerUser(String userName,
                               String password,
                               String cellPhoneNo) {

        if (!checkUserName(userName)) {

            return "Username is not correctly formatted; "
                    + "please ensure that your username contains an "
                    + "underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {

            return "Password is not correctly formatted; "
                    + "please ensure that the password contains at least "
                    + "eight characters, a capital letter, a number, "
                    + "and a special character.";
        }

        if (!checkCellPhoneNo(cellPhoneNo)) {

            return "Cell phone number is incorrectly formatted "
                    + "or does not contain international code.";
        }

        // Store the details after successful registration
        this.userName = userName;
        this.password = password;
        this.cellPhoneNo = cellPhoneNo;

        return "The user has been registered successfully.";
        }
    // =================================
    // LOGIN USER
    // =================================

    public boolean loginUser(String userName,String password){
        
        // Compare the entered details with the registered details
        return this.userName.equals(userName)
                && this.password.equals(password);
    }

    // =================================
    // RETURN LOGIN STATUS
    // =================================

    public String returnLoginStatus(boolean loginSuccessful,
                                    String firstName,
                                    String lastName) {

        if (loginSuccessful) {

            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
}

 



