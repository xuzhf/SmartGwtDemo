package com.demo.gwt.client.service;

import java.util.List;

import com.demo.gwt.model.Student;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HomeServiceAsync {

	void create(String name, Integer age, AsyncCallback<Void> callback);

	void delete(Integer id, AsyncCallback<Void> callback);

	void getStudent(Integer id, AsyncCallback<Student> callback);

	void listStudents(AsyncCallback<List<Student>> callback);

//	void setDataSource(DataSource ds, AsyncCallback<Void> callback);

	void update(Integer id, Integer age, AsyncCallback<Void> callback);

	void totalCount(AsyncCallback<Integer> callback);

	

}
