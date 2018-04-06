package assignment5;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

public class Main extends Application{
	private Canvas canvas;
	private GridPane gp=new GridPane();
	private FlowPane fp=new FlowPane();
	private boolean animationStatus=false;
	private GraphicsContext gc;
	private static String myPackage;
	private long counter=0;
	private boolean setUpComboBoxes=false;
	private int[] saveVals={Params.look_energy_cost, Params.walk_energy_cost, Params.run_energy_cost, Params.refresh_algae_count};
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	public static void main(String[] args) {
		 launch(args);
	}


	@Override
	public void start(Stage primaryStage){
		Scene scene=new Scene(gp,300,400);
		Critter.displayWorld(gp);
		primaryStage.setTitle("Critters");

		primaryStage.setHeight(800);
		primaryStage.setWidth(570);
		controlComp(gp,primaryStage);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
//		secondScene(primaryStage);
	}

	private void controlComp(GridPane gp,Stage primaryStage){

		//Setting up buttons & text fields
//		gp.setGridLinesVisible(true);
		List<String> critterTypes=new ArrayList<>();

		Text t1=new Text("World width: ");
		gp.getChildren().add(t1);
		gp.setConstraints(t1,0,0);
		TextField width_box=new TextField("10");
		width_box.setMaxWidth(60);
		gp.setConstraints(width_box,0,1);
		gp.getChildren().add(width_box);

		Text t2=new Text("World height:");
		gp.getChildren().add(t2);
		gp.setConstraints(t2,1,0);
		TextField height_box=new TextField("10");
		height_box.setMaxWidth(60);
		gp.setConstraints(height_box,1,1);
		gp.getChildren().add(height_box);

		Text t3=new Text("Add Critters to world:");
		gp.setConstraints(t3,0,4);
		gp.getChildren().add(t3);

		ComboBox typesOfCritters=new ComboBox();
//		for(String type:critterTypes){ typesOfCritters.getItems().add(type);}
		gp.setConstraints(typesOfCritters,0,6);
		Spinner<Integer> critterNumber=new Spinner<>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
		critterNumber.setEditable(true);
		gp.setConstraints(critterNumber,1,6);
		typesOfCritters.setDisable(true);
		critterNumber.setDisable(true);
		critterNumber.setMaxWidth(70);
		typesOfCritters.setValue("Craig");
		typesOfCritters.setMaxWidth(120);
		gp.getChildren().addAll(typesOfCritters,critterNumber);



		Text t4=new Text("Critter addition Status");
		t4.setFill(Color.BLUE);
		gp.getChildren().addAll(t4);
		gp.setConstraints(t4,1,8);

		Button addButton=new Button("Add Critters to world");
		gp.setConstraints(addButton,0,8);
		addButton.setDisable(true);
		gp.getChildren().addAll(addButton);


		Button createButton=new Button("Generate World");
		gp.setConstraints(createButton,0,12);
		gp.getChildren().addAll(createButton);

		Button resetButton=new Button("Reset world");
		gp.setConstraints(resetButton,1,12);
		gp.getChildren().addAll(resetButton);


		Button stepButton=new Button("time step");
		stepButton.setDisable(true);
		gp.setConstraints(stepButton,3,0);
		Spinner<Integer> stepNumber =new Spinner<>();
		SpinnerValueFactory<Integer> numStepsFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.MAX_VALUE,1);
		stepNumber.setMaxSize(70,5);
		stepNumber.setValueFactory(numStepsFactory);
		stepNumber.setEditable(true);
		stepNumber.setDisable(true);
		gp.setConstraints(stepNumber,3,1);
		gp.getChildren().addAll(stepButton,stepNumber);
//		gp.getChildren().addAll(stepButton);

		Button seedButton = new Button("set Seed");
		gp.setConstraints(seedButton,4,0);
		gp.setHalignment(seedButton,HPos.CENTER);
		TextField seedField=new TextField("Enter seed");
		seedField.setMaxWidth(120);
		seedButton.setMaxWidth(70);
		gp.setHalignment(seedField,HPos.CENTER);
		seedField.setOnKeyTyped(event -> {
			seedField.setStyle("-fx-text-fill: black");
		});
		gp.setConstraints(seedField,4,1);
		gp.getChildren().addAll(seedButton,seedField);
//		gp.getChildren().addAll(seedButton);

		Button quitButton=new Button("Quit");
		gp.setConstraints(quitButton,3,12);
		gp.setHalignment(quitButton,HPos.LEFT);
		gp.getChildren().add(quitButton);

		Button animateButton=new Button("Animate");
		gp.setConstraints(animateButton,4,8);
		gp.getChildren().add(animateButton);
		animateButton.setDisable(true);
		gp.setHalignment(animateButton,HPos.CENTER);

		int timeInterval=5;
		Spinner<Integer> timeIntervalBox=new Spinner<>();
		SpinnerValueFactory.IntegerSpinnerValueFactory times=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,25,4);
		times.setAmountToStepBy(1);
		times.setValue(0);
		times.setWrapAround(true);
		timeIntervalBox.setValueFactory(times);
		timeIntervalBox.setMaxWidth(60);
		gp.setConstraints(timeIntervalBox,4,6);
		gp.getChildren().add(timeIntervalBox);
		timeIntervalBox.setDisable(true);
		gp.setHalignment(timeIntervalBox,HPos.CENTER);

		Text animateSpeed=new Text("Animation speed");
		gp.setConstraints(animateSpeed,4,4);
		gp.setHalignment(animateSpeed,HPos.CENTER);
		gp.getChildren().add(animateSpeed);


		Text worldStats=new Text();
		worldStats.setFill(Color.PURPLE);
		gp.setConstraints(worldStats,0,14);
		gp.setColumnSpan(worldStats,4);
		gp.getChildren().addAll(worldStats);

		Button statsButton=new Button("Run stats for:");
		gp.setConstraints(statsButton,3,6);
		ComboBox statsMenu = new ComboBox();
		statsMenu.setDisable(true);
		statsButton.setDisable(true);
		gp.setConstraints(statsMenu,3,8);
		statsMenu.setMaxWidth(120);
		statsMenu.setValue("Craig");
		gp.getChildren().addAll(statsButton,statsMenu);

		Timeline timeline=new Timeline();

		//Adding critters to world (add command)
		addButton.setOnAction(event -> {
			String className;
			int critterNum;
			try {
//				className=t3_field.getText();
//				critterNum=Integer.parseInt(t3_field2.getText());
				className=typesOfCritters.getValue().toString();
				critterNum=Integer.parseInt(critterNumber.getEditor().getText());

				for (int i=0;i<critterNum;i++){
					Critter.makeCritter(className);
				}
				t4.setText("Added "+critterNum+" "+className);
				t4.setFill(Color.GREEN);
			}
			catch (InvalidCritterException e){
				t4.setText("Invalid Critter Type");
				t4.setFill(Color.RED);
			}
			catch (NumberFormatException e){
				t4.setText("Invalid number");
				t4.setFill(Color.RED);
			}
			Critter.displayWorld(gp);
			makeStats(worldStats,critterTypes);
		});

		//generate and the world initially
		createButton.setOnAction(event -> {
			int width;
			int height;
			width=Integer.parseInt(width_box.getText());
			height=Integer.parseInt(height_box.getText());
			Params.world_height=height;
			Params.world_width=width;
			Critter.displayWorld(gp);
			primaryStage.setResizable(false);
			width_box.setDisable(true);
			height_box.setDisable(true);
			stepButton.setDisable(false);
//			t3_field.setDisable(false);
//			t3_field2.setDisable(false);
			critterNumber.setDisable(false);
			typesOfCritters.setDisable(false);

			addButton.setDisable(false);
			stepNumber.setDisable(false);
			stepButton.setDisable(false);
			animateButton.setDisable(false);
			timeIntervalBox.setDisable(false);
			resetButton.setDisable(false);
			createButton.setDisable(true);
			statsMenu.setDisable(false);
			statsButton.setDisable(false);

			if(!setUpComboBoxes) {
				File folder = new File(System.getProperty("user.dir") + "\\src\\assignment5");

				for (File entry : folder.listFiles()) {
					String clsName;
					clsName = entry.getName();
					clsName = clsName.replaceAll(".java", "");
					try {
						Class<?> cls = Class.forName(myPackage + "." + clsName);
						Critter crt = (Critter) cls.newInstance();
						if (crt instanceof Critter) {
							typesOfCritters.getItems().add(clsName);
							statsMenu.getItems().add(clsName);
							critterTypes.add(clsName);
						}


					} catch (Exception e) {
					}
				}
				Params.look_energy_cost = saveVals[0];
				Params.walk_energy_cost = saveVals[1];
				Params.run_energy_cost = saveVals[2];
				Params.refresh_algae_count = saveVals[3];
				setUpComboBoxes=true;
			}
			makeStats(worldStats, critterTypes);
		});

		//resets world and remove the current view
		resetButton.setOnAction(event -> {
			animationStatus=true;
			animate(timeline);
			animateButton.setText("Animate");
			Critter.clearWorld();
			width_box.setDisable(false);
			height_box.setDisable(false);
			Params.world_width=0;
			Params.world_height=0;
			Critter.displayWorld(gp);
			stepButton.setDisable(true);
//			t3_field.setDisable(true);
//			t3_field2.setDisable(true);
			typesOfCritters.setDisable(true);
			critterNumber.setDisable(true);

			addButton.setDisable(true);
			stepNumber.setDisable(true);
			animateButton.setDisable(true);
			timeIntervalBox.setDisable(true);

			worldStats.setText("");
			resetButton.setDisable(true);
			createButton.setDisable(false);
			statsButton.setDisable(true);
			statsMenu.setDisable(true);
		});

		//do world time steps
		stepButton.setOnAction(event -> {
			int number_of_steps;
			number_of_steps=stepNumber.getValue();
			number_of_steps=Integer.parseInt(stepNumber.getEditor().getText());
			stepNumber.getEditor().setText("1");
//				stepNumber.setEditable(false);
			stepNumber.getValueFactory().setValue(1);
//			stepNumber.setEditable(true);
			for (int i=0;i<number_of_steps;i++){
				Critter.worldTimeStep();
				makeStats(worldStats,critterTypes);
			}
			Critter.displayWorld(gp);
		});

		//change the seed
		seedButton.setOnAction(event -> {
			int seed;
			try {
				seed=Integer.parseInt(seedField.getText());
				Critter.setSeed(seed);
				seedField.setText("SEED CHANGED");
				seedField.setStyle("-fx-text-fill: green");
			}
			catch (NumberFormatException e){
				seedField.setText("NOT VALID");
				seedField.setStyle("-fx-text-fill: red");
			}
		});

		//exit from the program
		quitButton.setOnAction(event -> {
			System.exit(0);
		});

		//set animation with speed
		animateButton.setOnAction(event -> {
			if(!animationStatus){
				addButton.setDisable(true);
				timeIntervalBox.setDisable(true);
				stepButton.setDisable(true);
			}else {
				addButton.setDisable(false);
				timeIntervalBox.setDisable(false);
				stepButton.setDisable(false);
			}
			timeline.getKeyFrames().clear();
			timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500 / timeIntervalBox.getValue()), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Critter.worldTimeStep();
					Critter.displayWorld(gp);
					makeStats(worldStats,critterTypes);
				}
			}));
			if(animateButton.getText().compareTo("Animate")==0){
				animateButton.setText("Pause");
			}else{
				animateButton.setText("Animate");
			}
			animate(timeline);

		});

		//runs the stat function for a particular critter
		statsButton.setOnAction(event -> {
//			try {
//				String statClassName=(String)statsMenu.getValue();
//				List<Critter> statCritters=Critter.getInstances(statClassName);
//				Class specificCritter=Class.forName(myPackage+"."+statClassName);
//				Method method= specificCritter.getMethod("runStats",List.class);
//				method.invoke(statCritters.getClass(),statCritters);
//			}
//			catch (Exception e){}

			String statClassName=(String)statsMenu.getValue();


			runStatsCommand(statClassName);


		});

		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(5);
		gp.setVgap(5);


	}

	private void runStatsCommand(String className){
		Stage stage=new Stage();
		FlowPane fp=new FlowPane();
		stage.setTitle("Stats for "+className);
		String statsResult="";
		try {
			List<Critter> critters = Critter.getInstances(className);
			Class critterType = Class.forName(myPackage + "." + className);
			Method method = critterType.getMethod("runStats", List.class);
			statsResult=(String)method.invoke(className.getClass(), critters);
		}
		catch (Exception e){ }
		Scene scene=new Scene(fp,750,100);

		stage.setScene(scene);
		Text txt=new Text();
		txt.setText(statsResult);
		fp.getChildren().add(txt);
		fp.setColumnHalignment(HPos.CENTER);
		stage.show();

	}

	private void makeStats(Text stats,List<String> critterTypes){
		List<Critter> critterStats;
		stats.setText("");
		int counter=0;
		try {
			for (String type : critterTypes) {
				critterStats=Critter.getInstances(type);
				counter++;
				stats.setText(stats.getText()+type+": "+critterStats.size()+"   ");
				if(counter==4){
					counter=0;
					stats.setText(stats.getText()+"\n");
				}
			}
		}
		catch (Exception e){}
	}

	private void animate(Timeline timeline){
//		Timeline t1=new Timeline();
//		t1.setCycleCount(Animation.INDEFINITE);
//		KeyFrame frame=new KeyFrame(Duration.seconds(0.01),
//				new EventHandler<ActionEvent>() {
//					@Override
//					public void handle(ActionEvent event) {
//						Critter.worldTimeStep();
//						Critter.displayWorld(gp);
//					}
//				});
//		t1.getKeyFrames().add(frame);
//		t1.play();
		if(!animationStatus) {
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			animationStatus=true;
		}else {
			animationStatus=false;
			timeline.stop();
		}
//
//	if(counter==1000){
//		Critter.worldTimeStep();
//		Critter.displayWorld(gp);
//		counter=0;
//	}else {
//		counter++;
//	}



//		while (true){
//			Critter.worldTimeStep();
//
//			try {
//				TimeUnit.MILLISECONDS.sleep(100);
//			}
//			catch (Exception e){
//				System.out.println("BOII");
//			}
//		}
	}

}
