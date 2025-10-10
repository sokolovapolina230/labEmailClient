package emailclient.repository;

import emailclient.model.Message;
import emailclient.model.enums.Priority;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements Repository<Message> {
    private final DatabaseManager dbManager = new DatabaseManager();

    @Override
    public void add(Message msg) {
        String sql = "INSERT INTO messages(accountId,sender,recipient,subject,body,priority) VALUES(?,?,?,?,?,?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, msg.getAccountId());
            stmt.setString(2, msg.getSender());
            stmt.setString(3, msg.getRecipient());
            stmt.setString(4, msg.getSubject());
            stmt.setString(5, msg.getBody());
            stmt.setString(6, msg.getPriority() != null ? msg.getPriority().name() : null);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Message msg) {
        String sql = "UPDATE messages SET accountId=?, sender=?, recipient=?, subject=?, body=?, priority=? WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, msg.getAccountId());
            stmt.setString(2, msg.getSender());
            stmt.setString(3, msg.getRecipient());
            stmt.setString(4, msg.getSubject());
            stmt.setString(5, msg.getBody());
            stmt.setString(6, msg.getPriority() != null ? msg.getPriority().name() : null);
            stmt.setInt(7, msg.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Message msg) {
        String sql = "DELETE FROM messages WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, msg.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Message getById(int id) {
        String sql = "SELECT * FROM messages WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Message msg = new Message();
                msg.setId(rs.getInt("id"));
                msg.setAccountId(rs.getInt("accountId"));
                msg.setSender(rs.getString("sender"));
                msg.setRecipient(rs.getString("recipient"));
                msg.setSubject(rs.getString("subject"));
                msg.setBody(rs.getString("body"));
                String priority = rs.getString("priority");
                msg.setPriority(priority != null ? Priority.valueOf(priority) : null);
                return msg;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Message> getAll() {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM messages";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Message msg = new Message();
                msg.setId(rs.getInt("id"));
                msg.setAccountId(rs.getInt("accountId"));
                msg.setSender(rs.getString("sender"));
                msg.setRecipient(rs.getString("recipient"));
                msg.setSubject(rs.getString("subject"));
                msg.setBody(rs.getString("body"));
                String priority = rs.getString("priority");
                msg.setPriority(priority != null ? Priority.valueOf(priority) : null);
                list.add(msg);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
