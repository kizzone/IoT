/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipr.iotlab.iot2018.cf.client;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP.Code;
import org.eclipse.californium.core.coap.Request;
import java.util.Scanner;
import java.util.Set;
import org.eclipse.californium.core.WebLink; 
//CoapClient client = new CoapClient("coap://127.0.0.1:5683/<resname>");
import org.eclipse.californium.core.server.resources.DiscoveryResource;

/**
 * @author domenico
 */

public class IotPjClient extends CoapClient {
    
    
    
    
    public static void main(String[] args){
        
        String discovery = ".well-known/core";
        CoapClient dummyClient = new CoapClient("coap://127.0.0.1:5683/"+discovery);
        Request discoveryRequest = new Request(Code.GET); 
        //Synchronously send the GET message (blocking call)
        CoapResponse discoveryCoapResp = dummyClient.advanced(discoveryRequest); 
        System.out.println(Utils.prettyPrint(discoveryCoapResp));

        for(;;){
            
            //DiscoveryResource DR = new DiscoveryresourceResource();
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter the resource: ");
            String resource = reader.nextLine();
            if(resource.equalsIgnoreCase("exit")) break;
            System.out.println("You want the value of " + resource);
            CoapClient client = new CoapClient("coap://127.0.0.1:5683/"+resource);
            Request request = new Request(Code.GET); 
            //Synchronously send the GET message (blocking call)
            CoapResponse coapResp = client.advanced(request); 
            System.out.println(Utils.prettyPrint(coapResp));
            
        }

        
    }
    
}
