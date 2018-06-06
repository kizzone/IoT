package resources;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import coreModuleController.CoreModuleServer;


/**
 * Rappresert the resource InsertCoins
 * @author domenico
 *
 */
public class InsertCoins extends CoapResource {
	
	CoreModuleServer cMS ;
	
	private static int minimumCoins;
	
	public static int coins;
	
	public static int id;

	public static int getCoins() {
		return coins;
	}

	public static void setCoins(int coins) {
		InsertCoins.coins = coins;
	}

	/**
	 * When this constructor is call it makes a request for the slotID to the resource getMinCoinsForSlotMachine on the RemoteStorageModule
	 * @param name
	 * @param slotID
	 * @param cm
	 * @throws NumberFormatException
	 */
	public InsertCoins(String name,int slotID, CoreModuleServer cm) throws NumberFormatException{
		super(name);
		//create a dummy client that send a post request to get minimum coins to remote storage
		//create the resource with the minimum coins already in
		//per adesso sono 50
		InsertCoins.id = slotID;
		CoapClient dummyClient = new CoapClient("coap://localhost:5888/getMinCoinsForSlotMachine");
		CoapResponse resp2 = dummyClient.post( Integer.toString(slotID), MediaTypeRegistry.TEXT_PLAIN);
		InsertCoins.minimumCoins = Integer.parseInt(resp2.getResponseText());
		System.out.println("Ciao sono il dummyclient ed il valore di danaro minimo per la slot machine è " + InsertCoins.minimumCoins);
		cMS = cm;
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		            
            int coins  = Integer.parseInt(exchange.getRequestText());
            InsertCoins.setCoins(coins);
            //per adesso la scommessa parte direttamete
            System.out.println("Vuoi scommettere " + coins + "il minimo richiesto è " + InsertCoins.minimumCoins);
            if(InsertCoins.getCoins() >= InsertCoins.minimumCoins ) {
            	System.out.println("puoi iniziare la partita");
            	exchange.respond(ResponseCode.CONTENT, "OK " , MediaTypeRegistry.TEXT_PLAIN);
            	//====================================================================
            	cMS.startGambling(InsertCoins.getCoins(), InsertCoins.id);
            	            	
            }else {
            	exchange.respond(ResponseCode.CONTENT, "ERRORE troppi pochi SOLDIIIII " , MediaTypeRegistry.TEXT_PLAIN);
            }
            
	}	
	
}
