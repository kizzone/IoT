package remote.storadge.module;

import org.eclipse.californium.core.CoapServer;

/**
 * 
 * This class handle all the resource of the section 1.4
 * 
 * @author domenico
 *
 */
public class RemoteStoradgeModule extends CoapServer {
	
	public getMinCoinsForSlotMachine moneteMinime;
	
	public WonGame partiteVinte;
	
	public LostGame partitePerse;
	
	public NewGame newGame;
	
	public PlayedGames playedGames;
	
	public SlotMachine slotMachine;
	
	public SlotMachines slotMachines;
	
	public GetVictoryData getVictoryData;
	
	
	public RemoteStoradgeModule () {
		
		CoapServer rM = new CoapServer(5888);
		moneteMinime = new getMinCoinsForSlotMachine("getMinCoinsForSlotMachine");
		partiteVinte = new WonGame("wonGame");
		partitePerse = new LostGame("lostGame");
		newGame = new NewGame("NewGame");
		playedGames = new PlayedGames("playedGames");
		slotMachine =  new SlotMachine("slotMachine");
		slotMachines = new SlotMachines("slotMachines");
		getVictoryData = new GetVictoryData("getVictoryData");
        rM.add(moneteMinime,newGame, partiteVinte, partitePerse,playedGames,slotMachine,slotMachines,getVictoryData);
       	rM.start();
		
	}
	
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		RemoteStoradgeModule RM = new RemoteStoradgeModule();

		
	}
	
}
