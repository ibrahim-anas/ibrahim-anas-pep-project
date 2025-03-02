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
        return messageDAO.deleteMessageByID(id);
    }
}
