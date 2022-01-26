package CBSservice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedHashSet;
import java.util.Set;

import Dao.BookingImpl;
import jdbc.dbConnect;
import ExceptionHandeling.ExceptionHandler;
import CBSmodel.CABS;


public class BookingSystemImpl implements BookingSystem{
	
	private Set<CABS> cabs=new LinkedHashSet<>(); //available cabs

	@Override
	public void addCab(CABS newCab) throws Exception{
		BookingImpl bi = new BookingImpl();
		cabs = bi.copycabs();//getting all the data from cab table into our hashSet
		
		Connection con=dbConnect.getConnection();//getting our connection object
		
		//adding new cab to the database
		PreparedStatement pst = con.prepareStatement("insert into cab(cabNo,freeOrBooked) values (?,?);");
		pst.setInt(1, newCab.getCabNo());
		pst.setInt(2, newCab.getFreeOrBooked());
		pst.execute(); //executing insert command
		
		cabs.add(newCab);//adding into cabs Set
		
	}
	@Override

	public Integer requestCab() throws Exception{
		
		
		BookingImpl bi = new BookingImpl();
		cabs = bi.copycabs();
		if(cabs.isEmpty()) {
			try {
				throw new ExceptionHandler("No Cab is Available");
			} catch (ExceptionHandler e) {
				System.out.println(e.toString());
				return -1;
			}
		}
		
		for(CABS cab:cabs) 
			if(cab.getFreeOrBooked()==0) {
				cab.setFreeOrBooked(1);
				return cab.getCabNo();
			}
			
		try {
			throw new ExceptionHandler("No Cab is Available");
		} catch (ExceptionHandler e) {
			System.out.println(e.getMessage());
			return-1;
		}
		
	}

	@Override
	public Integer getNoOfAvailableCabs() throws Exception{
		
		//returning total number of cabs that are available for booking
		BookingImpl bi = new BookingImpl();
		cabs = bi.copycabs();
		Integer count=0;
		for(CABS cab:cabs) {
			if(cab.getFreeOrBooked()==0) {
				count++;
			}
		}
		return count;
	}
	
	

	

}
