/**
 * 
 */
package remote.storadge.module;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * 
 * Lost game resource 
 * @author domenico
 *
 */
public class LostGame extends CoapResource {
	
	
	private static int counter = 0;
	
	private int lowest = 0;
	
	private int highest = 0;
	
	
	

	public LostGame(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	
	
	 
	
	
	
	/**
	  * {@inheritDoc}
	  * This post wants a string like:  coins-id 
	  * Set the minimum and max
	  */
	
	@Override
	public void handlePOST(CoapExchange exchange) {
		//mi arriva l'id della slot, non me ne faccio niente, il numero di monete giocate
        String[] resp =  exchange.getRequestText().split("-");
        int tmp = Integer.parseInt(resp[0]);
        //aumenta la partita persa
        this.setCounter(LostGame.getCounter()+1);
        //setta lowest e highest
        if(tmp < this.getHighest() ) {
        	this.setLowest(tmp);    
        }else if ( tmp > this.getHighest()){
        	this.setLowest(this.getHighest());
        	this.setHighest(tmp);
        }
        exchange.respond(ResponseCode.CONTENT, "ACK" , MediaTypeRegistry.TEXT_PLAIN);

        
	}	
	
	
	
	
	/**
	  * {@inheritDoc}
	  * This GET returns the amount of LOST game, the lowest and the highest coin amount
	  */
	@Override
	public void handleGET(CoapExchange exchange) {
		if (LostGame.getCounter() != 0 )	
			exchange.respond(ResponseCode.CONTENT,"Total played games:" + LostGame.getCounter() + "Highest: "+ this.getHighest() + "Lowest: " + this.getLowest() +  "Average: " + (this.getHighest() + this.getLowest())/LostGame.getCounter()  , MediaTypeRegistry.TEXT_PLAIN);
		else
			exchange.respond(ResponseCode.CONTENT,"Total played games:" + LostGame.getCounter() + "Highest: "+ this.getHighest() + "Lowest: " + this.getLowest() +  "Average: " + (this.getHighest() + this.getLowest()) , MediaTypeRegistry.TEXT_PLAIN);
	}	
	
	
	public static int getCounter() {
		return counter;
	}	
	
	public int getLowest() {
		return lowest;
	}




	public void setLowest(int lowest) {
		this.lowest = lowest;
	}




	public int getHighest() {
		return highest;
	}




	public void setHighest(int highest) {
		this.highest = highest;
	}


	public void setCounter(int counter) {
		LostGame.counter = counter;
	}

}



/*
• GET request to the CoAP resource /lostGames, returning the total number
of lost games, the lowest and the highest lost coins amount, and the average
lost coins amount.

 */