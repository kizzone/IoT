/**
 * creata per inizializzare la mappa dei valori
 */
package usefulClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Is a class for storing the minimum coins for a slot machine
 * @author domenico
 *
 */
public class CoinMap {
	
	public static  Map<Integer, Integer> myMap = createMap();
	
	public static Map<Integer, Integer> createMap() {
	        Map<Integer,Integer> myMap = new HashMap<Integer,Integer>();
	        myMap.put(1, 60);
	        myMap.put(2, 150);
	        myMap.put(3, 58);
	        return myMap;
	    }
}
