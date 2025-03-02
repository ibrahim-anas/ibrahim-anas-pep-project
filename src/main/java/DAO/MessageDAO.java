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
                Message message = new Message();

                message.setMessage_id(rs.getInt("message_id"));
                message.setPosted_by(rs.getInt("posted_by"));
                message.setMessage_text(rs.getString("message_text"));
                message.setTime_posted_epoch(rs.getLong("time_posted_epoch"));

                messages.add(message);
            }

            return messages;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

        /**
         * Retrieves a message by its id.
         * @param id Message id
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
}
