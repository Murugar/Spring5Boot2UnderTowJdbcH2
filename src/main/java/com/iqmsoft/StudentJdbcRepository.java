package com.iqmsoft;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Student findById(long id) {
		return jdbcTemplate.queryForObject("select * from student where in_id=?", new Object[] { id },
				//new BeanPropertyRowMapper<Student>(Student.class));
				new RowMapper<Student>() {
					@Override
					public Student mapRow(ResultSet rs, int rowMap) throws SQLException {
						Student student = new Student();
						student.setId(rs.getLong("in_id"));
						student.setName(rs.getString("vc_name"));
						student.setPassportNumber(rs.getString("vc_passportnumber"));
						return student;
					}
				});
	}
}
