package classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UndResult extends Table{
	public int und_id;
	public int sub_id;
	public String result;
	public Boolean payment;
	
	public int getUnd_id() {
		return und_id;
	}

	public void setUnd_id(int und_id) {
//		prev_und_id = this.und_id;
		this.und_id = und_id;
	}

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
//		prev_sub_id = this.sub_id;
		this.sub_id = sub_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Boolean getPayment() {
		return payment;
	}

	public void setPayment(Boolean payment) {
		this.payment = payment;
	}

	public int insertRecord()
	{
		String query = "INSERT INTO undresult VALUES(?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, und_id);
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
	
	public void updateRecord(int prev_sub_id)
	{
		String query1 = "DELETE FROM undresult WHERE und_id=? AND sub_id=?";
		try {
			PreparedStatement st = con.prepareStatement(query1);
			
			st.setInt(1, und_id);
			st.setInt(2, prev_sub_id);
			
			st.executeUpdate();
			insertRecord();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
