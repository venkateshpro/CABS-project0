package CBSclient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import jdbc.dbConnect;
import CBSmodel.CABS;
import CBSmodel.ModelEmp;
import CBSservice.BookingSystemImpl;



public class Admin {
	int stat() {
		System.out.println("!!! WELCOME to Admin Login !!!");
		while(true) {
			System.out.println("What do you wanna do?");
			System.out.println("1. Add Employee");
			System.out.println("2. Add Cab");
			System.out.println("3. Check Requests");
			System.out.println("Press anything else to Log Out!");
			Scanner sc =new Scanner(System.in);
			Integer ch = sc.nextInt();
			Admin a = new Admin();
			switch (ch) {
			case 1:
				try {
					a.addEmp();
				} catch (Exception e) {
					System.out.println("An exception is generated!" +e);
				}break;
			/*
			 * case 2: a.removeEmp(); break;
			 */
			case 2:
				a.addCab();
				break;
			case 3:
				try {
					a.checkRequest();
				} catch (Exception e) {
					System.out.println("An exception is generated!" +e);
				}
				break;
			default:
				System.out.println("Logging Out!");
				return 0;
			}
		}
	}
	void addEmp() throws Exception {
		Connection con = dbConnect.getConnection();
		PreparedStatement pt = con.prepareStatement("insert into employee(name,email,manager,active,dept,password) values(?,?,?,?,?,?)");
		
		ModelEmp emp = this.getNewEmployee();
		//pt.setInt(1,emp.getId());
		pt.setString(1, emp.getName());
		pt.setString(2, emp.getEmail());
		if(emp.getManager()==true)
			pt.setInt(3, 1);
		else
			pt.setInt(3, 0);
		pt.setInt(4, 1);
		pt.setString(5, emp.getDept());
		pt.setString(6, emp.getName()+"123");
		pt.execute(); //insert
		
	}
	
	/*
	 * void removeEmp() {
	 * PreparedStatement pt = con.prepareStatement("update employee set active=0 where (id=?)");
	 * 
	 * 
	 * }
	 */
	void addCab() {
		System.out.println("Enter Cab Number: ");
		Scanner sc =new Scanner(System.in);
		Integer CNo = sc.nextInt();
		CABS newCab = new CABS(CNo);
		
		BookingSystemImpl bs =new BookingSystemImpl();
		try {
			bs.addCab(newCab);
		} catch (Exception e) {
			System.out.println("Got an Exception. " +e);
		}
		
	}
	
	void checkRequest() throws Exception{
		Connection con = dbConnect.getConnection();
		Scanner sc = new Scanner(System.in);
		BookingSystemImpl bs =new BookingSystemImpl();
		Integer cabNo;
		System.out.println("All the manager approved requests are shown below one by one. \nPlease assign them a Cab.");
		PreparedStatement pt = con.prepareStatement("select * from request where statusId=2");
		ResultSet rs = pt.executeQuery();
		while(rs.next()) {
			System.out.println("Employee ID : " +rs.getInt(2) +" has requested an Cab with Request ID:"+rs.getInt(1)+"\nIts approved by his mamnager.");
			System.out.println("Assign a Cab? y/n");
			char m=sc.next().charAt(0);
			if(m=='y') {
				cabNo = bs.requestCab();
				if(cabNo>1000) {
					PreparedStatement pst = con.prepareStatement("insert into booking values(?,?)");
					pst.setInt(1,cabNo);
					pst.setInt(2, rs.getInt(2));
					pst.execute(); //insert
					System.out.println(cabNo+" assigned");
					//update the result in to request table
					pst = con.prepareStatement("select bookingId from booking where cabNo=? and employeeId=? order by bookingId desc limit 1");
					pst.setInt(1, cabNo);
					pst.setInt(2, rs.getInt(2));
					ResultSet rs1 = pst.executeQuery();
					if(rs1.next()) {
						pst = con.prepareStatement("update requests set statusId=4,bookingId=? where requestId=?");
						pst.setInt(1, rs1.getInt(1));
						pst.setInt(2, rs.getInt(1));
						int a = pst.executeUpdate(); //update
					}
				}
				else
					System.out.println("Exception Occoured!");
			}
				
		}
		System.out.println("No nore Remaining");
		return;
	
	}
	
	ModelEmp getNewEmployee() {
		ModelEmp a;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Employee Name");
		String n = sc.nextLine();
		System.out.println("Enter the Employee Email ID");
		String e = sc.nextLine();
		System.out.println("Enter this his assigned department");
		String d=sc.nextLine();
		System.out.println("Is he a Manager? y/n");
		char m=sc.next().charAt(0);
		if(m=='y')
			a = new ModelEmp(1,n,e,true,d);
		else
			a = new ModelEmp(1,n,e,d);
		return a;
	}


}

		
	
	

