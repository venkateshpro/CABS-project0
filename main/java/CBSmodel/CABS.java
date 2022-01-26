package CBSmodel;

import java.util.Date;
import java.util.Objects;
import java.util.Calendar;


public class CABS {
	private final Integer cabNo;
	private Date timing;
	private Integer freeOrBooked;
	
	public CABS(Integer cnum) {
		this.cabNo = cnum;
		//Calendar cal = Calendar.getInstance();
		//this.timing = cal.getTime();
		this.freeOrBooked = 0;
		
	}
	public CABS(Integer cnum,Integer fOB) {
		this.cabNo = cnum;
		this.freeOrBooked = fOB;
	}
	
	public Integer getFreeOrBooked() {
		return freeOrBooked;
	}

	public void setFreeOrBooked(Integer freeOrBooked) {
		this.freeOrBooked = freeOrBooked;
	}


	public Integer getCabNo() {
		return cabNo;
	}

	public Date getTiming() {
		return timing;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cabNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CABS other = (CABS) obj;
		return Objects.equals(cabNo, other.cabNo);
	}
	
	

		

}