/**
 * 
 * 
 */

package gambler;

import java.util.Scanner;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;


/**
 * send a post request to the resource insertCoins 
 * @author domenico
 *  
 */
public class GamblerClient extends CoapClient {

	
	
	/**
	 * Main method use for bet some money on a port (slodID) 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//stampo la lista di quelli connectede mi fido del fatto che il gambler giochi solo su quelli
		
		String RemoteStoradge = "coap://localhost:5888/slotMachines";
		CoapClient dummyclient = new CoapClient(RemoteStoradge);
		CoapResponse resp = dummyclient.post("CONNECTED", MediaTypeRegistry.TEXT_PLAIN);
		
		if (resp.isSuccess())
			System.out.println("Slot machine online:" + resp.getResponseText());		
		
		System.out.print("Insert the Id of the machine on which you wanna play (1-3):");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		
		
		if (num >  3 || num < 1 ) {
			System.out.println( "Gambler: 400 Bad Request ");
			System.exit(2);
		}
		else {
			 
			System.out.print("Insert the amount of coins you wanna bet:");
			int coins = in.nextInt();
			in.close();
			
			//la risorsa è localizzata in base all'id inserito
			String resource = "coap://localhost:569"+(3+num)+"/insertCoins";
			CoapClient client = new CoapClient(resource);
			CoapResponse resp2 = client.post(String.valueOf(coins), MediaTypeRegistry.TEXT_PLAIN);
			System.out.println("RESPONSE 2 CODE: " + resp2.getResponseText());
				
		}

		
	}
	
}
