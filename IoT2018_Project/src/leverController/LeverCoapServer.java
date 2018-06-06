/**
 * Questa classe rappresenta il server coap che gestisce la risorsa osservabile Lever (che ritoni dal client http)
 * 
 */
package leverController;

import java.io.IOException;
import org.eclipse.californium.core.CoapServer;


import resources.LeverObsRes;
import usefulClass.LeverActualValue;

/**
 * 
 * This class rappresents the coap server that handle the observable resources lever
 * @author kizzone
 *
 */
public class LeverCoapServer extends CoapServer {
	
	//gestisce solo la risorsa LeverObsRes

	private LeverObsRes obsRes;
	
	
    /**
     * Constructor for the lever resources 
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public LeverCoapServer(int id) throws InterruptedException, IOException{
    	
    	CoapServer cs = new CoapServer(5683+id);
        
        obsRes = new LeverObsRes("lever");
        obsRes.setObservable(true);
        obsRes.getAttributes().setObservable();     
        cs.add(obsRes);
        cs.start();
        
    }
    
    /**
     * setActualStatus  for the lever resource 
     * @throws InterruptedException
     * @throws IOException
     */
    public void setResource( LeverActualValue stat){
        LeverObsRes.setActualStatus(stat);
    }
		
    /**
    * getter ...
    * @return LeverActualValue:  resource status
    * @throws InterruptedException
    * @throws IOException
    */
    public LeverActualValue getResource() throws InterruptedException, IOException {
           return LeverObsRes.getActualStatus();
           
    }

	/**
	 * getter
	 * @return LeverObsRes: the lever resources
	 */
	public LeverObsRes getObsRes() {
		return obsRes;
	}

	/**
	 * setter for LeverObsRes
	 * @param obsRes
	 */
	public void setObsRes(LeverObsRes obsRes) {
		this.obsRes = obsRes;
	}
	
}
