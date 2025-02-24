package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public Message createMessage(Message message) {
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && this.accountDAO.getAccountByID(message.getPosted_by()) != null) {
            return messageDAO.insertMessage(message);
        }
        
        return null;
    }
}
