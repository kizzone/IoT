package it.unipr.iotlab.iot2018.cf.server;

import java.util.Random;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;

import it.unipr.iotlab.iot2018.cf.server.resources.ObservableResources;

public class UpdateTask extends TimerTask {

	private CoapResource mCoapRes;
	public UpdateTask(CoapResource coapRes) {
	mCoapRes = coapRes;
	}
	@Override
	public void run() {
		
		
		ObservableResources.setValue( new Random().nextInt(20) );
		mCoapRes.changed();
	}
} 