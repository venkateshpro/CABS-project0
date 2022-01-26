package CBSservice;
import CBSmodel.CABS;

public interface BookingSystem {
	
    void addCab(CABS newCab) throws Exception;
	Integer requestCab() throws Exception;
	Integer getNoOfAvailableCabs() throws Exception;


}
