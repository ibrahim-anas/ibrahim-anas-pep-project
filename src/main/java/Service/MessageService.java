package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import java.util.*;

public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    /**
     * Persists a new message.
     * @param message A message object
     * @return the successfully created message
     */
    public Message createMessage(Message message) {
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && this.accountDAO.getAccountByID(message.getPosted_by()) != null) {
            return messageDAO.insertMessage(message);
        }
        
        return null;
    }
    
    /**
     * Retrieves all messages.
     * @return A list of all messages
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * Retreives a message by message id.
     * @param id The message id
     * @return The succesfully retrieved message
     */
    public Message getMessageByID(int id) {
        return messageDAO.getMessageByID(id);
    }

    /**
     * Deletes a message by message id.
     * @param id The message id
     * @return The successfully deleted message
     */
    public Message deleteMessageByID(int id) {
        if (messageDAO.getMessageByID(id) != null) {
            return messageDAO.deleteMessageByID(id);
        }

        return null;
    }

    /**
     * Updates the message text for a message with specified 'id'.
     * @param id The message id
     * @param text The updated message text
     * @return The successfully updated message
     */
    public Message updateMessageText(int id, String text) {
        if (text.length() > 0 && text.length() <= 255 && messageDAO.getMessageByID(id) != null) {
            return messageDAO.updateMessageText(id, text);
        } 

        return null;
    }

    /**
     * Retrieves a list of all messages posted by a specific user.
     * @param posted_by The user that posted the message
     * @return A list containing all messages posted by the user
     */
    public List<Message> getAllMessagesForUser(int posted_by) {
        return messageDAO.getAllMessagesForUser(posted_by);
    }
}
