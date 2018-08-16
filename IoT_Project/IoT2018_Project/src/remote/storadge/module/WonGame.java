/**
 * 
 */
package remote.storadge.module;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * Class that rappresent the resource Wongame
 * 
 * @author domenico
 *
 */
public class WonGame extends CoapResource {
	
	
	
	private static int counter ;
	
	private int lowest;
	
	private int highest;
	
	private String sequence;
	
	

	/**
	 * Constructor initialize the field of this class to 0
	 * @param name
	 */
	public WonGame(String name) {
		super(name);
		this.setCounter(0);
		this.setLowest(0);
		this.setHighest(0);
		
	}
	
	
	
	
	/**
	  * {@inheritDoc}
	  * This post wants a string like:  coins-idslot-simbol1-simbol2-simbol3
	  * Retrieve the multiplier from getVictoryData and ...
	  */
	
	@Override 
	public void handlePOST(CoapExchange exchange) {
		//mi arriva l'id della slot (non me ne faccio niente) e il numero di monete giocate
        String[] resp =  exchange.getRequestText().split("-");
        int tmp = Integer.parseInt(resp[0]);
		int id  = Integer.parseInt(resp[1]);
    	int uno = Integer.parseInt(resp[2]);
		int due = Integer.parseInt(resp[3]);
		int tre = Integer.parseInt(resp[4]);
		
		
		//faccio il post a get victorydata così le ho usate tutte le risorse
		String RemoteStoradge = "coap://localhost:5888/getVictoryData";
		CoapClient dummyclient = new CoapClient(RemoteStoradge);
		CoapResponse getVictoryResp = dummyclient.post(id + "-" + uno + "-"+ due +"-"+ tre, MediaTypeRegistry.TEXT_PLAIN);
		String [] response = getVictoryResp.getResponseText().split(":");
		
		
		System.out.println("WON GAME: multiplier is : "+ Integer.parseInt(response[1]));
		
		
		/*
		if(uno == 0 && due == 0 && tre == 0) {
			tmp = tmp * 2;
		}else {
			tmp = tmp * 3;
		}*/
		
		tmp *= Integer.parseInt(response[1]);
		
		
        this.setSequence(Integer.parseInt(resp[2]) + "-" + Integer.parseInt(resp[3]) + "-" + Integer.parseInt(resp[4])); 
        //aumenta la partita vinta
        this.setCounter(WonGame.getCounter()+1);
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
	  * This get return the amount of WON game, the lowest and the highest coin amount
	  */
	
	@Override 
	public void handleGET(CoapExchange exchange) {
		if(WonGame.getCounter() != 0)
			exchange.respond(ResponseCode.CONTENT,"Total played games: " + WonGame.getCounter() + " Highest: "+ this.getHighest() + " Lowest: " + this.getLowest() +  " Average: " + (this.getHighest() + this.getLowest())/WonGame.getCounter()  , MediaTypeRegistry.TEXT_PLAIN);
		else
			exchange.respond(ResponseCode.CONTENT,"Total played games: " + WonGame.getCounter() + " Highest: "+ this.getHighest() + " Lowest: " + this.getLowest() +  " Average: " + (this.getHighest() + this.getLowest()) , MediaTypeRegistry.TEXT_PLAIN);
	}	
	
	
	

	/**
	 * @return sequence: is a string that rappresent the winning sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * Setter for the string that rappresent the winning sequence
	 * @param sequence
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
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
		WonGame.counter = counter;
	}

}
