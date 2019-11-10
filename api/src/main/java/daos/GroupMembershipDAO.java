package daos;

import models.GroupMembership;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GroupMembershipDAO extends DBConnect {

    public int insertGroupMembership(GroupMembership groupMembership) throws SQLException {
        String sql = "INSERT INTO groupMemberships (created, updated, deleted, active, groupId, userId) VALUES (now(), "
                + "now(), false, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, groupMembership.getGroupMembershipId());
        statement.setTimestamp(2, Timestamp.valueOf(groupMembership.getCreated()));
        statement.setTimestamp(3, Timestamp.valueOf(groupMembership.getUpdated()));

        System.out.println(sql);

        boolean rowInserted = statement.executeUpdate() > 0;
        int groupMembershipId = 0;
        ResultSet result = statement.getGeneratedKeys();
        if (result.next()) {
            groupMembershipId = result.getInt(1);
        }

        statement.close();
        disconnect();
        return groupMembershipId;
    }

    public GroupMembership getGroupMembershipByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * from groupMemberships where groupId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        GroupMembership groupMembership = new GroupMembership();
        if (result.next()) {
            groupMembership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            groupMembership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            groupMembership.setDeleted(result.getBoolean("deleted"));
            groupMembership.setActive(result.getBoolean("active"));
            groupMembership.setGroupId(result.getInt("groupId"));
            groupMembership.setUserId(result.getInt("userId"));
        }
        result.close();
        disconnect();

        return groupMembership;
    }

    public GroupMembership getGroupMembershipByUserId(int UserId) throws SQLException {
        String sql = "SELECT * from groupMemberships where UserId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, UserId);

        ResultSet result = statement.executeQuery();

        GroupMembership groupMembership = new GroupMembership();
        if (result.next()) {
            groupMembership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            groupMembership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            groupMembership.setDeleted(result.getBoolean("deleted"));
            groupMembership.setActive(result.getBoolean("active"));
            groupMembership.setGroupId(result.getInt("groupId"));
            groupMembership.setUserId(result.getInt("userId"));
        }
        result.close();
        disconnect();

        return groupMembership;
    }
}
