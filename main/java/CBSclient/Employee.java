package CBSclient;

import CBSmodel.ModelEmp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import jdbc.dbConnect;
public class Employee extends ModelEmp{
	private static final Logger logger = Logger.getLogger("EmployeeImpl.class");


	public Employee(Integer id, String name, String email, String dept) {
		super(id, name, email, dept);
		
	}
	
	void request() throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection con = dbConnect.getConnection();
		logger.info("Logged into as Employee");
		PreparedStatement pst;
		ResultSet rs;
		int ch;
		while(true) {
			System.out.println("---Wellcome to Employee login---");
			System.out.println("Do you want to request a cab?");
			System.out.println("Press 1 to Request a Cab");
			System.out.println("Press 2 to view booking history");
			System.out.println("Press 3 to view last boooking status");
			System.out.println("Press anything else to Log Out!");
			//PreparedStatement pst;
			//ResultSet rs;
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				
				logger.info("Requesting a cab");
				pst = con.prepareStatement("insert into request (employeeId,statusId) values(?,?)");
				pst.setInt(1,this.getId());
				pst.setInt(2, 1);
				pst.execute(); //insert
				System.out.println("A cab is requested");
				logger.info("A cab is requested");
				break;
				
				
			case 2:
				logger.info("Viewing all booking cab history");
				pst = con.prepareStatement("select * from request where employeeId=?");
				pst.setInt(1,this.getId());
				rs = pst.executeQuery();
				while(rs.next()) {
					int stat = rs.getInt(4);
					if(stat==1)
						System.out.println("Request ID: "+rs.getInt(1)+" is Requested");
					else if(stat==2)
						System.out.println("Request ID: "+rs.getInt(1)+" is Approved by your Manager");
					else if(stat==3)
						System.out.println("Request ID: "+rs.getInt(1)+" is Rejected by your Manager");
					else
						System.out.println("Request ID: "+rs.getInt(1)+" is Booked with Booking ID: "+rs.getInt(3));
				}
				logger.info(" booking history is printed");
				break;
			case 3:
				pst = con.prepareStatement("select statusId,bookingId from request where employeeId=? order by requestId desc limit 1");
				pst.setInt(1,this.getId());
				rs = pst.executeQuery();
				if(rs.next()) {
					int sta = rs.getInt(1);
					if(sta==1)
						System.out.println("Requested");
					else if(sta==2)
						System.out.println("Requested and Approved");
					else if(sta==3)
						System.out.println("Manager has Rejected your request!\nTry again later.");
					else
						System.out.println("Booked\nBooking ID:"+rs.getInt(2));
				}
				break;
			default:
				logger.info("Log out from Employee class");
				return;
			}
		}
	}



}