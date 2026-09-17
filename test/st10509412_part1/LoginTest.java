/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package st10509412_part1;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mphos
 */
public class LoginTest {
    
    

    /**
     * Test of main method, of class ST10509412_part1.
     */
    @Test
    public void testCheckUserNameCorrect() {
        login login = new login();

        boolean result = login.checkUserName("kyl_1");

        assertTrue(result);
    }
    
     @Test
    public void testCheckUserNameIncorrect() {
        login login = new login();

        boolean result = login.checkUserName("kyle!!!!!!!");

        assertFalse(result);
    }
    
    @Test
    public void testCheckPasswordComplexityCorrect() {
        login login = new login();

        boolean result =
                login.checkPasswordComplexity("Ch&&sec@ke99!");

        assertTrue(result);
    }
    
     @Test
    public void testCheckPasswordComplexityIncorrect() {
        login login = new login();

        boolean result =
                login.checkPasswordComplexity("password");

        assertFalse(result);
    }
    
     @Test
    public void testCheckCellPhoneNumberCorrect() {
        login login = new login();

        boolean result =
                login.checkCellPhoneNo("+27838968976");

        assertTrue(result);
    }
    
    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        login login = new login();

        boolean result =
                login.checkCellPhoneNo("08966553");

        assertFalse(result);
    }
    
    @Test
    public void testRegisterUserSuccessful() {
        login login = new login();

        String result = login.registerUser("kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        assertEquals("The user has been registered successfully.", result);
    }
    
    @Test
    public void testRegisterUserUnsuccessful() {
        login login = new login();

        String result = login.registerUser(
                "kyle!!!!!!!",
                "password",
                "08966553"
        );

        assertEquals(
                "Username is not correctly formatted; "
                + "please ensure that your username contains "
                + "an underscore and is no more than five "
                + "characters in length.",
                result);
    }
    
     @Test
    public void testReturnLoginStatusSuccessful() {
        login login = new login();

        String result =
                login.returnLoginStatus(true, "Kyle", "Smith");
        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                result);
    }
    
    @Test
    public void testReturnLoginStatusUnsuccessful() {
        login login = new login();

        String result =
                login.returnLoginStatus(false, "name", "surname");


        assertEquals(
                "Username or password incorrect, please try again.",
                result);
    }
    
    @Test
    public void testLoginUserSuccessful() {
        login login = new login();

        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976");

        boolean result =
                login.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!");

        assertTrue(result);
    }

    @Test
    public void testLoginUserUnsuccessful() {
        login login = new login();
        
        login.registerUser(
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976" );

        boolean result =
                login.loginUser(
                        "kyl_1",
                        "wrongPassword");

        assertFalse(result);
    }
    
}
