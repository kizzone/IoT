/**
 * 
 * Classe Core Module Server
 * 
 */
package coreModuleController;

import java.io.IOException;
import java.util.Random;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import resources.InsertCoins;
import usefulClass.LeverActualValue;
import usefulClass.LeverStatus;


/**
 * Class used for the CoreModule of the slotMachine
 * @author domenico
 *
 */

public class CoreModuleServer extends CoapServer{
	
	private static int slotID;
	public InsertCoins insertCoins;
	
	public LeverActualValue leverCurrentStatus;
	public String status;

	ObserveCoreClient obs;
	
	/**
	 * Costructor used for start a CoapServer on a differt port from the default one  and create the resource InsertCoins
	 * @param id  is the slot id
	 * @throws NumberFormatException
	 */
	public CoreModuleServer (Integer id) throws NumberFormatException {
		
		CoapServer cS = new CoapServer(5693+id);
		obs = new ObserveCoreClient(id);
		CoreModuleServer.slotID  = id;
		insertCoins = new InsertCoins("insertCoins",CoreModuleServer.slotID,this);
        cS.add(insertCoins);
        cS.start();
		
	}
	
	
	
    /**
     * 
     * starting method for CoreModuleServer class
     * @throws InterruptedException
     * @throws IOException
     * @param id is the slot id
     */
    public static void main (int id) throws InterruptedException, IOException {
        
    	@SuppressWarnings("unused")
		CoreModuleServer cs = new CoreModuleServer(id );
        
    }
    
    
    /**
     * 
     * Method used for start the bet and inform the RemoteStoradgeModule 
     * @param soldi amount of coin of the bet
     * @param ID is the SLOT id
     */
    public void startGambling(int soldi, int ID) {
    	    	
    	System.out.println("Start gambling");
    	
    	LeverActualValue lAv;
    	
    	CoapClient dummyClient = new CoapClient("coap://localhost:5888/NewGame");
		CoapResponse resp2 = dummyClient.post( Integer.toString(soldi)+Integer.toString(ID), MediaTypeRegistry.TEXT_PLAIN);
    	System.out.println("New game response: " + resp2.getResponseText());
    	    	
    	//controllo lo stato della leva se è pulled mando il post alla risorsa
    	
    	do {
    	
    		lAv = obs.getLeverStatus();
    		System.out.println("STAUTS : " + lAv.getStatoLeva());
    		
    	}	while (lAv.getStatoLeva() != LeverStatus.PULLED);
    	
		System.out.println("Lo stato è pulled la partita è iniziata e l'accelerazione è " + lAv.getAcceleration());
		 
		int uno,due,tre;
		
		uno = new Random().nextInt(2);
		due = new Random().nextInt(2);
		tre = new Random().nextInt(2);
		
		
		if(((uno == due && due ==  tre) || lAv.getAcceleration() == 42) && lAv.getAcceleration() > 0  ) {
			
			System.out.println("WINNER WINNER, CHICKEN DINNER!\n X: "+ uno + " Y:" + due+ " Z:" + tre );
			//fare il post  a win game
			CoapClient chickenDinner = new CoapClient("coap://localhost:5888/wonGame");
			String responseString = soldi+"-"+ ID + "-"+ uno + "-" + due + "-" + tre;
			CoapResponse winResp = chickenDinner.post( responseString, MediaTypeRegistry.TEXT_PLAIN);
			System.out.println("Winning post results are: " + winResp.getResponseText());			
			
		}else {
			
			System.out.println("INSERISCI ALTRI SOLDI, LA PROSSIMA VOLTA SARAI PIU FORTUNATO!\n X: "+ uno + " Y: " + due+ " Z:" + tre);
			//fare il post a lost game
			CoapClient looserDinner = new CoapClient("coap://localhost:5888/lostGame");
			String responseString = soldi+"-"+ ID;
			CoapResponse looserResp = looserDinner.post( responseString, MediaTypeRegistry.TEXT_PLAIN);
			System.out.println("Losing post results are: " + looserResp.getResponseText());
	
		}
    }
}