package usefulClass;

/**
 * Class that rappresent the actual status and acceleration of the lever
 * @author domenico
 *
 */
public class LeverActualValue {
	
	
	private  LeverStatus statoLeva;
	
	private int acceleration;




	public LeverStatus getStatoLeva() {
		return statoLeva;
	}

	public void setStatoLeva(LeverStatus statoLeva) {
		this.statoLeva = statoLeva;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * Costruttore dell'oggeto LeverActualValue
	 * @param acceleration: accelerazione della leva
	 * @param levastato: stato della leva
	 */
	public LeverActualValue (int acceleration, LeverStatus levastato) {
		this.statoLeva = levastato;
		this.acceleration = acceleration;
	}
	
	@Override
	public String toString() {
	        return statoLeva +":"+ acceleration ;
	    }
	
	

}
