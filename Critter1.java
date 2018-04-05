package assignment5;
/* CRITTERS Critter1.java
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
 * This Critter does not ever move but fights and reproduces every encounter if energy level over 70.
 * It's a Venus Flytrap
 * The child gets the same number of leaves as the parent when it is reproduces
 *
 * Additions for assignment5 by Abbas Al Khazal:
 *
 * reproduction function changed so that it used look and only leaves offspring if the direction chosen
 * is empty or has Algae in it
 *
 */



public class Critter1 extends Critter {
	int gender; //0 is male, 1 is female
	int leaves;
	
	public Critter1() {
		gender = Critter.getRandomInt(2);
		leaves = 0;
	}






	// The following four functions were added by Abbas Al Khazal after adopting the code for assignment5
	@Override
	public CritterShape viewShape() {
		return CritterShape.VSHAPE;
	}
	@Override
	public javafx.scene.paint.Color viewFillColor(){return javafx.scene.paint.Color.PURPLE;}
	@Override
	public javafx.scene.paint.Color viewOutlineColor(){return javafx.scene.paint.Color.PURPLE;}
	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.PURPLE;}


	//Critter1 is stationary and does nothing unless encountered
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		leaves++;
	}
	
	//Critter1 reproduces every encounter if energy level over 70 and always fights
	//adjusted for assignment5 by Abbas Al Khazal to include Critter.look
	@Override
	public boolean fight(String oponent) {
		// TODO Auto-generated method stub
		if(getEnergy() > 70) {
			Critter1 c = new Critter1();
			c.leaves = leaves;
			int dir=Critter.getRandomInt(8);
			String lookValue=look(dir,false);
			if(lookValue==null || lookValue=="@"){
				reproduce(c, dir);
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() { 
		return "1";
	}

}
