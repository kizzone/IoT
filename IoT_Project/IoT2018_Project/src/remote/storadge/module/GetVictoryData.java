package remote.storadge.module;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * Class for the resource GetVictoryData. This class handle post request and response with the multiplier, depending on the sequence of symbols, for the bet in case of winning 
 * @author domenico
 *
 */
public class GetVictoryData extends CoapResource {

	public GetVictoryData(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	  * {@inheritDoc}
	  * This post wants a string like:  id-simbolo1-simbolo2-simbolo3
	  * Return the multiplier
	  */
	
	
	@Override //============================si aspetta una stringa del tipo id-simbolo1-simbolo2-simbolo3 ==================
	public void handlePOST(CoapExchange exchange) {
		
		
		//mi arriva l'id della slot e la sequenza di simboli
		String [] response = exchange.getRequestText().split("-");
		@SuppressWarnings("unused")
		int id = Integer.parseInt(response[0]);
		int uno = Integer.parseInt(response[1]);
		int due = Integer.parseInt(response[2]);
		int tre = Integer.parseInt(response[3]);
		
		if(uno == 0 && due == 0 && tre == 0) {
			exchange.respond(ResponseCode.CONTENT, "Multiplier:2" , MediaTypeRegistry.TEXT_PLAIN);
		}else
			exchange.respond(ResponseCode.CONTENT, "Multiplier:3" , MediaTypeRegistry.TEXT_PLAIN);
	        
	}	
	
		
}
