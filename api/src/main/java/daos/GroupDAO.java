package daos;

import models.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;

public class GroupDAO extends DBConnect {

    public boolean insertGroup(Group group) throws SQLException {
        String sql = "INSERT INTO groups (created, updated, deleted, groupName, classId, startDatetime) VALUES (now(), " +
                "now(), ?, ?, ?, ?";
        connect();

        System.out.println(sql);

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setBoolean(1, group.isDeleted());
        statement.setString(3, group.getGroupName());
        statement.setInt(5, group.getClassId());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public Group getGroupByGroupName(String groupName) throws SQLException {
        String sql = "SELECT * from groups where name = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, groupName);

        ResultSet result = statement.executeQuery();

        Group group = new Group();
        if (result.next()) {
            group.setCreated(new Timestamp(result.getDate("created").getTime()).toLocalDateTime());
            group.setUpdated(new Timestamp(result.getDate("updated").getTime()).toLocalDateTime());
            group.setDeleted(result.getBoolean("deleted"));
            group.setGroupName(result.getString("name"));
            group.setClassId(result.getInt("classId"));
            group.setGroupId(result.getInt("groupId"));
        }
        result.close();
        disconnect();

        return group;
    }
}