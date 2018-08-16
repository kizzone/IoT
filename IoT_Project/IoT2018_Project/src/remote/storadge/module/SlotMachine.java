/**
 * 
 */
package remote.storadge.module;


import java.util.Set;
import java.util.TreeSet;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import usefulClass.slotMachineMap;

/**
 * Rappresent the slotMachine Resource
 * @author domenico
 *
 */
public class SlotMachine extends CoapResource {
	
	public String sMs;

	public SlotMachine(String name) {
		super(name);
	}
	
	
	
	
	
	/**
	  * {@inheritDoc}
	  * This post wants a string like:  id-CONNECTED||DISCONNECTED
	  * 
	  * Set the status of the selected slot
	  */
	
	@Override //============================in th funziona==================
	public void handlePOST(CoapExchange exchange) {
			
		//la richiesta deve arrivare come id-STATUS\
		String resp = exchange.getRequestText();
		
		System.out.println("Request to set " + resp);

			try {
				String [] arg = resp.split("-");
				int id = Integer.parseInt(arg[0]);
				
				//System.out.println(arg[1]);
				
				if ( arg[1].equals("CONNECTED")) {
					sMs = "CONNECTED"   ;
				}
				else {
					sMs = "DISCONNECTED";
				}
				
				//System.out.println("SMS is "+ sMs);
				slotMachineMap.myMap.replace(new Integer(id), sMs);	
				exchange.respond(ResponseCode.CONTENT, "200 OK" , MediaTypeRegistry.TEXT_PLAIN);
				
				
				System.out.println(slotMachineMap.myMap.get(new Integer(id)));
				
				System.out.println("Il nuovo stato della mappa è: ");
				Set<Integer> keys = new TreeSet<Integer>();
				keys.addAll(slotMachineMap.myMap.keySet());

				for(int key: keys){
				   System.out.println( key  + ":" + slotMachineMap.myMap.get(key));
				}
				
				
			}
			catch(NumberFormatException ex){ // handle  exception
				exchange.respond(ResponseCode.CONTENT, "Slot machine resources : 400 Bad Request" , MediaTypeRegistry.TEXT_PLAIN);
			}
			  
	}

}
