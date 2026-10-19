/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package st10509412_part1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

/**
 *
 * @author mphos
 */
public class sendMessageTest {
    
    // Test 1: Message ID is generated correctly
    @Test
        public void testMessageIDIsGenerated() {
            sendMessage message = new sendMessage(0);

            assertNotNull(message.getMessageID());
            assertTrue(message.getMessageID().matches("[0-9]{10}"));
            assertTrue(message.checkMessageID());
    }

    // Test 2: Invalid message ID is rejected
    @Test
        public void testInvalidMessageID() {
            sendMessage message = new sendMessage(0, "12345");

            assertFalse(message.checkMessageID());
    }

    // Test 3: Correct recipient cellphone number
    @Test
        public void testValidRecipientNumber() {
            sendMessage message = new sendMessage(0);
            message.setRecipient("+27718693002");

            assertTrue(message.checkRecipientCell());
    }

    // Test 4: Local number without international code is rejected
    @Test
        public void testInvalidRecipientNumber() {
            sendMessage message = new sendMessage(0);
            message.setRecipient("08575975889");

            assertFalse(message.checkRecipientCell());
    }

    // Test 5: Message within 250 characters
    @Test
        public void testMessageWithin250Characters() {
            sendMessage message = new sendMessage(0);
            message.setMessageText("Hey, how are you?");

            assertTrue(message.checkMessageLength());
    }

    // Test 6: Exactly 250 characters is accepted
    @Test
        public void testMessageExactly250Characters() {
            sendMessage message = new sendMessage(0);
            message.setMessageText(repeatCharacter('A', 250));

            assertTrue(message.checkMessageLength());
    }

    // Test 7: More than 250 characters is rejected
    @Test
        public void testMessageOver250Characters() {
            sendMessage message = new sendMessage(0);
            message.setMessageText(repeatCharacter('A', 251));

            assertFalse(message.checkMessageLength());
    }

    // Test 8: Empty message is rejected
    @Test
        public void testEmptyMessage() {
            sendMessage message = new sendMessage(0);
            message.setMessageText("");

            assertFalse(message.checkMessageLength());
    }

    // Test 9: Exact message hash from the assignment
    @Test
        public void testExactMessageHash() {
            sendMessage message =
                new sendMessage(0, "0012345678");

            message.setMessageText(
                "Hi Mike, can you join us for dinner tonight?"
            );
             message.refreshMessageHash();

            assertEquals(
                "00:0:HITONIGHT",
                message.getMessageHash()
            );
    }

    // Test 10: Sending a message returns the correct response
    @Test
        public void testSendMessageResponse() {
            sendMessage message = new sendMessage(0);
            message.setRecipient("+27718693002");
            message.setMessageText("Hello, Mike!");
            message.refreshMessageHash();

            Scanner input = new Scanner("1\n");

            assertEquals(
                "Message successfully sent.",
                message.sentMessage(input)
            );

            assertEquals("Sent", message.getMessageStatus());
    }

    // Test 11: Disregarding a message returns the required response
    @Test
        public void testDisregardMessageResponse() {
            sendMessage message = new sendMessage(0);
            message.setRecipient("+27718693002");
            message.setMessageText("Hello, Mike!");

            Scanner input = new Scanner("2\n");

            assertEquals(
                "Press 0 to delete the message.",
                message.sentMessage(input)
            );

            assertEquals(
                "Disregarded",
                message.getMessageStatus()
            );
    }

    // Test 12: Storing a message returns the correct response
    @Test
        public void testStoreMessageResponse() {
            sendMessage message = new sendMessage(0);
            message.setRecipient("+27718693002");
            message.setMessageText("Hello, Mike!");
            message.refreshMessageHash();

            Scanner input = new Scanner("3\n");

            assertEquals(
                "Message successfully stored.",
                message.sentMessage(input)
            );

            assertEquals("Stored", message.getMessageStatus());
    }

    // Test 13: Total sent message count increases after sending
    @Test
        public void testTotalMessagesSentIncreases() {
            int totalBefore = sendMessage.returnTotalMessages();

            sendMessage message = new sendMessage(0);
            message.setRecipient("+27718693002");
            message.setMessageText("Testing message count");
            message.refreshMessageHash();

            Scanner input = new Scanner("1\n");
            message.sentMessage(input);

            assertEquals(
                totalBefore + 1,
                sendMessage.returnTotalMessages()
            );
    }
        
        
    // Test 14: Invalid numeric choice does not send the message
    @Test
    public void testInvalidNumericChoice() {
        sendMessage message = new sendMessage(0);
        message.setMessageText("Testing invalid choice");

        Scanner input = new Scanner("4\n");

        int totalBefore = sendMessage.returnTotalMessages();

        assertEquals(
            "Invalid choice. Message not processed.",
            message.sentMessage(input)
        );

        assertEquals("Pending", message.getMessageStatus());
        assertEquals(
            totalBefore,
            sendMessage.returnTotalMessages()
        );
    }

    // Test 15: Non-numeric choice does not send the message
    @Test
    public void testNonNumericChoice() {
        sendMessage message = new sendMessage(0);
        message.setMessageText("Testing non-numeric choice");

        Scanner input = new Scanner("hello\n");

        int totalBefore = sendMessage.returnTotalMessages();

        assertEquals(
            "Invalid choice. Message not processed.",
            message.sentMessage(input)
        );

        assertEquals("Pending", message.getMessageStatus());
        assertEquals(
            totalBefore,
            sendMessage.returnTotalMessages()
        );
    }


    // Helper method for creating messages of a specific length
        
        private String repeatCharacter(char character, int count) {
            StringBuilder result = new StringBuilder();

        for (int i = 0; i < count; i++) {
            result.append(character);
        }

        return result.toString();
    }
}


