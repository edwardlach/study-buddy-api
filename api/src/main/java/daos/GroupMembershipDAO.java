package daos;

import models.Group;
import models.GroupMembership;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupMembershipDAO extends DBConnect {

    public int insertGroupMembership(GroupMembership groupMembership) throws SQLException {
        String sql = "INSERT INTO groupMemberships (created, updated, deleted, active, groupId, userId) VALUES (now(), "
                + "now(), false, true, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, groupMembership.getGroupId());
        statement.setInt(2, groupMembership.getUserId());

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

    public boolean removeGroupMembership(GroupMembership membership) throws SQLException{
        String sql = "UPDATE groupMemberships set deleted = true where groupMembership = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, membership.getGroupMembership());
        boolean rowUpdated = statement.executeUpdate() > 0;

        statement.close();
        disconnect();
        return rowUpdated;
    }

    public GroupMembership getGroupMembershipById(int groupMembership) throws SQLException {
        String sql = "SELECT * FROM groupMemberships where groupMembership = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupMembership);

        ResultSet result = statement.executeQuery();

        GroupMembership membership = new GroupMembership();

        if (result.next()) {
            membership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            membership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            membership.setDeleted(result.getBoolean("deleted"));
            membership.setActive(result.getBoolean("active"));
            membership.setGroupId(result.getInt("groupId"));
            membership.setUserId(result.getInt("userId"));
            membership.setGroupMembership(result.getInt("groupMembership"));
        }
        result.close();
        disconnect();

        return membership;
    }

    public GroupMembership getGroupMembershipByUserAndGroup(int userId, int groupId) throws SQLException {
        String sql = "SELECT * FROM groupMemberships where userId = ? and groupId = ? and deleted = false";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, groupId);

        ResultSet result = statement.executeQuery();

        GroupMembership membership = new GroupMembership();

        if (result.next()) {
            membership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            membership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            membership.setDeleted(result.getBoolean("deleted"));
            membership.setActive(result.getBoolean("active"));
            membership.setGroupId(result.getInt("groupId"));
            membership.setUserId(result.getInt("userId"));
            membership.setGroupMembership(result.getInt("groupMembership"));
        }
        result.close();
        disconnect();

        return membership;
    }


    public List<GroupMembership> getGroupMembershipsByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * from groupMemberships where groupId = ? and deleted = false";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        List<GroupMembership> groupMemberships = new ArrayList<GroupMembership>();
        int count = 0;
        while (result.next()) {
            GroupMembership groupMembership = new GroupMembership();
            groupMembership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            groupMembership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            groupMembership.setDeleted(result.getBoolean("deleted"));
            groupMembership.setActive(result.getBoolean("active"));
            groupMembership.setGroupId(result.getInt("groupId"));
            groupMembership.setUserId(result.getInt("userId"));
            groupMembership.setGroupMembership(result.getInt("groupMembership"));
            groupMemberships.add(groupMembership);
            count++;
        }

        if (count == 0) {
            throw new SQLException("No active groupMemberships found for group");
        }

        List<GroupMembership> activeMemberships = new ArrayList<>();
        for (GroupMembership g:groupMemberships){
            if (g.isDeleted() == false){
                activeMemberships.add(g);
            }
        }

        result.close();
        disconnect();

        return activeMemberships;
    }

    public List<GroupMembership> getGroupMembershipsByUserId(int userId) throws SQLException {
        String sql = "SELECT * from groupMemberships where userId = ? and deleted = false";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        List<GroupMembership> groupMemberships = new ArrayList<GroupMembership>();
        int count = 0;
        while (result.next()) {
            GroupMembership groupMembership = new GroupMembership();
            groupMembership.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            groupMembership.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            groupMembership.setDeleted(result.getBoolean("deleted"));
            groupMembership.setActive(result.getBoolean("active"));
            groupMembership.setGroupId(result.getInt("groupId"));
            groupMembership.setUserId(result.getInt("userId"));
            groupMembership.setGroupMembership(result.getInt("groupMembership"));
            groupMemberships.add(groupMembership);
            count++;
        }

        if (count == 0) {
            throw new SQLException("No active groupMemberships found for user");
        }

        List<GroupMembership> activeMemberships = new ArrayList<>();
        for (GroupMembership g:groupMemberships){
            if (g.isDeleted() == false){
                activeMemberships.add(g);
            }
        }

        result.close();
        disconnect();

        return activeMemberships;
    }

    public List<GroupMembership> getDetailedGroupMembershipsByGroupId(int groupId) throws SQLException {
        String sql = "SELECT u.*, " +
                        "gm.created as membershipCreated, " +
                        "gm.updated as membershipUpdated, " +
                        "gm.deleted as membershipDeleted, " +
                        "gm.active, " +
                        "gm.groupId, " +
                        "gm.groupMembership " +
                     "FROM groupMemberships gm " +
                     "JOIN users u on gm.userId = u.userId " +
                     "WHERE gm.groupId = ? " +
                     "AND gm.deleted = false " +
                     "AND u.deleted = false";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, groupId);

        ResultSet result = statement.executeQuery();

        List<GroupMembership> groupMemberships = new ArrayList<GroupMembership>();
        int count = 0;
        while (result.next()) {
            User user = new User();
            GroupMembership groupMembership = new GroupMembership();

            user.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            user.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            user.setDeleted(result.getBoolean("deleted"));
            user.setFirstName(result.getString("firstName"));
            user.setLastName(result.getString("lastName"));
            user.setEmail(result.getString("email"));
            user.setEducationLevel(result.getInt("educationLevel"));
            user.setUniversityId(result.getInt("universityId"));
            user.setUserId(result.getInt("userId"));
            groupMembership.setCreated(new Timestamp(result.getDate("membershipCreated").getTime()).toLocalDateTime());
            groupMembership.setUpdated(new Timestamp(result.getDate("membershipUpdated").getTime()).toLocalDateTime());
            groupMembership.setDeleted(result.getBoolean("membershipDeleted"));
            groupMembership.setActive(result.getBoolean("active"));
            groupMembership.setGroupId(result.getInt("groupId"));
            groupMembership.setUserId(result.getInt("userId"));
            groupMembership.setGroupMembership(result.getInt("groupMembership"));
            groupMembership.setUser(user);

            groupMemberships.add(groupMembership);
            count++;
        }

        if (count == 0) {
            throw new SQLException("No active groupMemberships found for user");
        }

        result.close();
        disconnect();

        return groupMemberships;
    }

}
