package CBSclient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import jdbc.dbConnect;

public class loginpage {
	
	String email;
	String password;

	private static final Logger logger = Logger.getLogger("Login.class");
	
	public static void main(String[] args) throws Exception{
		Connection con = dbConnect.getConnection();
		Scanner sc = new Scanner(System.in);
		int ch;
		while(true) {
			
			System.out.println("****--Wellcome To Employee CAB booking System--*****");
			System.out.println("Choose The Option For  Login");
			System.out.println("Enter 1 for Admin login");
			System.out.println("Enter 2 for Manager login");
			System.out.println("Enter 3 for Employee login");
			ch =sc.nextInt();
			if(sc.hasNextLine()){
			    String strunfh = sc.nextLine();
			}
			//Integer ch =sc.nextInt();sc.nextLine();
			switch(ch) {
			case 1:
				loginpage admin = new loginpage();
				logger.info("Admin Login initiated");
				admin.getEmailPass();
				PreparedStatement pt = con.prepareStatement("select * from employee where email=? and password=? ");
				pt.setString(1, admin.email);
				pt.setString(2, admin.password);
				ResultSet rts = pt.executeQuery();
				if(rts.next()) {
					Admin empi = new Admin();
					logger.info("Going in to Admin.Java file");
					int a = empi.stat();
				}
				else {
					logger.info("Wrong password for login into  Admin portal ");

					System.out.println("-----Wrong  Email or  password of Admin Login-----");
				}
				break;
			case 2:
				loginpage manager = new loginpage();
				logger.info("Manager Login initiated");
				manager.getEmailPass();
				PreparedStatement ps = con.prepareStatement("select * from employee where email=? and password=? and manager=1");
				ps.setString(1, manager.email);
				ps.setString(2, manager.password);
				ResultSet rst = ps.executeQuery();
				if(rst.next()) {
					Integer id = rst.getInt("id");
					String name = rst.getString("name");
					String email = rst.getString("email");
					String dept = rst.getString("dept");
					Mannager manage = new Mannager(id,name,email,true,dept);
					try {
						logger.info("Going in to ManagerImpl.Java file");
						manage.accept();
					} catch (Exception e) {
						System.out.println("Got an Exception. " +e);
					}
				}
				else {
					logger.info("Wrong password by Manager");
					System.out.println("----Wrong  Email and Password for Manager Login----");
				}
				break;
			case 3:
				loginpage emp = new loginpage();
				logger.info("Employee Login initiated");
				emp.getEmailPass();
				PreparedStatement pst = con.prepareStatement("select * from employee where email=? and password=? and manager=0");
				pst.setString(1, emp.email);
				pst.setString(2, emp.password);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					Integer id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String dept = rs.getString("dept");
					Employee empi = new Employee(id,name,email,dept);
					logger.info("Going in to EmployeeImpl.Java file");
					empi.request();
					//try {
						//empi.request();
					//} catch (Exception e) {
						//System.out.println("Got an Exception. " +e);
					//}
				}
				else {
					logger.info("Wrong password entered for Employee");
					System.out.println("----Wrong  Email and password for employee login----");
				}
				break;
			default:
				logger.info("Closing the Application");

				System.out.println("Please Choose the Correct Option");
				System.out.println("Please try again!");
				System.out.println("!!! Exited !!!");
				
				System.exit(0);
		}
		
	}

}
	void getEmailPass() {
		logger.info("Getting email and password");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Email :");
		this.email = sc.nextLine();
		System.out.println("Enter your Password :");
		this.password = sc.nextLine();
		logger.info("Got email and password. Now validating details");
		
	}
	

}
