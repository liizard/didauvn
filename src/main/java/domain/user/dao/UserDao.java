/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.user.dao;

import java.text.ParseException;
import java.util.List;

import domain.user.model.User;
import domain.user.model.UserGeneral;

public interface UserDao {

	public long insert(final User user);

	public void update(User user) throws ParseException;

	public void delete(long id);

	public User findById(long id);

	public List<User> get(int from, int count);

	public List<User> get();

	public int count();

	public void insertPassword(long id, String password);

	public User getUserByEmail(String email);

	public boolean checkEmail(String email);

	public void changePassword(long id, String password);

	public UserGeneral findUserViewById(long id);

	public void updateImage(long userId, long imageId);

	public long getImage(long userId);

	public void insertToken(long userId, String token);

	public boolean isValidActivation(long userId, String token);

	public void activateUser(long userId);

	public void deleteInvalidActivation(long userId);

	public void truncateUserActivationTable();

	public boolean isAdmin(long id);

	public void deactivateUser(long userId);

	public long insertForFB(User user);
}
