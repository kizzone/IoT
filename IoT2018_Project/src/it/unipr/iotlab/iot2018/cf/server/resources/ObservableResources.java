package it.unipr.iotlab.iot2018.cf.server.resources;

import java.util.Timer;
import it.unipr.iotlab.iot2018.cf.server.*;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ObservableResources extends CoapResource {
	
	private static int value = 0;
	
	public static int getValue() {
		return value;
	}

	public static void setValue(int value) {
		ObservableResources.value = value;
	}

	public ObservableResources (String resourceName) {
		
		super(resourceName);
		Timer timer = new Timer();
		timer.schedule(new UpdateTask(this), 0, 1000);		
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond(ResponseCode.CONTENT, ObservableResources.getValue()+"" , MediaTypeRegistry.TEXT_PLAIN);
	}	

}
