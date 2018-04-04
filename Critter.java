package assignment5;

import javafx.scene.layout.GridPane;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	/**
	 * checks whether or not there is a critter in a passed direction and distance from the current point (Added in project5)
	 * @param direction direction to look in
	 * @param steps 1(false) or 2(true) spaces away from current location
	 * @return the toString() of the critter occupying the spot if it exists and null otherwise
	 */
	protected String look(int direction,boolean steps){
		this.energy-=Params.look_energy_cost;
		int lookCordX;
		int lookCordY;
		lookCordX=this.x_coord;
		lookCordY=this.y_coord;

		int magnatude=1;
		if(steps)
			magnatude=2;

		switch (direction){
			case 0:
				lookCordX+=magnatude;
				break;
			case 1:
				lookCordX+=magnatude;
				lookCordY+=magnatude;
				break;
			case 2:
				lookCordY+=magnatude;
				break;
			case 3:
				lookCordX-=magnatude;
				lookCordY+=magnatude;
				break;
			case 4:
				lookCordX-=magnatude;
				break;
			case 5:
				lookCordX-=magnatude;
				lookCordY-=magnatude;
				break;
			case 6:
				lookCordY-=magnatude;
				break;
			case 7:
				lookCordX+=magnatude;
				lookCordY-=magnatude;
				break;
		}

		for(Critter c:population){
			if(c.x_coord==lookCordX && c.y_coord==lookCordY){
				return c.toString();
			}
		}
		return null;
	}

	private static final double canvasWidth=500;
	private static final double canvasHeight=500;
	private static Canvas canvas=new Canvas(canvasWidth,canvasHeight);
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;


	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	

	
	public static void displayWorld(Object pane) {} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */


	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	


	public static void displayWorld(GridPane gp){
		try {
			makeCritter("Craig");
		}
		catch (Exception e){
			System.out.println("bye");
		}
		GraphicsContext gc;
		gc=canvas.getGraphicsContext2D();
		gc.clearRect(0,0,canvasWidth,canvasHeight);
		if(gp.getChildren().contains(canvas)){
			gp.getChildren().remove(canvas);
		}
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,canvasWidth,canvasHeight);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gp.setColumnSpan(canvas,2);
		gp.setConstraints(canvas,0,16);

		double rowSize;
		double colSize;

		rowSize=canvasWidth/Params.world_height;
		colSize=canvasHeight/Params.world_width;


		for(double i=0;(canvasHeight-i)>=0 ;i+=rowSize) {

			gc.strokeLine(0, i, rowSize*Params.world_height, i);
			if(Math.abs(canvasHeight-(i+rowSize))<=0.000000000001){
				i=canvasHeight;
				gc.strokeLine(0, i, rowSize*Params.world_height, i);
				break;
			}

		}

		for (double i=0;(canvasWidth-i)>=0;i+=colSize){
			gc.strokeLine(i,0,i,colSize*Params.world_width);
			if(Math.abs(canvasWidth-(i+colSize))<=0.000000000001){
				i=canvasWidth;
				gc.strokeLine(i,0,i,colSize*Params.world_width);
				break;
			}
		}

		double yOffset;
		double xOffset;
		yOffset=rowSize/2;
		xOffset=colSize/2;

		for(Critter c:population){
			CritterShape shape;
			shape=c.viewShape();

			gc.setStroke(c.viewOutlineColor());
			gc.setFill(c.viewFillColor());
			drawDiamond(gc,c,rowSize,colSize);
//			switch (shape){
//				case STAR:
//					break;
//				case CIRCLE:
//					gc.fillOval(c.x_coord*rowSize+1,c.y_coord*colSize+1,colSize-2,rowSize-2);
//					gc.strokeOval(c.x_coord*rowSize+1,c.y_coord*colSize+1,colSize-2,rowSize-2);
//					break;
//				case SQUARE:
//					gc.fillRect(c.x_coord*rowSize,c.y_coord*colSize,colSize,rowSize);
//					gc.strokeRect(c.x_coord*rowSize,c.y_coord*colSize,colSize,rowSize);
//					break;
//				case DIAMOND:
//					drawDiamond(gc,c,rowSize,colSize);
//					break;
//				case TRIANGLE:
//					drawTriangle(gc,c,rowSize,colSize);
//					break;
//			}
		}
		gp.getChildren().addAll(canvas);
	}

	private static void drawTriangle(GraphicsContext gc,Critter c, double rowSize, double colSize){
		double []xPoints={c.x_coord*colSize,c.x_coord*colSize+colSize/2,(c.x_coord+1)*colSize,c.x_coord*colSize};
		double []yPoints={(c.y_coord+1)*rowSize,(c.y_coord)*rowSize,(c.y_coord+1)*rowSize,(c.y_coord+1)*rowSize};
		gc.fillPolygon(xPoints,yPoints,4);
		gc.strokePolygon(xPoints,yPoints,4);


	}

	private static void drawDiamond(GraphicsContext gc,Critter c,double rowSize,double colSize){
		double []xPoints={c.x_coord*colSize,c.x_coord*colSize+colSize/2,(c.x_coord+1)*colSize,c.x_coord*colSize+colSize/2,c.x_coord*colSize};
		double []yPoints={c.y_coord*rowSize+rowSize/2,c.y_coord*rowSize,c.y_coord*rowSize+rowSize/2,(c.y_coord+1)*rowSize,c.y_coord*rowSize+rowSize/2};
		gc.fillPolygon(xPoints,yPoints,5);
		gc.strokePolygon(xPoints,yPoints,5);
	}


	/**
	 * Place all the critters in their position in a 2d
	 * string array
	 */
	private static void create2d() {
		int width = Params.world_width;
		int height = Params.world_height;

		dispArr = new String[width+2][height+2];
		for (int i = 0; i < dispArr.length; i++) {
			for (int j = 0; j < dispArr[0].length;j++) {
				dispArr[i][j] = " ";
			}
		}

		dispArr[0][0] = "+";
		dispArr[0][height+1] = "+";
		dispArr[width+1][0] = "+";
		dispArr[width+1][height+1] = "+";

		for (int i = 1; i < width+1; i++) {
			dispArr[i][0] = "-";
			dispArr[i][height+1] = "-";
		}
		for (int i = 1; i < height+1; i++) {
			dispArr[0][i] = "|";
			dispArr[width+1][i] = "|";
		}
		for (Critter c : population) {
			dispArr[c.x_coord+1][c.y_coord+1] = c.toString();
		}
	}


	private boolean hasMoved = false;
	private boolean hasFought = false;
	private static boolean first = true;
	private static String[][] dispArr;

	/**
	 * checks if the critter can walk in the specified direction and does it
	 * @param direction the random direction the critter wants to walk in
	 */
	protected final void walk(int direction) {

		if (!hasMoved) {
			int tempx = x_coord;
			int tempy = y_coord;

			if(direction == 0 || direction == 1 || direction == 7) x_coord++;
			if(direction == 3 || direction == 4 || direction == 5) x_coord--;
			if(direction == 1 || direction == 2 || direction == 3) y_coord--;
			if(direction == 5 || direction == 6 || direction == 7) y_coord++;


			if(x_coord == Params.world_width) x_coord = 0;
			if(x_coord == -1) x_coord = (Params.world_width - 1);
			if(y_coord == Params.world_height) y_coord = 0;
			if(y_coord == -1) y_coord = (Params.world_height - 1);

			for (Critter c : population) {
				if (hasFought && inSameLoc(this, c) && c.energy > 0 && !(this == c)) {
					x_coord = tempx;
					y_coord = tempy;
				}
			}
			hasMoved = true;
		}

		energy -= Params.walk_energy_cost;
	}

	/**
	 * checks if the critter can run in the specified direction and does it
	 * @param direction the random direction the critter wants to run in
	 */
	protected final void run(int direction) {

		if(!hasMoved) {
			int tempx = x_coord;
			int tempy = y_coord;

			if(direction == 0 || direction == 1 || direction == 7) x_coord += 2;
			if(direction == 3 || direction == 4 || direction == 5) x_coord -= 2;
			if(direction == 1 || direction == 2 || direction == 3) y_coord -= 2;
			if(direction == 5 || direction == 6 || direction == 7) y_coord += 2;

			if(x_coord == Params.world_width) x_coord = 0;
			if(x_coord == -1) x_coord = (Params.world_width - 1);
			if(y_coord == Params.world_height) y_coord = 0;
			if(y_coord == -1) y_coord = (Params.world_height - 1);

			if(x_coord == Params.world_width + 1) x_coord = 1;
			if(x_coord == -2) x_coord = (Params.world_width - 2);
			if(y_coord == Params.world_height + 1) y_coord = 1;
			if(y_coord == -2) y_coord = (Params.world_height - 2);

			for (Critter c : population) {
				if (hasFought && inSameLoc(this, c) && c.energy > 0 && !(this == c)) {
					x_coord = tempx;
					y_coord = tempy;
				}
			}

			hasMoved = true;
		}
		energy -= Params.run_energy_cost;

	}

	/**
	 * Creates a new offspring with half the energy as the parent
	 * @param offspring new offspring
	 * @param direction random direction of where it will be located
	 */
	protected final void reproduce(Critter offspring, int direction) {

		if(energy > Params.min_reproduce_energy) {

			offspring.energy = getEnergy()/2;
			energy = getEnergy()/2;

			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord;

			if(direction == 0 || direction == 1 || direction == 7) { offspring.x_coord++; }
			if(direction == 3 || direction == 4 || direction == 5) { offspring.x_coord--; }
			if(direction == 1 || direction == 2 || direction == 3) { offspring.y_coord--; }
			if(direction == 5 || direction == 6 || direction == 7) { offspring.y_coord++; }

			if(offspring.x_coord == Params.world_width) offspring.x_coord = 0;
			if(offspring.x_coord == -1) offspring.x_coord = (Params.world_width - 1);
			if(offspring.y_coord == Params.world_height) offspring.y_coord = 0;
			if(offspring.y_coord == -1) offspring.y_coord = (Params.world_height - 1);

			babies.add(offspring);
		}

	}


	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class critterType = Class.forName(myPackage + "."+ critter_class_name);
			Critter c = (Critter) critterType.newInstance();
			c.x_coord = getRandomInt(Params.world_width);
			c.y_coord = getRandomInt(Params.world_height);
			c.energy = Params.start_energy;
			population.add(c);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InvalidCritterException(critter_class_name);
		}

	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Class critterType;
		try {
			critterType = Class.forName(myPackage + "."+ critter_class_name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new InvalidCritterException(critter_class_name);
		}
		for(Critter c:population) {
			if(critterType.isInstance(c)) {
				result.add(c);
			}
		}
		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static String runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
		return null;
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
		// Complete this method.
	}

	/**
	 * reset the moved flags
	 */
	private static void resetHasMoved() {
		for (Critter c : population) {
			c.hasMoved = false;
			c.hasFought = false;
		}
	}
	/**
	 * do the timestep for each critter, check for encounters,
	 * remove the rest energy from each critter
	 * generate algae and add babies to the population.
	 * Reset all flags
	 */
	public static void worldTimeStep() {
		for (Critter c : population) {
			c.doTimeStep();
		}
		doEncounters();
		updateRestEnergy();
		genAlgae();
		population.addAll(babies);
		babies.clear();
		resetHasMoved();

		int i = 0;
		while (i < population.size()) {
			if (population.get(i).energy <= 0) {
//				System.out.println("dead: " +population.get(i) + "coord " + population.get(i).x_coord +":"+ population.get(i).y_coord);
				population.remove(i);
			}
			else {
				i++;
			}
		}


		// Complete this method.
	}

	/**
	 * check one critter with rest of population until I find one in the same location
	 * invoke the fight of each
	 * check if if A and B are both still alive, and both still in the same position,
	 *  then you must generate two random numbers
	 * remove the critter which dies
	 * repeat the step from the beginning
	 *
	 */
	private static void doEncounters() {

		int outInd = 0;
		int inInd;
		boolean afight = false;
		boolean bfight = false;

		while(outInd < population.size()) {
			inInd = 0;
			while (inInd < population.size()) {

				Critter a = population.get(outInd);
				Critter b = population.get(inInd);

				//check if two critters are in same loc
				if ((outInd != inInd) && (a.energy > 0) && (b.energy > 0) && inSameLoc(a,b)) {
					a.hasFought = true;
					b.hasFought = true;

					afight = a.fight(b.toString());
					bfight = b.fight(a.toString());


					if ((a.energy > 0) && (b.energy > 0) && inSameLoc(a,b)) {
						int aPower = 0;
						int bPower = 0;

						if (afight) {
							aPower = getRandomInt(a.energy);
						}
						else {
							aPower = 0;
						}

						if (bfight) {
							bPower = getRandomInt(b.energy);
						}
						else {
							bPower = 0;
						}

						if (aPower < bPower) {
							b.energy += a.energy/2;
							population.get(outInd).energy = 0;
						}
						else {
							a.energy += b.energy/2;
							population.get(inInd).energy = 0;
						}
					}
				}
				else {
					inInd++;
				}
			}
			outInd++;
		}
	}

	/**
	 * Are two critters in same spot
	 * @param a Critter one
	 * @param b Critter 2
	 * @return true if they are false if not
	 */
	private static boolean inSameLoc(Critter a, Critter b) {
		boolean inSameXLoc = a.x_coord == b.x_coord;
		boolean inSameYLoc = a.y_coord == b.y_coord;
		return inSameXLoc && inSameYLoc;
	}
	/**
	 * Decrease all critters energy by rest energy
	 */
	private static void updateRestEnergy() {
		for (Critter c : population) {
			c.energy -= Params.rest_energy_cost;
		}
	}
	/**
	 * Generate the algae in every timestep
	 */
	private static void genAlgae() {
		String algae = "Algae";
		for (int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter(algae);
//				System.out.println("genAlgae");
			} catch (InvalidCritterException e) {
//				System.out.println("error while algae");
				// TODO Auto-generated catch block
			}
		}
	}
	/**
	 * Display the 2d string array
	 */

}
