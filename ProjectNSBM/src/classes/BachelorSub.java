package classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BachelorSub extends Table{
	public int bachcourse_id;
	public int sub_id;
	public int prev_bachcourse_id;
	public int prev_sub_id;
	
	public int getBachcourse_id() {
		return bachcourse_id;
	}

	public void setBachcourse_id(int bachcourse_id) {
		prev_bachcourse_id = this.bachcourse_id;
		this.bachcourse_id = bachcourse_id;
	}

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		prev_sub_id = this.sub_id;
		this.sub_id = sub_id;
	}

	public int insertRecord()
	{
		String query = "INSERT INTO bachelorsub VALUES (?,?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, bachcourse_id);
			st.setInt(2, sub_id);
			
			return st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateRecord()
	{
		String query = "UPDATE bachelorsub SET bachcourse_id=?, sub_id=? WHERE bachcourse_id=? AND sub_id=?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, bachcourse_id);
			st.setInt(2, sub_id);
			st.setInt(3, prev_bachcourse_id);
			st.setInt(4, prev_sub_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
