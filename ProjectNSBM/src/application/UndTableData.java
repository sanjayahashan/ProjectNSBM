package application;

import classes.*;

public class UndTableData {
	public UndTableData und;
	public Faculty fac;
	public Course course;
	
	UndTableData(UndTableData u, Faculty f, Course c)
	{
		this.und = u;
		this.fac = f;
		this.course = c;
		
	}

	public UndTableData getUnd() {
		return und;
	}

	public void setUnd(UndTableData und) {
		this.und = und;
	}

	public Faculty getFac() {
		return fac;
	}

	public void setFac(Faculty fac) {
		this.fac = fac;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
