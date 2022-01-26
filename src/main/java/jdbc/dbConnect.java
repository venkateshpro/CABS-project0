package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.util.Properties;




public class dbConnect {

	public static Connection getConnection() throws Exception{
		FileInputStream fileStream = new FileInputStream("C:\\spring\\CBS\\src\\main\\resourses\\jdbc.properties"); 
		Properties properties = new Properties(); //taking connection credentials from jdbc.properties file.
		properties.load(fileStream);
		String url = properties.getProperty("url");	//making use of our connection credentials from the file.
		String id = properties.getProperty("id"); 
		String pwd = properties.getProperty("pwd");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, id, pwd);//creating connection
		
		return con;
		
	}

}