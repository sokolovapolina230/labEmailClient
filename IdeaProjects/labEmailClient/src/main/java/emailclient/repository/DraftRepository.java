package emailclient.repository;

import emailclient.model.Draft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DraftRepository implements Repository<Draft> {
    private final DatabaseManager dbManager = new DatabaseManager();

    @Override
    public void add(Draft draft) {
        String sql = "INSERT INTO drafts(accountId,subject,body) VALUES(?,?,?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, draft.getAccountId());
            stmt.setString(2, draft.getSubject());
            stmt.setString(3, draft.getBody());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Draft draft) {
        String sql = "UPDATE drafts SET accountId=?, subject=?, body=? WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, draft.getAccountId());
            stmt.setString(2, draft.getSubject());
            stmt.setString(3, draft.getBody());
            stmt.setInt(4, draft.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Draft draft) {
        String sql = "DELETE FROM drafts WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, draft.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Draft getById(int id) {
        String sql = "SELECT * FROM drafts WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Draft draft = new Draft();
                draft.setId(rs.getInt("id"));
                draft.setAccountId(rs.getInt("accountId"));
                draft.setSubject(rs.getString("subject"));
                draft.setBody(rs.getString("body"));
                return draft;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Draft> getAll() {
        List<Draft> list = new ArrayList<>();
        String sql = "SELECT * FROM drafts";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Draft draft = new Draft();
                draft.setId(rs.getInt("id"));
                draft.setAccountId(rs.getInt("accountId"));
                draft.setSubject(rs.getString("subject"));
                draft.setBody(rs.getString("body"));
                list.add(draft);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
