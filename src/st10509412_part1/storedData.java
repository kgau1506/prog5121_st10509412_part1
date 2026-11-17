/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10509412_part1;

/**
 *
 * @author mphos
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class storedData {

    // Location of the JSON file
    private String jsonFile = "messages.json";

    // Five arrays required for Part 3
    private String[] sentMessages = new String[0];
    private String[] disregardedMessages = new String[0];
    private String[] storedMessages = new String[0];
    private String[] messageHash = new String[0];
    private String[] messageID = new String[0];

    // Additional arrays for message details
    private String[] recipients = new String[0];
    private String[] senders = new String[0];
    private String[] statuses = new String[0];

    // Constructor
    public storedData() {

        sentMessages = convertMessages(sendMessage.getSentMessages());

        disregardedMessages = convertMessages(
                sendMessage.getDisregardedMessages());

        storedMessages = convertMessages(
                sendMessage.getStoredMessages());

        messageHash = sendMessage.getMessageHashes().toArray(new String[0]);

        messageID = sendMessage.getMessageIDs().toArray(new String[0]);

        // Load previously saved messages
        readStoredMessages();
    }
    
    // Constructor used for testing with a separate JSON file
    public storedData(String fileName) {
        jsonFile = fileName;
        readStoredMessages();
    }

    // Converts message objects into a String array
    private String[] convertMessages(ArrayList<sendMessage> messages) {

        String[] messageArray = new String[messages.size()];

        for (int i = 0; i < messages.size(); i++) {

            messageArray[i] = messages.get(i).getMessageText();
        }

        return messageArray;
    }

    // Reads stored messages from the JSON file
    public void readStoredMessages() {

        try {

            JSONParser parser = new JSONParser();

            Object data = parser.parse(
                    new String(Files.readAllBytes(Paths.get(jsonFile)),
                            StandardCharsets.UTF_8));

            JSONArray messages = (JSONArray) data;

            storedMessages = new String[messages.size()];
            recipients = new String[messages.size()];
            senders = new String[messages.size()];
            statuses = new String[messages.size()];
            messageHash = new String[messages.size()];
            messageID = new String[messages.size()];

            for (int i = 0; i < messages.size(); i++) {

                JSONObject message = (JSONObject) messages.get(i);

                storedMessages[i] = (String) message.get("messageText");
                recipients[i] = (String) message.get("recipient");
                statuses[i] = (String) message.get("messageStatus");
                messageHash[i] = (String) message.get("messageHash");
                messageID[i] = (String) message.get("messageID");

                // Sender information is not currently saved in the JSON file
                senders[i] = (String) message.get("sender");

                if (senders[i] == null) {
                    senders[i] = "Unknown (older message)";
                }
            }

            System.out.println("Stored messages loaded successfully.");

        } catch (IOException | org.json.simple.parser.ParseException e) {

            System.out.println("Error reading stored messages: "
                    + e.getMessage());
        }
    }

    // Displays messages loaded from the JSON file
    public void displayStoredMessages() {

        System.out.println("\nSTORED MESSAGES");

        for (int i = 0; i < storedMessages.length; i++) {

            System.out.println("\nMessage " + (i + 1));
            System.out.println("Message ID: " + messageID[i]);
            System.out.println("Message Hash: " + messageHash[i]);
            System.out.println("Sender: " + senders[i]);
            System.out.println("Recipient: " + recipients[i]);
            System.out.println("Message: " + storedMessages[i]);
            System.out.println("Status: " + statuses[i]);
        }
    }
    
    // Finds the longest message in a String array
    public String findLongestMessage(String[] messages) {

        if (messages.length == 0) {
            return "No messages available.";
        }

        String longestMessage = messages[0];

        for (int i = 1; i < messages.length; i++) {

            if (messages[i].length() > longestMessage.length()) {
                longestMessage = messages[i];
            }
        }

        return longestMessage;
    }

    // Finds and displays the longest stored message
    public String displayLongestMessage() {

        if (storedMessages.length == 0) {
            return "No stored messages available.";
        }

        String longestMessage = findLongestMessage(storedMessages);

        int longestIndex = 0;

        for (int i = 0; i < storedMessages.length; i++) {

            if (storedMessages[i].equals(longestMessage)) {
                longestIndex = i;
                break;
            }
        }

        return "Longest Message: " + storedMessages[longestIndex]
                + "\nSender: " + senders[longestIndex]
                + "\nRecipient: " + recipients[longestIndex]
                + "\nMessage ID: " + messageID[longestIndex]
                + "\nMessage Hash: " + messageHash[longestIndex];
    }
    
    // Searches for a message using its message ID
    public String searchMessageByID(String searchID) {

        for (int i = 0; i < messageID.length; i++) {

            if (messageID[i].equals(searchID)) {

                return "Message found!"
                        + "\nMessage ID: " + messageID[i]
                        + "\nSender: " + senders[i]
                        + "\nRecipient: " + recipients[i]
                        + "\nMessage: " + storedMessages[i]
                        + "\nMessage Hash: " + messageHash[i]
                        + "\nStatus: " + statuses[i];
            }
        }

        return "Message not found.";
    }
    
    // Finds a message using its ID in the given arrays
    public String findMessageByID(String searchID, String[] ids, String[] messages) {

        for (int i = 0; i < ids.length; i++) {

            if (ids[i].equals(searchID)) {
                return messages[i];
            }
        }

        return "Message not found.";
    }
    
    // Finds all messages belonging to a recipient
    public String searchMessagesByRecipient(String searchRecipient) {

        String result = "";
        boolean found = false;

        for (int i = 0; i < recipients.length; i++) {

            if (recipients[i].equals(searchRecipient)) {

                result += "\nMessage ID: " + messageID[i]
                        + "\nSender: " + senders[i]
                        + "\nRecipient: " + recipients[i]
                        + "\nMessage: " + storedMessages[i]
                        + "\nMessage Hash: " + messageHash[i]
                        + "\nStatus: " + statuses[i] + "\n";

                found = true;
            }
        }

        if (!found) {
            return "No messages found for this recipient.";
        }

        return result;
    }
    
    // Searches a supplied array for messages belonging to a recipient
    public String searchMessagesByRecipient(
            String searchRecipient, String[] testRecipients, String[] testMessages) {

        String result = "";

        for (int i = 0; i < testRecipients.length; i++) {

            if (testRecipients[i].equals(searchRecipient)) {
                result += testMessages[i] + "\n";
            }
        }

        return result;
    }
    
    // Deletes a stored message using its message hash
    public String deleteMessageByHash(String searchHash) {

        for (int i = 0; i < messageHash.length; i++) {

            if (messageHash[i].equals(searchHash)) {

                String deletedMessage = storedMessages[i];

                // Remove the message from the JSON file
                if (removeMessageFromJSON(searchHash)) {
                    readStoredMessages();

                    return "Message deleted successfully: " + deletedMessage;
                } else {
                    return "Message could not be deleted.";
                }
            }
        }

        return "Message hash not found.";
    }
    
    // Removes a message from the JSON file using its hash
    private boolean removeMessageFromJSON(String searchHash) {

        try {
            JSONParser parser = new JSONParser();

            Object data = parser.parse(
                new String(Files.readAllBytes(Paths.get(jsonFile)),
                        StandardCharsets.UTF_8));

            JSONArray messages = (JSONArray) data;

            boolean found = false;

            for (int i = 0; i < messages.size(); i++) {

                JSONObject message = (JSONObject) messages.get(i);

                String hash = (String) message.get("messageHash");

                if (searchHash.equals(hash)) {
                    messages.remove(i);
                    found = true;
                    break;
                }
            }

            if (found) {
                Files.write(
                    Paths.get(jsonFile),
                    messages.toJSONString().getBytes(StandardCharsets.UTF_8));

                return true;
            }

        } catch (IOException | org.json.simple.parser.ParseException e) {

            System.out.println("Error deleting message: " + e.getMessage());
        }

        return false;
    }
    
    // Displays a full report of all stored messages
    public String displayFullReport() {

        String report = "\nFULL MESSAGE REPORT\n";

        for (int i = 0; i < storedMessages.length; i++) {

            report += "\nMessage " + (i + 1)
                    + "\nMessage ID: " + messageID[i]
                    + "\nMessage Hash: " + messageHash[i]
                    + "\nSender: " + senders[i]
                    + "\nRecipient: " + recipients[i]
                    + "\nMessage: " + storedMessages[i]
                    + "\nStatus: " + statuses[i]
                    + "\n-------------------------";
        }

        if (storedMessages.length == 0) {
            return "No stored messages available.";
        }

        return report;
    }
    
}
