/**
 * classe utilizzata per mandare richieste di tipo GET al server HTTP  
 */
package leverController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*; //utilizzato per il parsing
//
import usefulClass.LeverActualValue;
import usefulClass.LeverPosition;
import usefulClass.LeverStatus;

/**
 * 
 * Send GET request to the Golang HTTP server
 * @author kizzone
 *
 */
public class HttpClient {
	
	private static HttpURLConnection con;
	//http://localhost:8080/accelerometer
	
	private static final String httpServerURL = "http://vps529937.ovh.net:8080/accelerometer";
	
	
	/**
	 * getter for server URL
	 * @return httpServerURL
	 */
	public  String getHttpserverurl() {
		return httpServerURL;
	}


	/**
	 * @return LeverActualValue fro the HTTP server
	 * @throws IOException
	 */
	
	public  LeverActualValue requestLeverStatus () throws IOException {
		
		
		LeverStatus status;
		int acceleration;
	
		try {

            URL myurl = new URL(this.getHttpserverurl());
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            //roba di java...
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            
            //System.out.println("(http client) Creating the object position\n");
            // Now do the magic.
            LeverPosition data = new Gson().fromJson(content.toString(), LeverPosition.class);
            // Show it.
           // System.out.println(data.toString() + "value of X is " + data.getXCord());            
            //a questo punto devo analizzare la posizione della leva e decidere se e' in stato di QUIETE 
            //o e' stata tirata con un accelerazione
                        
            if (data.getXCord() ==  0 && data.getYCord() == 0 && data.getZCord() == 0){
                
                //System.out.println(" (HTTP client)The lever is in QUIET state\n Creating the return");
               
                status = LeverStatus.QUIET;
                acceleration = 0;        
        		LeverActualValue statoAttualeLeva = new LeverActualValue(acceleration, status);
                                
                return statoAttualeLeva;
                
            }
            else {
            	//in questo caso la leva era in movimento e con un accelerazione diversa da 0 (l'accelerazione di una leva e' su un solo asse)
                status = LeverStatus.PULLED;
                acceleration = data.getXCord();
                //System.out.println(" (HTTP client) The lever is in MOTION state\n Creating the return");
                LeverActualValue statoAttualeLeva = new LeverActualValue(acceleration, status);
                
                return statoAttualeLeva;
                
            }
           
            
        } finally {
            con.disconnect();
        }
        
   }

}
	
	
	

	
