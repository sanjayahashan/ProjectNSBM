package classes;

import java.util.Date;

public class Postgraduate extends Table{
	public int id;
	public String fname;
	public String mname;
	public String lname;
	public String gender;
	public java.sql.Date dob;
	public String home_no;
	public String add_1;
	public String add_2;
	public String add_3;
	public int t_no;
	public String email;
	public String type;
	public String institute;
	public int grad_year;
	public int r_year;
	public int r_semester;
	public int fac_id;
	public int course_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public java.sql.Date getDob() {
		return dob;
	}
	public void setDob(java.sql.Date dob) {
		this.dob = dob;
	}
	public String getHome_no() {
		return home_no;
	}
	public void setHome_no(String home_no) {
		this.home_no = home_no;
	}
	public String getAdd_1() {
		return add_1;
	}
	public void setAdd_1(String add_1) {
		this.add_1 = add_1;
	}
	public String getAdd_2() {
		return add_2;
	}
	public void setAdd_2(String add_2) {
		this.add_2 = add_2;
	}
	public String getAdd_3() {
		return add_3;
	}
	public void setAdd_3(String add_3) {
		this.add_3 = add_3;
	}
	public int getT_no() {
		return t_no;
	}
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public int getGrad_year() {
		return grad_year;
	}
	public void setGrad_year(int grad_year) {
		this.grad_year = grad_year;
	}
	public int getR_year() {
		return r_year;
	}
	public void setR_year(int r_year) {
		this.r_year = r_year;
	}
	public int getR_semester() {
		return r_semester;
	}
	public void setR_semester(int r_semester) {
		this.r_semester = r_semester;
	}
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
}
