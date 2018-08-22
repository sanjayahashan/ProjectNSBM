package classes;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class LabSession extends Table{
	public int ins_id;
	public int sub_id;
	public int lab_id;
	public java.sql.Time start_time;
	public java.sql.Time end_time;

	public int getIns_id() {
		return ins_id;
	}
	public void setIns_id(int ins_id) {
		this.ins_id = ins_id;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public int getLab_id() {
		return lab_id;
	}
	public void setLab_id(int lab_id) {
		this.lab_id = lab_id;
	}
	public java.sql.Time getStart_time() {
		return start_time;
	}
	public void setStart_time(java.sql.Time start_time) {
		this.start_time = start_time;
	}
	public java.sql.Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(java.sql.Time end_time) {
		this.end_time = end_time;
	}
	
	public void updateRecord(int prev_ins_id, int prev_sub_id, int prev_lab_id)
	{
		String query = "DELETE FROM labsession WHERE ins_id=? AND sub_id=? AND lab_id=?";
		try {
			java.sql.PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, prev_ins_id);
			st.setInt(2, prev_sub_id);
			st.setInt(3, prev_lab_id);
			st.executeUpdate();
			
			insertRecord();
			System.out.println(st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insertRecord()
	{
		String query = "INSERT INTO labsession VALUES (?,?,?,?,?)";
		java.sql.PreparedStatement st;
		try {
			st = con.prepareStatement(query);

			st.setInt(1, ins_id);
			st.setInt(2, sub_id);
			st.setInt(3, lab_id);
			st.setTime(4, start_time);
			st.setTime(5, end_time);
			
			System.out.println(st);
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
