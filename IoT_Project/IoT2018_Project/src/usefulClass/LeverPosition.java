/**
 * Classe utilizzata per rappresentare le posizioni/accelerazioni che il Server HTTP restituisce
 * 
 */
package usefulClass;

/**
 * Class used for storing the information retrieved by the http server
 * @author kizzone
 *
 */
public class LeverPosition {
	
	/**
	 * @return the xCord
	 */
	public int getXCord() {
		return XCord;
	}




	/**
	 * @param xCord the xCord to set
	 */
	public void setXCord(int xCord) {
		XCord = xCord;
	}




	/**
	 * @return the yCord
	 */
	public int getYCord() {
		return YCord;
	}




	/**
	 * @param yCord the yCord to set
	 */
	public void setYCord(int yCord) {
		YCord = yCord;
	}




	/**
	 * @return the zCord
	 */
	public int getZCord() {
		return ZCord;
	}




	/**
	 * @param zCord the zCord to set
	 */
	public void setZCord(int zCord) {
		ZCord = zCord;
	}




	private int XCord;
	private int YCord;
	private int ZCord;
	
	
	
	
	/**
	 * @param override cosi il toString su questa classe stampa le "posizioni/accelerazioni" dell'oggetto 
	 */
	@Override
	public String toString()
	    {
	        return "Class LevelPositions : [ZCord = "+ ZCord +", YCord = "+ YCord +", XCord = "+ XCord +"]";
	    }
	
	
	
	
	
	
	

}
