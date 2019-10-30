package daos;

import models.Group;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends DBConnect {

    public boolean insertGroup(Group group) throws SQLException {
        String sql = "INSERT INTO groups (created, updated, deleted, groupName, classId, startDate, endDate) VALUES (now(), " +
                "now(), ?, ?, ?, ?, ?";
        connect();

        System.out.println(sql);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setBoolean(1, group.isDeleted());
        statement.setString(2, group.getGroupName());
        statement.setInt(3, group.getClassId());
        statement.setTimestamp(4, Timestamp.valueOf(group.getStartDate()));
        statement.setTimestamp(5, Timestamp.valueOf(group.getEndDate()));

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Group> getGroupsByName(String groupName) throws SQLException {
        String sql = "SELECT * from groups where name = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, groupName);

        ResultSet result = statement.executeQuery();

        List<Group> groups = new ArrayList<Group>();
        if (result.next()) {
            Group group = new Group();
            group.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            group.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            group.setDeleted(result.getBoolean("deleted"));
            group.setGroupName(result.getString("name"));
            group.setClassId(result.getInt("classId"));
            group.setGroupId(result.getInt("groupId"));
            group.setStartDate(new Timestamp(result.getDate("startDate").getTime()).toLocalDateTime());
            group.setEndDate(new Timestamp(result.getDate("endDate").getTime()).toLocalDateTime());
            groups.add(group);
        }
        result.close();
        disconnect();

        return groups;
    }

    public Group getGroupById(int id) throws SQLException {
        String sql = "SELECT * from groups where groupId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        Group group = new Group();
        if (result.next()) {
            group.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            group.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            group.setDeleted(result.getBoolean("deleted"));
            group.setGroupName(result.getString("name"));
            group.setClassId(result.getInt("classId"));
            group.setGroupId(result.getInt("groupId"));
            group.setStartDate(new Timestamp(result.getDate("startDate").getTime()).toLocalDateTime());
            group.setEndDate(new Timestamp(result.getDate("endDate").getTime()).toLocalDateTime());
        }
        result.close();
        disconnect();

        return group;
    }

}