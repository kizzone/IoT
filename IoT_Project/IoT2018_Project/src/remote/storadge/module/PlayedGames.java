package remote.storadge.module;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * 
 * Rappresent the playedGames resource
 * @author domenico
 *
 */
public class PlayedGames extends CoapResource {
		
	private static int totalAmount;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		PlayedGames.totalAmount = totalAmount;
	}

	/**
	 * Create and set the total amount of played games to zero
	 * @param name
	 */
	public PlayedGames(String name) {
		super(name);
		this.setTotalAmount(0);
		
	}
	
	
	
	/**
	  * {@inheritDoc}
	  * This get return the amount of total played games
	  */
	

	
	@Override //============================CAMBIATO QUI==================
	public void handleGET(CoapExchange exchange) {
		
		//recuperare da lost e win game il numero di partite (counter) sommarli e restituirli
		this.setTotalAmount(  LostGame.getCounter() + WonGame.getCounter() );
		exchange.respond(ResponseCode.CONTENT, "Played games: " + this.getTotalAmount() , MediaTypeRegistry.TEXT_PLAIN);
		
	}

}
