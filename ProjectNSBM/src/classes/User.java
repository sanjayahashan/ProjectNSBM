package classes;

import java.util.List;

public class User extends Table
{
	public int id;
	public String username;
	public String password;

	public static List<User> verifyUser(String un, String pwd)
	{
		String clause = "username = \'" + un + "\' AND password = \'" + pwd + "\' LIMIT 1";
		List<User> l = query("user", User.class, clause);
//		System.out.println(clause);
		return l;
	}
}
