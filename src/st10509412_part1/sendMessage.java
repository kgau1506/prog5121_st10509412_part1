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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class sendMessage {
     private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String messageStatus;
    
    // Stores messages that have been sent
    private static  ArrayList<sendMessage> sentMessages = new ArrayList<>();

    // Stores messages that have been disregarded
    private static  ArrayList<sendMessage> disregardedMessages = new ArrayList<>();

    // Stores messages saved for later.
    private static ArrayList<sendMessage> storedMessages = new ArrayList<>();

    // Counts the total number of messages sent
    private static int totalMessages = 0;
    
    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    
    public sendMessage(int messageNumber) {
        
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
        this.recipient = "";
        this.messageText = "";
        this.messageHash = "";
        this.messageStatus = "Pending";
    }
    
   
    public sendMessage(int messageNumber, String messageID) {
        this.messageNumber = messageNumber;
        this.messageID = messageID;
        this.recipient = "";
        this.messageText = "";
        this.messageHash = "";
        this.messageStatus = "Pending";
}

    
    // =================================================
    // GENERATE MESSAGE ID
    // =================================================
    
    private String generateMessageID() {
        Random random = new Random();

        long id = 1000000000L + random.nextInt(900000000);

        return String.valueOf(id);
    }
    
    // Checks that the message ID is not more than 10 characters
    
    
    public boolean checkMessageID() {
    return messageID != null
            && messageID.matches("[0-9]{10}");
}

    
    // Checks the recipient's cellphone number
    
    public boolean checkRecipientCell() {
        return recipient != null
                && recipient.matches("^\\+27[6-8][0-9]{8}$");
    }
    
    // Checks that the message is not empty and is at most 250 characters
    
    public boolean checkMessageLength() {
        return messageText != null
                && !messageText.trim().isEmpty()
                && messageText.length() <= 250;
    }
    
     //Creates and returns the message hash automatically
    
        
    public String createMessageHash() {

        if (messageID == null || messageID.length() < 2) {
        return "";
    }

            String firstTwoNumbers = messageID.substring(0, 2);
            String firstWord = "";
            String lastWord = "";

        if (messageText != null && !messageText.trim().isEmpty()) {
            String[] words = messageText.trim().split("\\s+");

            firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
            lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");
    }

        return (firstTwoNumbers + ":" + messageNumber + ":"
            + firstWord + lastWord).toUpperCase();
    }


        
        // Allows the user to send, disregard, or store the message
        
         
           public String sentMessage(Scanner input) {
               
                String choiceText = input.nextLine();
                int choice;

            try {
                
                choice = Integer.parseInt(choiceText);
                
            } catch (NumberFormatException e) {
                
                messageStatus = "Pending";
                
            return "Invalid choice. Message not processed.";
    }

            switch (choice) {
                
                case 1:
                    messageStatus = "Sent";

                if (!sentMessages.contains(this)) {
                    
                sentMessages.add(this);
                totalMessages++;
            }

                return "Message successfully sent.";

                
                case 2:
                    messageStatus = "Disregarded";

                if (!disregardedMessages.contains(this)) {
                    disregardedMessages.add(this);
            }

                return "Press 0 to delete the message.";

                case 3:
                    
                if (storeMessage()) {
                    
                return "Message successfully stored.";
            }

                return "Message could not be stored.";

               default:
                   
                    messageStatus = "Pending";
                return "Invalid choice. Message not processed.";
    }
}

        
         // Returns all messages sent during the current program run
         
        public static String printMessages() {

        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }

        StringBuilder output = new StringBuilder();

        for (sendMessage message : sentMessages) {
            output.append("Message ID: ")
                    .append(message.messageID).append("\n")
                    .append("Message Hash: ")
                    .append(message.messageHash).append("\n")
                    .append("Recipient: ")
                    .append(message.recipient).append("\n")
                    .append("Message: ")
                    .append(message.messageText).append("\n")
                    .append("Status: ")
                    .append(message.messageStatus).append("\n\n");
        }

        return output.toString();
    }
        
        // Returns the total number of messages sent
        
        public static int returnTotalMessages() {
        return totalMessages;
    }
    
       // 7. Stores messages in a JSON file
        
    public boolean storeMessage() {

        String previousStatus = messageStatus;
        messageStatus = "Stored";

        if (!storedMessages.contains(this)) {
            storedMessages.add(this);
        }

        StringBuilder json = new StringBuilder("[\n");

        for (int i = 0; i < storedMessages.size(); i++) {
            json.append(storedMessages.get(i).toJson());

            if (i < storedMessages.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        try {
            Files.write(
                    Paths.get("messages.json"),
                    json.toString().getBytes("UTF-8")
            );

            return true;

        } catch (IOException e) {
            messageStatus = previousStatus;
            storedMessages.remove(this);

            System.out.println("Error storing message: "
                    + e.getMessage());

            return false;
        }
    }

    // Converts the message details into JSON format
    
    private String toJson() {

        return "  {\n"
                + "    \"messageID\": \"" + escapeJson(messageID) + "\",\n"
                + "    \"messageNumber\": " + messageNumber + ",\n"
                + "    \"recipient\": \"" + escapeJson(recipient) + "\",\n"
                + "    \"messageText\": \"" + escapeJson(messageText) + "\",\n"
                + "    \"messageHash\": \"" + escapeJson(messageHash) + "\",\n"
                + "    \"messageStatus\": \"" + escapeJson(messageStatus) + "\"\n"
                + "  }";
    }

    // Handles special characters inside JSON text
    
    private String escapeJson(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Getters allow other classes to access message details
    
        public String getMessageID() {
            return messageID;
    }

        public int getMessageNumber() {
            return messageNumber;
    }

        public String getRecipient() {
            return recipient;
    }

        public String getMessageText() {
            return messageText;
    }

        public String getMessageHash() {
            return messageHash;
    }

        public String getMessageStatus() {
            return messageStatus;
    }
        
        
        public void setRecipient(String recipient) {
            this.recipient = recipient;
    }

        public void setMessageText(String messageText) {
            this.messageText = messageText;
    }

        public void refreshMessageHash() {
            this.messageHash = createMessageHash();
    }

 

    
}
