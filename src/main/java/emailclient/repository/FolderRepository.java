package emailclient.repository;

import emailclient.model.Folder;
import emailclient.model.enums.FolderType;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class FolderRepository implements Repository<Folder> {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public void add(Folder folder) {
        String sql = "INSERT INTO folders(name,type) VALUES(?,?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, folder.getName());
            stmt.setString(2, folder.getType().name());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Folder folder) {
        String sql = "UPDATE folders SET name=?, type=? WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, folder.getName());
            stmt.setString(2, folder.getType().name());
            stmt.setInt(3, folder.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Folder folder) {
        String sql = "DELETE FROM folders WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, folder.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Folder getById(int id) {
        String sql = "SELECT * FROM folders WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Folder folder = new Folder();
                folder.setId(rs.getInt("id"));
                folder.setName(rs.getString("name"));
                folder.setType(FolderType.valueOf(rs.getString("type")));
                return folder;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Folder> getAll() {
        List<Folder> list = new ArrayList<>();
        String sql = "SELECT * FROM folders";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Folder folder = new Folder();
                folder.setId(rs.getInt("id"));
                folder.setName(rs.getString("name"));
                folder.setType(FolderType.valueOf(rs.getString("type")));
                list.add(folder);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}

