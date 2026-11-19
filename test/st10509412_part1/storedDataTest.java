/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package st10509412_part1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author mphos
 */
public class storedDataTest {
      
    
@Test
public void testReadStoredMessages() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"messageNumber\":1,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First test message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.searchMessageByID("1234567890");

    assertTrue(result.contains("First test message"));
    assertTrue(result.contains("TestUser"));
    assertTrue(result.contains("+27834557896"));

    Files.deleteIfExists(testFile);
}
    
   @Test
    public void testDisplayLongestMessage() {

        storedData data = new storedData();

        String[] testMessages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time !",
            "Ok, I am leaving without you."
        };

        String result = data.findLongestMessage(testMessages);

        assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            result
        );
    }
    
    @Test
    public void testSearchMessageByID() {

        storedData data = new storedData();

        String[] testIDs = {
            "1234567890",
            "0838884567",
            "9876543210",
            "4567890123",
            "5678901234"
        };

        String[] testMessages = {
            "Did you get the cake?",
            "It is dinner time !",
            "Yohoooo, I am at your gate.",
            "Where are you?",
            "Ok, I am leaving without you."
        };

        String result = data.findMessageByID(
                "0838884567", testIDs, testMessages);

        assertEquals("It is dinner time !", result);
    }
    
   @Test
    public void testSearchMessagesByRecipient() {

        storedData data = new storedData();

        String[] testRecipients = {
            "+27834557896",
            "+27838884567",
            "+27834484567",
            "+27838884567",
            "+27838884567"
        };

        String[] testMessages = {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time !",
            "Ok, I am leaving without you."
        };

        String result = data.searchMessagesByRecipient(
            "+27838884567", testRecipients, testMessages);

        assertTrue(result.contains("Where are you? You are late!"));
        assertTrue(result.contains("It is dinner time !"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
   
@Test
public void testDeleteMessageByHash() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"messageNumber\":1,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First test message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"messageNumber\":2,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27838884567\","
            + "\"messageText\":\"Second test message\","
            + "\"messageHash\":\"hash2\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.deleteMessageByHash("hash2");

    assertTrue(result.contains("Message deleted successfully"));

    String updatedJSON = new String(
            Files.readAllBytes(testFile), StandardCharsets.UTF_8);

    assertTrue(updatedJSON.contains("hash1"));
    assertFalse(updatedJSON.contains("hash2"));

    // Check that the remaining message can still be found
    assertTrue(data.searchMessageByID("1234567890")
            .contains("First test message"));

    Files.deleteIfExists(testFile);
}

    
    @Test
    public void testDisplayFullReport() {

        storedData data = new storedData();

        String result = data.displayFullReport();

        assertTrue(result.contains("FULL MESSAGE REPORT"));
        assertTrue(result.contains("Message ID:"));
        assertTrue(result.contains("Message Hash:"));
        assertTrue(result.contains("Sender:"));
        assertTrue(result.contains("Recipient:"));
        assertTrue(result.contains("Message:"));
        assertTrue(result.contains("Status:"));
    }
    
    
    @Test
    public void testFindLongestMessageEmptyArray() {

        storedData data = new storedData();

        String[] testMessages = {};

        String result = data.findLongestMessage(testMessages);

        assertEquals("No messages available.", result);
    }

    @Test
    public void testSearchMessageByIDNotFound() {

        storedData data = new storedData();

        String[] testIDs = {
            "1234567890",
            "0987654321"
        };

        String[] testMessages = {
            "First test message",
            "Second test message"
        };

        String result = data.findMessageByID(
            "1111111111", testIDs, testMessages);

        assertEquals("Message not found.", result);
    }
    
    
    @Test
    public void testSearchMessagesByRecipientNotFound() {

        storedData data = new storedData();

        String[] testRecipients = {
            "+27834557896",
            "+27838884567"
        };

        String[] testMessages = {
            "First test message",
            "Second test message"
        };

        String result = data.searchMessagesByRecipient(
            "+27830000000", testRecipients, testMessages);

        assertEquals("", result);
    }
    
    @Test
    public void testDeleteMessageByHashNotFound() throws Exception {

        Path testFile = Files.createTempFile("testMessages", ".json");

        String json = "[]";

        Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

        storedData data = new storedData(testFile.toString());

        String result = data.deleteMessageByHash("unknownHash");

        assertEquals("Message hash not found.", result);

        Files.deleteIfExists(testFile);
    }
    
    @Test
    public void testDisplayFullReportEmpty() throws Exception {

        Path testFile = Files.createTempFile("testMessages", ".json");

        Files.write(testFile, "[]".getBytes(StandardCharsets.UTF_8));

        storedData data = new storedData(testFile.toString());

        String result = data.displayFullReport();

        assertEquals("No stored messages available.", result);

        Files.deleteIfExists(testFile);
    }
    
    
@Test
public void testSearchMessageByIDFromJSON() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"messageNumber\":1,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First test message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.searchMessageByID("1234567890");

    assertTrue(result.contains("First test message"));
    assertTrue(result.contains("TestUser"));
    assertTrue(result.contains("+27834557896"));
    assertTrue(result.contains("hash1"));

    Files.deleteIfExists(testFile);
}

@Test
public void testSearchMessageByIDFromJSONNotFound() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    Files.write(testFile, "[]".getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.searchMessageByID("9999999999");

    assertEquals("Message not found.", result);

    Files.deleteIfExists(testFile);
}


@Test
public void testSearchMessagesByRecipientFromJSON() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"messageNumber\":1,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First test message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"messageNumber\":2,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"Second test message\","
            + "\"messageHash\":\"hash2\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.searchMessagesByRecipient("+27834557896");

    assertTrue(result.contains("First test message"));
    assertTrue(result.contains("Second test message"));
    assertTrue(result.contains("hash1"));
    assertTrue(result.contains("hash2"));

    Files.deleteIfExists(testFile);
}


@Test
public void testSearchMessagesByRecipientFromJSONNotFound() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    Files.write(testFile, "[]".getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.searchMessagesByRecipient("+27830000000");

    assertEquals("No messages found for this recipient.", result);

    Files.deleteIfExists(testFile);
}


@Test
public void testDisplayFullReportFromJSON() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"messageNumber\":1,"
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First test message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    storedData data = new storedData(testFile.toString());

    String result = data.displayFullReport();

    assertTrue(result.contains("FULL MESSAGE REPORT"));
    assertTrue(result.contains("1234567890"));
    assertTrue(result.contains("hash1"));
    assertTrue(result.contains("TestUser"));
    assertTrue(result.contains("+27834557896"));
    assertTrue(result.contains("First test message"));
    assertTrue(result.contains("Stored"));

    Files.deleteIfExists(testFile);
}
    
    
@Test
public void testDisplayLongestMessageDetails() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"Short message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"sender\":\"SecondUser\","
            + "\"recipient\":\"+27838884567\","
            + "\"messageText\":\"This is the longest message in the test file.\","
            + "\"messageHash\":\"hash2\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    try {
        storedData data = new storedData(testFile.toString());

        String result = data.displayLongestMessage();

        assertTrue(result.contains("This is the longest message"));
        assertTrue(result.contains("SecondUser"));
        assertTrue(result.contains("+27838884567"));
        assertTrue(result.contains("0987654321"));
        assertTrue(result.contains("hash2"));

    } finally {
        Files.deleteIfExists(testFile);
    }
}

        
@Test
public void testSearchMessagesByRecipientExcludesOtherRecipients() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"Message for first recipient\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"sender\":\"SecondUser\","
            + "\"recipient\":\"+27838884567\","
            + "\"messageText\":\"Message for second recipient\","
            + "\"messageHash\":\"hash2\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    try {
        storedData data = new storedData(testFile.toString());

        String result = data.searchMessagesByRecipient("+27834557896");

        assertTrue(result.contains("Message for first recipient"));
        assertFalse(result.contains("Message for second recipient"));
        assertTrue(result.contains("hash1"));
        assertFalse(result.contains("hash2"));

    } finally {
        Files.deleteIfExists(testFile);
    }
}

                
@Test
public void testDeleteMessageByHashKeepsOtherMessages() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"Message to delete\","
            + "\"messageHash\":\"deleteHash\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"sender\":\"SecondUser\","
            + "\"recipient\":\"+27838884567\","
            + "\"messageText\":\"Message to keep\","
            + "\"messageHash\":\"keepHash\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    try {
        storedData data = new storedData(testFile.toString());

        data.deleteMessageByHash("deleteHash");

        String updatedJson = new String(
                Files.readAllBytes(testFile),
                StandardCharsets.UTF_8
        );

        assertFalse(updatedJson.contains("Message to delete"));
        assertFalse(updatedJson.contains("deleteHash"));

        assertTrue(updatedJson.contains("Message to keep"));
        assertTrue(updatedJson.contains("keepHash"));

    } finally {
        Files.deleteIfExists(testFile);
    }
}

                   
@Test
public void testReadMultipleStoredMessagesFromJSON() throws Exception {

    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "["
            + "{"
            + "\"messageID\":\"1234567890\","
            + "\"sender\":\"FirstUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"First stored message\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "},"
            + "{"
            + "\"messageID\":\"0987654321\","
            + "\"sender\":\"SecondUser\","
            + "\"recipient\":\"+27838884567\","
            + "\"messageText\":\"Second stored message\","
            + "\"messageHash\":\"hash2\","
            + "\"messageStatus\":\"Stored\""
            + "}"
            + "]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    try {
        storedData data = new storedData(testFile.toString());

        String firstMessage = data.searchMessageByID("1234567890");
        String secondMessage = data.searchMessageByID("0987654321");
        String report = data.displayFullReport();

        assertTrue(firstMessage.contains("First stored message"));
        assertTrue(firstMessage.contains("FirstUser"));

        assertTrue(secondMessage.contains("Second stored message"));
        assertTrue(secondMessage.contains("SecondUser"));

        assertTrue(report.contains("hash1"));
        assertTrue(report.contains("hash2"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("+27838884567"));

    } finally {
        Files.deleteIfExists(testFile);
    }
}

                @Test
public void testDisplayStoredMessagesPrintsMessageDetails() throws Exception {
    Path testFile = Files.createTempFile("testMessages", ".json");

    String json = "[{"
            + "\"messageID\":\"1234567890\","
            + "\"sender\":\"TestUser\","
            + "\"recipient\":\"+27834557896\","
            + "\"messageText\":\"Stored display test\","
            + "\"messageHash\":\"hash1\","
            + "\"messageStatus\":\"Stored\""
            + "}]";

    Files.write(testFile, json.getBytes(StandardCharsets.UTF_8));

    java.io.PrintStream originalOut = System.out;
    java.io.ByteArrayOutputStream output =
            new java.io.ByteArrayOutputStream();

    try {
        storedData data = new storedData(testFile.toString());

        System.setOut(new java.io.PrintStream(output));
        data.displayStoredMessages();

        String result = output.toString("UTF-8");

        assertTrue(result.contains("STORED MESSAGES"));
        assertTrue(result.contains("1234567890"));
        assertTrue(result.contains("TestUser"));
        assertTrue(result.contains("+27834557896"));
        assertTrue(result.contains("Stored display test"));
        assertTrue(result.contains("Stored"));

    } finally {
        System.setOut(originalOut);
        Files.deleteIfExists(testFile);
    }
}
    
}
    
    

