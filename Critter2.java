package assignment5;

/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * <Brendan Ngo>
 * <bdn378>
 * <16215>
 * <Neil Charles>
 * <nc23227>
 * <16215>
 * Slip days used: <0>
 * Spring 2017
 *
 * This Critter runs if energy is greater than 50 and walks otherwise. Only fights algae. It's a herbivore 
 * An Antelope
 * It sits in one place and reproduces if it has good health 
 * if the health is from 100 to 50 then run. Less than that then walk because it will be tired.
 * It keeps track of the number of miles it has attempted to walk.
 */

public class Critter2 extends Critter {
	int gender; //0 is male, 1 is female
	int milesWalkedAttempted;
	
	public Critter2() {
		gender = Critter.getRandomInt(2);
		milesWalkedAttempted = 0;
	}


	//the following four functions were added by Abbas Al Khazal as a part of assignment5
	@Override
	public CritterShape viewShape(){return CritterShape.TSHAPE;}
	@Override
	public javafx.scene.paint.Color viewFillColor(){return javafx.scene.paint.Color.BROWN;}
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){return javafx.scene.paint.Color.YELLOW;}
	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BROWN;}
	
	//Critter2 sits in one place and reproduces if it has good health 
	//if the health is from 100 to 50 then run. Less than that then walk because it will be tired.
	@Override
	public void doTimeStep() {
		if (getEnergy() >= 100) {
			Critter2 c = new Critter2();
			
			
			reproduce(c, Critter.getRandomInt(8));
		}
		else if ((getEnergy() > 50) && (getEnergy() < 100)){
			run(Critter.getRandomInt(8));
			milesWalkedAttempted += 2;
		}
		else {
			milesWalkedAttempted += 1;
			walk(Critter.getRandomInt(8));
		}
		
	}

	@Override
	public boolean fight(String oponent) {
		if(getEnergy() < 25) {run(Critter.getRandomInt(8));}
		return oponent.equals("@");
	}
	
	@Override
	public String toString() {
		return "2";
	}

}
