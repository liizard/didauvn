package domain.attribute.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import domain.attribute.dao.MessageDao;
import domain.attribute.model.MessageGeneral;

@Repository
public class MessageDaoImpl extends JdbcDaoSupport implements MessageDao {

	@Autowired
	public MessageDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void insert(long placeId, long userId, int messageType) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO messages(Type,Place,User,CreateDate) VALUES (?,?,?,NOW())";
		getJdbcTemplate().update(sql, messageType, placeId, userId);
	}

	@Override
	public List<MessageGeneral> getMoreMessages(String criteria, long userId,
			long currentMessage,long from, int count) {
		// TODO Auto-generated method stub
		String sql = "SELECT m.Id AS MessageId,p.Id AS PlaceId,p.Name AS PlaceName,u.Name AS UserName,u.Id AS UserId,m.Type,m.CreateDate,u.Avatar from places p INNER JOIN (SELECT * from messages WHERE Place IN "
				+ criteria
				+ " AND User <> ? AND Id < ?) m ON m.Place = p.Id INNER JOIN (select Id,Name,Avatar from users) u ON u.Id = m.User Order By MessageId DESC LIMIT ?,?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				userId, currentMessage, from, count);
		if (!rows.isEmpty()) {
			List<MessageGeneral> messages = new ArrayList<MessageGeneral>(
					rows.size());
			MessageGeneral message = null;
			for (Map<String, Object> row : rows) {
				message = new MessageGeneral();
				message.setMessageId((Long) row.get("MessageId"));
				message.setType((Integer) row.get("Type"));
				message.setPlaceId((Long) row.get("PlaceId"));
				message.setPlacename((String) row.get("PlaceName"));
				message.setUsername((String) row.get("UserName"));
				message.setUserId((Long) row.get("UserId"));
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				message.setCreateDate(df.format((Date) row.get("CreateDate")));
				message.setAvatar((String)row.get("Avatar"));
				messages.add(message);
			}
			return messages;
		} else
			return null;

	}

	@Override
	public void updateCount(long placeId, long userId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE notifications SET count = count + 1 WHERE User IN (SELECT User from Subscribers WHERE Place = ? UNION SELECT User from managers WHERE Place = ? UNION SELECT OWNER from places WHERE Id = ?) AND User <> ?";
		getJdbcTemplate().update(sql, placeId, placeId, placeId, userId);
	}

	@Override
	public long getUnreadMessages(long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT Count from notifications WHERE User = ?";
		return getJdbcTemplate().queryForLong(sql, userId);
	}

	@Override
	public List<MessageGeneral> getMessages(String criteria, long userId,
			long from, int count) {
		// TODO Auto-generated method stub
		String sql = "SELECT m.Id AS MessageId,p.Id AS PlaceId,p.Name AS PlaceName,u.Name AS UserName,u.Id AS UserId,m.Type,m.CreateDate,u.Avatar from places p INNER JOIN (SELECT * from messages WHERE Place IN "
				+ criteria
				+ " AND User <> ?) m ON m.Place = p.Id INNER JOIN (select Id,Name,Avatar from users) u ON u.Id = m.User Order By MessageId DESC LIMIT ?,?";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,
				userId, from, count);
		if (!rows.isEmpty()) {
			List<MessageGeneral> messages = new ArrayList<MessageGeneral>(
					rows.size());
			MessageGeneral message = null;
			for (Map<String, Object> row : rows) {
				message = new MessageGeneral();
				message.setMessageId((Long) row.get("MessageId"));
				message.setType((Integer) row.get("Type"));
				message.setPlaceId((Long) row.get("PlaceId"));
				message.setPlacename((String) row.get("PlaceName"));
				message.setUsername((String) row.get("UserName"));
				message.setUserId((Long) row.get("UserId"));
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				message.setCreateDate(df.format((Date) row.get("CreateDate")));
				message.setAvatar((String)row.get("Avatar"));
				messages.add(message);
			}
			return messages;
		} else
			return null;
	}

	@Override
	public void deleteCount(long userId) {
		// TODO Auto-generated method stub
		String sql = "Update notifications SET count = 0 WHERE User = ?";
		getJdbcTemplate().update(sql,userId);
	}
}
