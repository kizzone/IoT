/**
 * Rappresenta il client in ascolto dei cambiamenti di stato della risorsa gestita dal server coap del leverController
 */
package coreModuleController;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

import usefulClass.LeverActualValue;
import usefulClass.LeverStatus;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;

/**
 * 
 * Class used for create an observe relationship with the LeverCoapServer
 * @author domenico
 *
 */
public class ObserveCoreClient extends CoapClient {
	
	
	private  int slotID;
	public LeverActualValue leverStatus = new LeverActualValue(0, LeverStatus.STARTING);
	 
	
	
	/**
	 * Create the observe relationship
	 */
	public ObserveCoreClient (int id) {
		
		String serverCoap = "coap://127.0.0.1:568"+(3+id)+"/lever"; //
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    CoapClient client = new CoapClient(serverCoap);
        @SuppressWarnings("unused")
		CoapObserveRelation relation = client.observe(new CoapHandler() {
        
        public void onLoad(CoapResponse response) {
            //System.out.println( "(CORE MODULE CLIENT) ATTENZIONE LO STATO DELLA SLOT MACHINE E' CAMBIATO " );
            //System.out.println( response.getResponseText());
            String[] parts = response.getResponseText().split(":") ;
            //return response;
         
            if ("PULLED".equals(parts[0])){
                //che la risorsa osserveta è in stato pulled...
            	leverStatus.setStatoLeva(LeverStatus.PULLED);
            	try{
            			//System.out.println(parts[1]);
	            	    int i = Integer.parseInt(parts[1]);
	            	    leverStatus.setAcceleration(i);
	                   	//System.out.println( "(CORE MODULE CLIENT) PULLED " );
	                   	
        		}catch(NumberFormatException ex){ // handle your exception
        				System.err.println("ERRORE NEL PARSING DELL'INT");
        		}
            	//leverStat.setAcceleration(Integer.parseInt(parts[1]));      	

            	
            }else{
            	 //System.out.println( "(CORE MODULE CLIENT) QUIET " );
            	 leverStatus.setStatoLeva(LeverStatus.QUIET);
            	 leverStatus.setAcceleration(0);  
  
            }
            

        }
        @Override
        public void onError() {
            System.err.println("Failed");
        	}
        });
               
       
	 // wait for user
     /*
		try { br.readLine(); } catch (IOException e) { }
		
		System.out.println("CANCELLATION");
		
		relation.proactiveCancel();
     */

	}
		
	/**
	 * @return the slotID
	 */
	public int getSlotID() {
		return slotID;
	}

	/**
	 * @param slotID the slotID to set
	 */
	public void setSlotID(int slotID) {
		this.slotID = slotID;
	}

	/**
	 * @return
	 */
	public LeverActualValue getLeverStatus() {
		return leverStatus;
	}

	/**
	 * @param leverStatus
	 */
	public  void setLeverStatus(LeverActualValue leverStatus) {
		this.leverStatus = leverStatus;
	}
	

}
