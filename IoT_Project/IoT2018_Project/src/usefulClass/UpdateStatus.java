/**
 * utilizzata per richiamare periodicamente il client http
 */
package usefulClass;

import java.io.IOException;
import java.util.TimerTask;


import org.eclipse.californium.core.CoapResource;

import leverController.HttpClient;
import resources.LeverObsRes;

/**
 * Used for send a periodic request to the HTTP server and notify the client of a resource change 
 * @author kizzone
 *
 */
public class UpdateStatus extends TimerTask{
	
    private final CoapResource mCoapRes;
    
    LeverActualValue startingStatus = new LeverActualValue(0 , LeverStatus.STARTING);
    LeverActualValue status;
    HttpClient httpC = new HttpClient();
    
    public UpdateStatus(CoapResource coapRes) {
        mCoapRes = coapRes;
    }
    
    
    
    
    /**
     * {@inheritDoc}
     * 
     * Use the HTTP client periodically to retrieve the status information
     * 
     * 
     */
    @Override
    public void run() {
    	
            try {
            	status = httpC.requestLeverStatus();	
            	
            	
                //System.out.println("(UPDATE STATUS) the old status is: " + startingStatus.toString() + " -- The new one is: " + status.toString());
  	          
    		    if (startingStatus.getStatoLeva() != status.getStatoLeva()){
    		        
    		        //sulla risorsa chiamare changed
    		        //lo  stato ricevuto e' lo stesso e non devo aggiornare lo stato della risorsa
    		        LeverObsRes.setActualStatus(status);
    		        //System.out.println("(UPDATE STATUS)ATTENZIONE LO STATO E' CAMBIATO DA" + startingStatus.getStatoLeva() +" a " + status.getStatoLeva() + "CON ACCELERAZIONE DI: " + status.getAcceleration() ); 
    		        mCoapRes.changed();
    		         
    		      }

              startingStatus.setStatoLeva(status.getStatoLeva() );
              startingStatus.setAcceleration(status.getAcceleration());
              
            	
			} catch (IOException e) {
				e.printStackTrace();
			}
            
	
    }


}
