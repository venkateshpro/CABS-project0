package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;


import jdbc.dbConnect;
import CBSmodel.CABS;

public class BookingImpl implements Booking{
	private static final Logger logger = Logger.getLogger("BookingImpl.class");


	@Override
	public Set<CABS> copycabs() {
		Set<CABS> cabs=new LinkedHashSet<>();
		try {
			Connection con = dbConnect.getConnection();
			PreparedStatement pt = con.prepareStatement("select cabNo,freeOrBooked from cab");
			ResultSet rs = pt.executeQuery();
			while(rs.next()) {
				Integer cabNo = rs.getInt(1);
				Integer fOB = rs.getInt(2);
				CABS a = new CABS(cabNo,fOB);
				cabs.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("All cab details are stored locally");

		return cabs;
	}

}