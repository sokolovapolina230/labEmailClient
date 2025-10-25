package emailclient.repository;

import emailclient.model.Account;
import emailclient.model.enums.ProtocolType;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {
    private final DatabaseManager dbManager = DatabaseManager.getInstance();

    @Override
    public void add(Account acc) {
        String sql = "INSERT INTO accounts(userId,email,password,protocol) VALUES(?,?,?,?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, acc.getUserId());
            stmt.setString(2, acc.getEmail());
            stmt.setString(3, acc.getPassword());
            stmt.setString(4, acc.getProtocol().name());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(Account acc) {
        String sql = "UPDATE accounts SET userId=?, email=?, password=?, protocol=? WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, acc.getUserId());
            stmt.setString(2, acc.getEmail());
            stmt.setString(3, acc.getPassword());
            stmt.setString(4, acc.getProtocol().name());
            stmt.setInt(5, acc.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(Account acc) {
        String sql = "DELETE FROM accounts WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, acc.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Account getById(int id) {
        String sql = "SELECT * FROM accounts WHERE id=?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("userId"));
                acc.setEmail(rs.getString("email"));
                acc.setPassword(rs.getString("password"));
                acc.setProtocol(ProtocolType.valueOf(rs.getString("protocol")));
                return acc;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                acc.setUserId(rs.getInt("userId"));
                acc.setEmail(rs.getString("email"));
                acc.setPassword(rs.getString("password"));
                acc.setProtocol(ProtocolType.valueOf(rs.getString("protocol")));
                list.add(acc);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}

