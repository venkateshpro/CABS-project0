package CBSclient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Logger;

import jdbc.dbConnect;
import CBSmodel.ModelEmp;

public class Mannager extends ModelEmp{
	private static final Logger logger = Logger.getLogger("ManagerImpl.class");

		
	public Mannager(Integer id, String name, String email, Boolean manager, String dept) {
			super(id, name, email, manager, dept);
		}
		
		void accept() throws Exception{
			Connection con = dbConnect.getConnection();
			Scanner sc = new Scanner(System.in);
			logger.info(" Manager login");

			System.out.println("Following Cab requests are pending!\nPlease approve or reject them.");
			PreparedStatement pt = con.prepareStatement("select * from request where statusId=1");
			ResultSet rs = pt.executeQuery();
			logger.info(" pending Cab Requests by  employee");

			while(rs.next()) {
				System.out.println("Employee ID : " +rs.getInt(2) +" has requested an Cab with Request ID:"+rs.getInt(1));
				System.out.println("Approve? y/n");
				char m=sc.next().charAt(0);
				if(m=='y') {
					PreparedStatement pst = con.prepareStatement("update requests set statusId=2 where requestId=?");
					pst.setInt(1, rs.getInt(1));
					int a = pst.executeUpdate(); //update
					System.out.println(a+"Request Approved!");
					logger.info("Request Approved");

				}
				else {
					PreparedStatement pst = con.prepareStatement("update requests set statusId=3 where requestId=?");
					pst.setInt(1, rs.getInt(1));
					int a = pst.executeUpdate(); //update
					System.out.println(a+"Request Rejected!");
					logger.info("Request Rejected");

				}
			}
			logger.info("Log out from Manager class");

			return;
		}

	}


