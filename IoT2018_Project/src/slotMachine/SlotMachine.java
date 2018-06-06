package slotMachine;

import java.io.IOException;
import java.util.Scanner;
/*
import java.util.Set;
import java.util.TreeSet;
import usefulClass.slotMachineMap;

*/
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import coreModuleController.CoreModuleServer;
import leverController.LeverControllerModule;

/**
 * Class use for starting the slot machine.
 * @author domenico
 *
 */
public class SlotMachine {

	public static void main (String [] args ) throws InterruptedException, IOException {
		
		
		
		System.out.print("Insert the Id of the machine in range 1-3 :");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		
		if (num <=  3 && num >= 1) {
			
			LeverControllerModule.main(num);
			CoreModuleServer.main(num);	
			
			
			String RemoteStoradge = "coap://localhost:5888/slotMachine";
			CoapClient dummyclient = new CoapClient(RemoteStoradge);
			CoapResponse resp = dummyclient.post(num + "-CONNECTED", MediaTypeRegistry.TEXT_PLAIN);
			
			if (resp.isSuccess())
				System.out.println(resp.getResponseText());
			
			
			
		}
		
		else {
			
			System.out.println("We dont have this slot machine");
			System.exit(-2);
			
		}
	
		
	}

}
