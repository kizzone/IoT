package remote.storadge.module;

import java.util.Set;
import java.util.TreeSet;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import usefulClass.slotMachineMap;

public class SlotMachines extends CoapResource {

	public SlotMachines(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	
	/**
	  * {@inheritDoc}
	  * This post wants a string like:  CONNECTED or DISCONNECTED and return a string with the connected or disconnected slot machines
	  * Return the multiplier
	  */
	
	
	/*
	POST request to the CoAP resource /slotMachines, with a payload containing
	parameter between CONNECT and DISCONNECT, and returning a CoAP
	response containing the corresponding list of (connected or disconnected)
	slot machines.  
	 */
	@Override //==============================================
	public void handlePOST(CoapExchange exchange) {
		
		String request = exchange.getRequestText();
		String response = "";
		
		if ( request.equals("CONNECTED") || request.equals("DISCONNECTED") ) {
			
			Set<Integer> keys = new TreeSet<Integer>();
			keys.addAll(slotMachineMap.myMap.keySet());

			for(int key: keys){
				
				if( slotMachineMap.myMap.get(key).toString().equals(request) ) {
					 response +=  key  + ":" + slotMachineMap.myMap.get(key) + "\n";
				}
				
			}
			
			if ( !response.equals(null)) {
				exchange.respond(ResponseCode.CONTENT,  response , MediaTypeRegistry.TEXT_PLAIN);
			}else {
				exchange.respond(ResponseCode.CONTENT, "there is no slot in status " + request , MediaTypeRegistry.TEXT_PLAIN);
			} 
			
		}else {
			exchange.respond(ResponseCode.CONTENT, "Error SlotMachines: 404 Bad Request" , MediaTypeRegistry.TEXT_PLAIN);
			
		}
		
	}
	
	/*
	GET request to the CoAP resource /slotMachines, returning the list of
	connected and disconnected slot machines.

	*/
	
	
	
	/**
	  * {@inheritDoc}
	  * This Get return the list of the status of the slot machine in the smart gambling house
	  */
	
	@Override 
	public void handleGET(CoapExchange exchange) {
		
		String response = "";
				 
		Set<Integer> keys = new TreeSet<Integer>();
		keys.addAll(slotMachineMap.myMap.keySet());

		for(int key: keys){
		   response +=  key  + ":" + slotMachineMap.myMap.get(key) + "\n";
		}
				
		exchange.respond(ResponseCode.CONTENT,  response , MediaTypeRegistry.TEXT_PLAIN);
		
	}
	
	
	
}
