package daos;

import models.Group;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends DBConnect {

    public int insertGroup(Group group) throws SQLException {
        String sql = "INSERT INTO groups (created, updated, deleted, name, classId, startDate, endDate) VALUES (now(), " +
                "now(), false, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, group.getGroupName());
        statement.setInt(2, group.getClassId());
        statement.setTimestamp(3, Timestamp.valueOf(group.getStartDate()));
        statement.setTimestamp(4, Timestamp.valueOf(group.getEndDate()));

        System.out.println(sql);

        boolean rowInserted = statement.executeUpdate() > 0;
        int groupId = 0;
        ResultSet result = statement.getGeneratedKeys();
        if (result.next()) {
            groupId = result.getInt(1);
        }

        statement.close();
        disconnect();
        return groupId;
    }

    public List<Group> getGroupsByName(String groupName) throws SQLException {
        String sql = "SELECT * from groups where name like ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, "%" + groupName + "%");

        ResultSet result = statement.executeQuery();

        List<Group> groups = new ArrayList<Group>();
        int count = 0;
        while (result.next()) {
            Group group = new Group();
            group.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            group.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            group.setDeleted(result.getBoolean("deleted"));
            group.setGroupName(result.getString("name"));
            group.setClassId(result.getInt("classId"));
            group.setGroupId(result.getInt("groupId"));
            group.setStartDate(new Timestamp(result.getDate("startDate").getTime()).toLocalDateTime());
            group.setEndDate(new Timestamp(result.getDate("endDate").getTime()).toLocalDateTime());
            System.out.println(result.getString("name"));
            groups.add(group);
            ++count;
        }

        if (count == 0) {
            throw new SQLException("No group found with a name containing the term " + groupName);
        }

        result.close();
        disconnect();

        return groups;
    }

    public List<Group> getGroupsByClassId(int classId) throws SQLException {
        String sql = "SELECT * from groups where classId = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, classId);

        ResultSet result = statement.executeQuery();

        List<Group> groups = new ArrayList<Group>();
        int count = 0;
        while (result.next()) {
            Group group = new Group();
            group.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            group.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            group.setDeleted(result.getBoolean("deleted"));
            group.setGroupName(result.getString("name"));
            group.setClassId(result.getInt("classId"));
            group.setGroupId(result.getInt("groupId"));
            group.setStartDate(new Timestamp(result.getDate("startDate").getTime()).toLocalDateTime());
            group.setEndDate(new Timestamp(result.getDate("endDate").getTime()).toLocalDateTime());
            System.out.println(result.getString("name"));
            groups.add(group);
            ++count;
        }

        if (count == 0) {
            throw new SQLException("No group found with that class");
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