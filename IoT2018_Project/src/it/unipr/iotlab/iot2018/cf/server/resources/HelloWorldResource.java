package it.unipr.iotlab.iot2018.cf.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class HelloWorldResource extends CoapResource {
	
	
	public HelloWorldResource(String resourceName) {
		super(resourceName);		
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond(ResponseCode.CONTENT,"vaffanculo",MediaTypeRegistry.TEXT_PLAIN);
	}
	
	
}
