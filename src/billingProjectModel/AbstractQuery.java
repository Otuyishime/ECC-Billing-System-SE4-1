package billingProjectModel;

import dAO.EM;

public class AbstractQuery {
    
	public void open(){
		EM.INSTANCE.initEM();
	}
	 
	public void shutdown(){
		EM.INSTANCE.close();

	}
}
