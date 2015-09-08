package com.demo.gwt.server.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.demo.gwt.client.service.HomeService;
import com.demo.gwt.model.Student;
import com.demo.gwt.shared.mapper.StudentMapper;


@Service("homeService")
public class StudentServiceImpl implements HomeService {
	static Logger log = Logger.getLogger(StudentServiceImpl.class.getName());
	   private DataSource dataSource;
	   @Autowired
	   private JdbcTemplate jdbcTemplateObject;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	   public void create(String name, Integer age) {
	      String SQL = "insert into Student (name, age) values (?, ?)";
	      
	      jdbcTemplateObject.update( SQL, name, age);
	      return;
	   }

	   public Student getStudent(Integer id) {
	      String SQL = "select * from Student where id = ?";
//	      Student student = jdbcTemplateObject.queryForObject(SQL, 
//	                        new Object[]{id}, new StudentMapper());
	      
	      Student student = jdbcTemplateObject.queryForObject(SQL, 
          new Object[]{id},new RowMapper<Student>() {
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				      Student student = new Student();
				      student.setId(rs.getInt("id"));
				      student.setName(rs.getString("name"));
				      student.setAge(rs.getInt("age"));
				      return student;
				   }
			});
	      return student;
	   }

	   public List<Student> listStudents() {
		   log.info("Created Record Name =1111 " );
	      String SQL = "select * from Student";
	      List <Student> students = jdbcTemplateObject.query(SQL, 
	                                new StudentMapper());
	      return students;
	   }

	   public void delete(Integer id){
	      String SQL = "delete from Student where id = ?";
	      jdbcTemplateObject.update(SQL, id);
	      return;
	   }

	   public void update(Integer id, Integer age){
	      String SQL = "update Student set age = ? where id = ?";
	      jdbcTemplateObject.update(SQL, age, id);
	      return;
	   }

	public int totalCount() {
		// TODO Auto-generated method stub
		return 10;
	}

}
