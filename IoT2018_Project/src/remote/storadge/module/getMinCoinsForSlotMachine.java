package remote.storadge.module;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import usefulClass.CoinMap;

/**
 * 
 * Used by the RemoteStoradgeModule to get the minimum coin amount from the class CoinMap
 * @author domenico
 *
 */

public class getMinCoinsForSlotMachine extends CoapResource {
	

	public getMinCoinsForSlotMachine(String name) {
		super(name);
		//CoinMap.myMap
		
	}
	
	@Override
	public void handlePOST(CoapExchange exchange) {
            
			Integer key = Integer.parseInt(exchange.getRequestText());
	        System.out.println("La chiave da ricercare è " + key);
	        Integer MinCoinsForSlotMachine = CoinMap.myMap.get(key);
	        System.out.println("Il valore associato è" + MinCoinsForSlotMachine );
	       	exchange.respond(ResponseCode.CONTENT, MinCoinsForSlotMachine.toString() , MediaTypeRegistry.TEXT_PLAIN);

	}	

}
