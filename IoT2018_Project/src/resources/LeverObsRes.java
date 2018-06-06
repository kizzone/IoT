package resources;

import java.io.IOException;
import java.util.Timer;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import leverController.HttpClient;
import usefulClass.LeverActualValue;
import usefulClass.UpdateStatus;

/**
 * Rappresent the LeverObsRes resources, use UpdateTask for getting the new status from the HTTP server
 * @author domenico
 *
 */
public class LeverObsRes extends CoapResource {
	
	//la risorsa LeverObsRes gestisce un oggetto di tipo LeverActualValue
	private static LeverActualValue actualStatus;
	

	public LeverObsRes(String name) throws IOException {
		super(name);
		//chiamo per la prima volta HttpClient per mettere la risorsa in uno stato coerente
		HttpClient httpCl = new HttpClient();
		LeverActualValue atMoment = httpCl.requestLeverStatus();
		//System.out.println("UPDATE TASK " + atMoment.toString());
		LeverObsRes.setActualStatus(atMoment);
		//ora viene il bello creo un nuovo oggetto Timer
		Timer timer = new Timer();
		//schedulo una nuova chimata con il client HTTP
		timer.schedule(new UpdateStatus( this ), 0, 2000);//cambiato da 10000	
		
	}

	/**
	 * Setter per lo stato della risorsa
	 * @param actualStatus the actualStatus to set
	 */
	public static void setActualStatus(LeverActualValue actualStatus) {
		LeverObsRes.actualStatus = actualStatus;
	}
	
	
	/**
	 * Getter per lo stato della risorsa
	 * 
	 */
	public static LeverActualValue getActualStatus() {
		return actualStatus;
	}
	
	//in th non dovrebbe manco servire?
	@Override
	public void handleGET(CoapExchange exchange) {
            exchange.respond(ResponseCode.CONTENT, LeverObsRes.getActualStatus()+ "" , MediaTypeRegistry.TEXT_PLAIN);
	}	

}
