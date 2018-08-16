package remote.storadge.module;


import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * New game resource
 * @author domenico
 *
 */
public class NewGame extends CoapResource {

	
	public NewGame(String name) {
		super(name);
	}
	
	
	/**
	  * {@inheritDoc}
	  * This post returns an ack
	  */
	
	@Override
	public void handlePOST(CoapExchange exchange) {
		
        //exchange.getRequestText();
        exchange.respond(ResponseCode.CONTENT, "ACK" , MediaTypeRegistry.TEXT_PLAIN);
		
	}
	

}
