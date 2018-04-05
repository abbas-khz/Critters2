package assignment5;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application{
	private Canvas canvas;
	private GridPane gp=new GridPane();
	private FlowPane fp=new FlowPane();
	private boolean setConstraints=false;
	private GraphicsContext gc;
	private static String myPackage;

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

//		Params.world_height=0;
//		Params.world_width=0;
//		Critter.displayWorld(gp);
//		Canvas canvas = new Canvas(300, 400);
//		GridPane gp=new GridPane();
		primaryStage.setHeight(790);
		primaryStage.setWidth(530);
		controlComp(gp,primaryStage);

//		Text t1=new Text("World width: ");
//		gp.getChildren().add(t1);
//		gp.setConstraints(t1,0,0);
//		TextField width_box=new TextField("50");
//		width_box.setMaxWidth(60);
//		gp.setConstraints(width_box,0,2);
//		gp.getChildren().add(width_box);
//
//		Text t2=new Text("World height:");
//		gp.getChildren().add(t2);
//		gp.setConstraints(t2,1,0);
//		TextField height_box=new TextField("50");
//		height_box.setMaxWidth(60);
//		gp.setConstraints(height_box,1,2);
//		gp.getChildren().add(height_box);
//
//		Text t3=new Text("Add Critters to world:");
//		gp.setConstraints(t3,0,4);
//		gp.getChildren().add(t3);
//		TextField t3_field=new TextField("type");
//		gp.setConstraints(t3_field,0,6);
//		gp.getChildren().add(t3_field);
//		t3_field.setMaxWidth(60);
//		TextField t3_field2=new TextField("number");
//		gp.setConstraints(t3_field2,1,6);
//		t3_field2.setMaxWidth(60);
//		gp.getChildren().addAll(t3_field2);
//		//combo box commented out for possible future addition
////		ComboBox cb=new ComboBox();
////		cb.getItems().addAll("Critter1","Craig1");
////		gp.add(cb,0,10);
//		Button btn1=new Button("Add Critters to world");
//		gp.setConstraints(btn1,0,8);
//		gp.getChildren().addAll(btn1);
//		Text t4=new Text("Critter addition Status");
//		t4.setFill(Color.BLUE);
//		gp.getChildren().addAll(t4);
//		gp.setConstraints(t4,1,8);
//
//
//		Button btn2=new Button("Generate World");
//		gp.setConstraints(btn2,0,12);
//		btn2.setOnAction(event -> {
//			int width;
//			int height;
//			width=Integer.parseInt(width_box.getText());
//			height=Integer.parseInt(height_box.getText());
//			Params.world_height=height;
//			Params.world_width=width;
////			gc=canvas.getGraphicsContext2D();
////			gc.setFill(Color.GREEN);
////			gc.setStroke(Color.BLUE);
////			gc.setLineWidth(5);
////			gc.strokeLine(40, 10, 10, 40);
////			gc.fillOval(10, 60, 30, 30);
////			gp.getChildren().addAll(canvas);
////			gp.setColumnSpan(canvas,2);
////			gp.setConstraints(canvas,0,16);
//
//
//			Critter.displayWorld(gp);
//			primaryStage.setHeight(760);
//			primaryStage.setWidth(530);
//			primaryStage.setResizable(false);
//
//		});
//		gp.getChildren().addAll(btn2);
//
//		gp.setPadding(new Insets(10,10,10,10));
//		gp.setHgap(5);
//		gp.setVgap(5);

//		fp.getChildren().addAll(btn1,btn2);


//		gp.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(true);
//		secondScene(primaryStage);
	}

	private void controlComp(GridPane gp,Stage primaryStage){

		//Setting up buttons & text fields
//		gp.setGridLinesVisible(true);
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
		TextField t3_field=new TextField("type");
		t3_field.setMaxHeight(5);
		gp.setConstraints(t3_field,0,6);
		t3_field.setDisable(true);
		gp.getChildren().add(t3_field);
		t3_field.setMaxWidth(60);
		TextField t3_field2=new TextField("number");
		t3_field2.setMaxHeight(5);
		gp.setConstraints(t3_field2,1,6);
		t3_field2.setDisable(true);
		t3_field2.setMaxWidth(60);
		gp.getChildren().addAll(t3_field2);

		//combo box commented out for possible future addition
//		ComboBox cb=new ComboBox();
//		cb.getItems().addAll("Critter1","Craig1");
//		gp.add(cb,0,10);

		Text t4=new Text("Critter addition Status");
		t4.setFill(Color.BLUE);
		gp.getChildren().addAll(t4);
		gp.setConstraints(t4,1,8);

		Button btn1=new Button("Add Critters to world");
		gp.setConstraints(btn1,0,8);
		btn1.setDisable(true);
		gp.getChildren().addAll(btn1);


		Button btn2=new Button("Generate World");
		gp.setConstraints(btn2,0,12);
		gp.getChildren().addAll(btn2);

		Button btn3=new Button("Reset world");
		gp.setConstraints(btn3,1,12);
		gp.getChildren().addAll(btn3);


		Button stepButton=new Button("time step");
		stepButton.setDisable(true);
		gp.setConstraints(stepButton,3,0);
		Spinner<Integer> stepNumber =new Spinner<>();
		SpinnerValueFactory<Integer> numStepsFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.MAX_VALUE,1);
		stepNumber.setMaxSize(70,5);
		stepNumber.setValueFactory(numStepsFactory);
//		stepNumber.setEditable(true);
		gp.setConstraints(stepNumber,3,1);
		gp.getChildren().addAll(stepButton,stepNumber);
//		gp.getChildren().addAll(stepButton);

		Button seedButton = new Button("set Seed");
		gp.setConstraints(seedButton,4,0);
		TextField seedField=new TextField("Enter seed");
		seedField.setOnKeyTyped(event -> {
			seedField.setStyle("-fx-text-fill: black");
		});
		seedField.setMaxSize(100,5);
		gp.setConstraints(seedField,4,1);
		gp.getChildren().addAll(seedButton,seedField);
//		gp.getChildren().addAll(seedButton);




		//Adding critters to world (add command)
		btn1.setOnAction(event -> {
			String className;
			int critterNum;
			try {
				className=t3_field.getText();
				critterNum=Integer.parseInt(t3_field2.getText());
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
		});

		//generate and the world initially
		btn2.setOnAction(event -> {
			int width;
			int height;
			width=Integer.parseInt(width_box.getText());
			height=Integer.parseInt(height_box.getText());
			Params.world_height=height;
			Params.world_width=width;
//			gc=canvas.getGraphicsContext2D();
//			gc.setFill(Color.GREEN);
//			gc.setStroke(Color.BLUE);
//			gc.setLineWidth(5);
//			gc.strokeLine(40, 10, 10, 40);
//			gc.fillOval(10, 60, 30, 30);
//			gp.getChildren().addAll(canvas);
//			gp.setColumnSpan(canvas,2);
//			gp.setConstraints(canvas,0,16);


			Critter.displayWorld(gp);
			primaryStage.setResizable(false);
			width_box.setDisable(true);
			height_box.setDisable(true);
			stepButton.setDisable(false);
			t3_field.setDisable(false);
			t3_field2.setDisable(false);
			btn1.setDisable(false);

		});

		//resets world and remove the current view
		btn3.setOnAction(event -> {
			Critter.clearWorld();
			width_box.setDisable(false);
			height_box.setDisable(false);
			Params.world_width=0;
			Params.world_height=0;
			Critter.displayWorld(gp);
			stepButton.setDisable(true);
			t3_field.setDisable(true);
			t3_field2.setDisable(true);
			btn1.setDisable(true);
		});

		//do world time steps
		stepButton.setOnAction(event -> {
			int number_of_steps;
			number_of_steps=stepNumber.getValue();
			stepNumber.setEditable(false);
			stepNumber.getValueFactory().setValue(1);
//			stepNumber.setEditable(true);
			for (int i=0;i<number_of_steps;i++){ Critter.worldTimeStep(); }
			Critter.displayWorld(gp);
		});

		//change the seed
		seedButton.setOnAction(event -> {
			runStatsCommand("Algae",t4);
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

		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(5);
		gp.setVgap(5);

	}

	private void runStatsCommand(String className,Text status){
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
		catch (Exception e){
			status.setText("Error processing");
			status.setFill(Color.RED);
		}
		Scene scene=new Scene(fp,500,100);

		stage.setScene(scene);
		Text txt=new Text();
		txt.setText(statsResult);
		fp.getChildren().add(txt);
		stage.show();

	}

}
