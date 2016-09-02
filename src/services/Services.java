package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import connector.DatabaseConnector;

public class Services extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    public Services() {
        super();
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String function = request.getParameter("function");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter output = response.getWriter();
		
		DatabaseConnector databaseConnector = new DatabaseConnector();
		Connection connection = databaseConnector.connect();
		
		if(function.equals("get_students")){
			output.print(getStudents(request, connection));
		}else if(function.equals("get_courses")){
			output.print(getCourses(request, connection));
		}else{
			output.print("Entra a servidor");
		}
		
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	
    	String function = request.getParameter("function");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter output = response.getWriter();
		
		DatabaseConnector databaseConnector = new DatabaseConnector();
		Connection connection = databaseConnector.connect();
		
		if(function.equals("get_courses_by_student")){
			output.print(getCoursesByStudent(request, connection));
		}else if(function.equals("add_course")){
			output.print(addCourse(request, connection));
		}else if(function.equals("delete_course")){
			output.print(deleteCourse(request, connection));
		}else if(function.equals("change_score")){
			output.print(changeScore(request, connection));
		}
	}
    
    public String changeScore(HttpServletRequest request, Connection connection){
    	long studentId = Long.parseLong(request.getParameter("studentId"));
    	long courseId = Long.parseLong(request.getParameter("courseId"));
    	long newScore = Long.parseLong(request.getParameter("newCourseScore"));
    	
    	try {
			Statement statement = connection.createStatement();
			String sql = "";
			ResultSet resulSetPostgres = statement.executeQuery(sql);
			while(resulSetPostgres.next()){
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return "200";
    }
    
    public String addCourse(HttpServletRequest request, Connection connection){
    	long studentId = Long.parseLong(request.getParameter("studentId"));
    	long courseId = Long.parseLong(request.getParameter("courseId"));
    	return "200";
    }
    
    public String deleteCourse(HttpServletRequest request, Connection connection){
    	long studentId = Long.parseLong(request.getParameter("studentId"));
    	long courseId = Long.parseLong(request.getParameter("courseId"));
    	return "200";
    }
    
    public String getCourses(HttpServletRequest request, Connection connection){
    	JSONArray studentList = new JSONArray();
		try {
			JSONObject json = new JSONObject();
			json.put("name", "Biologia");
			json.put("code", 123);
			json.put("score", 1);
			JSONObject json1 = new JSONObject();
			json1.put("name", "Fisica");
			json1.put("code", 345);
			json1.put("score", 1);
			JSONObject json2 = new JSONObject();
			json2.put("name", "Filosofia");
			json2.put("code", 675);
			json2.put("score", 1);
			studentList.put(json);
			studentList.put(json1);
			studentList.put(json2);
			
			return studentList.toString(1);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public String getStudents(HttpServletRequest request, Connection connection){
    	JSONArray studentList = new JSONArray();
		try {
			JSONObject json = new JSONObject();
			json.put("name", "pedro");
			json.put("lastName", "andres");
			json.put("identification", 123);
			JSONObject json1 = new JSONObject();
			json1.put("name", "Camilo");
			json1.put("lastName", "Parra");
			json1.put("identification", 567);
			studentList.put(json);
			studentList.put(json1);
			
			return studentList.toString(1);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public String getCoursesByStudent(HttpServletRequest request, Connection connection){
		long studentId = Long.parseLong(request.getParameter("studentId"));
		
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from curso where estudiante_id=" + studentId;
			ResultSet resulSetPostgres = statement.executeQuery(sql);
			resulSetPostgres.getArray(1);//codigo
			resulSetPostgres.getArray(2);//nombre
			resulSetPostgres.getArray(3);//nota
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONArray studentList = new JSONArray();
		try {
			JSONObject json = new JSONObject();
			json.put("name", "Biologia");
			json.put("code", 443);
			json.put("score", 1);
			JSONObject json1 = new JSONObject();
			json1.put("name", "Quimica");
			json1.put("code", 453);
			json1.put("score", 2);
			studentList.put(json);
			studentList.put(json1);
			
			return studentList.toString(1);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
