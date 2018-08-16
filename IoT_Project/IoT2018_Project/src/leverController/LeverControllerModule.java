/**
 * Questa classe gestisce tutto il livello del lever controller (sezione 1.1 (tranne il server HTTP )e 1.2 della traccia)
 */

package leverController;

import java.io.IOException;

/**
 * 
 * This class handle all the lever controller
 * @author kizzone
 *
 */
public class LeverControllerModule {

	/**
	 * this Main method start the Coap server for the lever Resources
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(int id) throws InterruptedException, IOException {
		
		//creo un istanza del LeverCoapServer e la lancio
		LeverCoapServer coapLeverServer = new  LeverCoapServer(id);
	    //coapLeverServer.start(); //start the server (lui parte sulla 5683 mi sa)==================================

	}

}
