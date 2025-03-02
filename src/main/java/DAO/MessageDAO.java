package DAO;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.*;

public class MessageDAO {
    
    /**
     * Inserts a message into the message table.
     * @param message
     * @return The successfully added message
     */
    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                int message_id = generatedKeys.getInt("message_id");
                return new Message(message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Retrieves all persisted messages.
     * @return A list of all messages
     */
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                Long time_posted_epoch = rs.getLong("time_posted_epoch");

                messages.add(new Message(message_id, posted_by, message_text, time_posted_epoch));
            }

            return messages;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

        /**
         * Retrieves a message from the message table by a message_id.
         * @param id The message_id
         * @return The successfully retrieved message
         */
    public Message getMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");

                return new Message(id, posted_by, message_text, time_posted_epoch);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Deletes a message from the message table by a message_id.
     * @param id The message_id
     * @return The successfully delted message
     */
    public Message deleteMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            Message message = getMessageByID(id);

            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            return message;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Updates the message_text field for a message by message_id
     * @param id The message_id
     * @param text The updated message_text
     * @return The successfully updated message
     */
    public Message updateMessageText(int id, String text) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, text);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return getMessageByID(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    /**
     * Retrieves all messages posted by a specific user.
     * @param posted_by The user id
     * @return A list containing all messages posted by the user
     */
    public List<Message> getAllMessagesForUser(int posted_by) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, posted_by);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int message_id = rs.getInt("message_id");
                String message_text = rs.getString("message_text");
                Long time_posted_epoch = rs.getLong("time_posted_epoch");

                messages.add(new Message(message_id, posted_by, message_text, time_posted_epoch));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }
}
