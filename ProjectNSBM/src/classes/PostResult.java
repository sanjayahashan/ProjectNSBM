package classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostResult extends Table{
	public int post_id;
	public int sub_id;
	public String result;
	public Boolean payment;

	public int insertRecord()
	{
		String query = "INSERT INTO postresult VALUES(?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, post_id);
			st.setInt(2, sub_id);
			st.setString(3, result);
			st.setBoolean(4, payment);
			
			System.out.println(st);
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateRecord()
	{
		String query = "UPDATE postresult SET result=?, payment=? WHERE post_id=? AND sub_id=?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, result);
			st.setBoolean(2, payment);
			st.setInt(3, post_id);
			st.setInt(4, sub_id);
			
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
