package classes;

public class Subject extends Table{
	public int id;
	public String name;
	public int credits;
	public int fee;
	public int lec_id;
	public java.sql.Time start_time;
	public java.sql.Time end_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getLec_id() {
		return lec_id;
	}
	public void setLec_id(int lec_id) {
		this.lec_id = lec_id;
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
}
