package it.unipr.iotlab.iot2018.cf.server;

import it.unipr.iotlab.iot2018.cf.server.resources.*;

import org.eclipse.californium.core.CoapServer;

public class IotPjServer extends CoapServer {

	public static void main(String[] args) {
		IotPjServer server = new IotPjServer(); //creating a new server
		HelloWorldResource hello = new HelloWorldResource("hello-world"); //creating a new istance of the resource HelloWo...
		ObservableResources obsRes = new ObservableResources("obs-res");
		//what is the difference?????????
		obsRes.setObservable(true);
		obsRes.getAttributes().setObservable(); 
		
		server.add(hello,obsRes); //adding resources to the server
		server.start(); //start the server
	}

}
