package emailclient.repository;

import emailclient.model.Attachment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttachmentRepository implements Repository<Attachment> {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public void add(Attachment att) {
        String sql = "INSERT INTO attachments(messageId,fileName,data) VALUES(?,?,?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, att.getMessageId());
            stmt.setString(2, att.getFileName());
            stmt.setBytes(3, att.getData());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Attachment att) {
        String sql = "UPDATE attachments SET messageId=?, fileName=?, data=? WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, att.getMessageId());
            stmt.setString(2, att.getFileName());
            stmt.setBytes(3, att.getData());
            stmt.setInt(4, att.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Attachment att) {
        String sql = "DELETE FROM attachments WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, att.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Attachment getById(int id) {
        String sql = "SELECT * FROM attachments WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Attachment att = new Attachment();
                att.setId(rs.getInt("id"));
                att.setMessageId(rs.getInt("messageId"));
                att.setFileName(rs.getString("fileName"));
                att.setData(rs.getBytes("data"));
                return att;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Attachment> getAll() {
        List<Attachment> list = new ArrayList<>();
        String sql = "SELECT * FROM attachments";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Attachment att = new Attachment();
                att.setId(rs.getInt("id"));
                att.setMessageId(rs.getInt("messageId"));
                att.setFileName(rs.getString("fileName"));
                att.setData(rs.getBytes("data"));
                list.add(att);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
