package net.lampbrother.java.share.dao;

import java.util.List;

import net.lampbrother.java.share.domain.Student;

public class StudentDao extends BaseDao<Student> {

	public List<Student> getStudents() {
		String sql = "select * from student where state=0";
		return queryForList(sql);
	}

	public List<Object[]> getGroup() {
		String sql = "SELECT DISTINCT groupname FROM student where state=0";
		return queryGroupForList(sql);
	}

	public List<Object[]> getName(String name) {
		String sql = "SELECT NAME FROM student WHERE groupname = ? and state=0";
		return queryGroupForList(sql, name);
	}

	public List<Object[]> getTwoGroup() {
		String sql = "SELECT DISTINCT groupname FROM student";
		return queryGroupForList(sql);
	}

}
