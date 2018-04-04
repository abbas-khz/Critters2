package assignment5;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class Main extends Application{
	public Canvas canvas;
	public GridPane gp=new GridPane();
	public FlowPane fp=new FlowPane();
	private GraphicsContext gc;

	public static void main(String[] args) {
		 launch(args);
	}


	@Override
	public void start(Stage primaryStage){


		Scene scene=new Scene(gp,300,400);
		primaryStage.setTitle("Critters");
//		Canvas canvas = new Canvas(300, 400);
//		GridPane gp=new GridPane();

		Text t1=new Text("World width: ");
		gp.getChildren().add(t1);
		gp.setConstraints(t1,0,0);
		TextField width_box=new TextField("50");
		width_box.setMaxWidth(60);
		gp.setConstraints(width_box,0,2);
		gp.getChildren().add(width_box);

		Text t2=new Text("World height:");
		gp.getChildren().add(t2);
		gp.setConstraints(t2,1,0);
		TextField height_box=new TextField("50");
		height_box.setMaxWidth(60);
		gp.setConstraints(height_box,1,2);
		gp.getChildren().add(height_box);

		Text t3=new Text("Add Critters to world:");
		gp.setConstraints(t3,0,4);
		gp.getChildren().add(t3);
		TextField t3_field=new TextField("type");
		gp.setConstraints(t3_field,0,6);
		gp.getChildren().add(t3_field);
		t3_field.setMaxWidth(60);
		TextField t3_field2=new TextField("number");
		gp.setConstraints(t3_field2,1,6);
		t3_field2.setMaxWidth(60);
		gp.getChildren().addAll(t3_field2);
		//combo box commented out for possible future addition
//		ComboBox cb=new ComboBox();
//		cb.getItems().addAll("Critter1","Craig1");
//		gp.add(cb,0,10);
		Button btn1=new Button("Add Critters to world");
		gp.setConstraints(btn1,0,8);
		gp.getChildren().addAll(btn1);
		Text t4=new Text("Critter addition Status");
		t4.setFill(Color.BLUE);
		gp.getChildren().addAll(t4);
		gp.setConstraints(t4,1,8);


		Button btn2=new Button("Generate World");
		gp.setConstraints(btn2,0,12);
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
			primaryStage.setHeight(760);
			primaryStage.setWidth(530);
			primaryStage.setResizable(false);

		});
		gp.getChildren().addAll(btn2);

		gp.setPadding(new Insets(10,10,10,10));
		gp.setHgap(5);
		gp.setVgap(5);

//		fp.getChildren().addAll(btn1,btn2);


//		gp.getChildren().add(canvas);
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
		primaryStage.show();

//		secondScene(primaryStage);
	}

	private void viewComp(ArrayList<Critter> critterList, GraphicsContext gc){
	}

	private void secondScene(Stage stg,int width,int height,Scene original){
		canvas=new Canvas(width,height);
		gc=canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);
		gc.strokeLine(40, 10, 10, 40);
		gc.fillOval(10, 60, 30, 30);
		Button b1=new Button("yeehaw");
		b1.setOnAction(event -> {
			stg.setScene(original);
			return;
		});
		fp.getChildren().addAll(b1);
		fp.getChildren().addAll(canvas);
		Scene s2=new Scene(fp);
//		stg.setHeight(height+30);
//		stg.setWidth(width+30);
		stg.setScene(s2);
		stg.setResizable(true);
		stg.show();
	}

}
