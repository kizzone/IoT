/**
 * 
 */
package usefulClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Is a class for storing the status of a slot machine on the smart gambling house
 * @author domenico
 *
 */

public class slotMachineMap {

public static Map<Integer, String> myMap = createMap();
	
	public static Map<Integer, String> createMap() {
	        Map<Integer,String> myMap = new HashMap<Integer,String>();
	        myMap.put(1, "DISCONNECTED");
	        myMap.put(2, "DISCONNECTED");
	        myMap.put(3, "DISCONNECTED");
	        return myMap;
	    }	
}
