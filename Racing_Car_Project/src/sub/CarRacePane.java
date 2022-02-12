package sub;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CarRacePane extends StackPane {

	// Attributes
	private BorderPane root  = null;
	private HBox       hBox  = null;
	private Pane       pane  = null;
	private Group      group = null;
	
	private Label    [] carNumLabel = new Label[4];
	private TextField[] carNumField = new TextField[4];
	
	private Rectangle[] carLowerBodyRect  = new Rectangle[4];
	private Circle   [] carTiresCircle    = new Circle[8];
	private Polygon  [] carUpperBodyPoly  = new Polygon[4];
	private Line     [] carHorizontalPath = new Line[16];
	
	private Timeline animation1 = null, animation2 = null, animation3 = null, animation4 = null;
	
	private Button raceBeginButton = null;
	private Label  notificationLabel = null;
	
	private Double[] m = null, n = null, o = null, p = null;
	private Random random = new Random();

	/**
	 * No argument constructor
	 */
	public CarRacePane() {
		
		setUpGUI();   // Invoke set up the GUI method
		
		
		// Event fired When clicking on Button
		raceBeginButton.setOnAction(event -> {
			
			try {
				
				setCarMovement();   // Invoke setCarMovement method
				
				// Set the Cycle count for animation1, 2, 3 and 4
				animation1.setCycleCount(Timeline.INDEFINITE);
				animation2.setCycleCount(Timeline.INDEFINITE);
				animation3.setCycleCount(Timeline.INDEFINITE);		
				animation4.setCycleCount(Timeline.INDEFINITE);
				
				// Allow animation1, 2, 3 and 4 to play
				animation1.play();
				animation2.play();
				animation3.play();
				animation4.play();
				
			} catch(NullPointerException ee) {
				
				System.err.println("Worst");
				notificationLabel.setText("Number must be from 0 to 100");  // Inform the user to enter number
				
			}
			
		});
		
	}
	
	/**
	 *  Method to set up the Graphical User Interface which is user friendly
	 */
	private void setUpGUI() {
		
		// Instances of layouts
		root  = new BorderPane();
		hBox  = new HBox(5);           hBox.setAlignment(Pos.CENTER);    hBox.setStyle("-fx-background-color: AQUA");
		group = new Group();
		
		// Instances of Button and Label
		raceBeginButton    = new Button("START");
		notificationLabel  = new Label();         notificationLabel.setTextFill(Color.RED);
		
		// Instances for Wrapper Classes of double
		m = new Double[] {12.0 , 35.0, 24.0 , 10.0, 36.0, 10.0, 48.0, 35.0};
		n = new Double[] {12.0 , 35.0, 24.0 , 10.0, 36.0, 10.0, 48.0, 35.0};
		o = new Double[] {12.0 , 35.0, 24.0 , 10.0, 36.0, 10.0, 48.0, 35.0};
		p = new Double[] {12.0 , 35.0, 24.0 , 10.0, 36.0, 10.0, 48.0, 35.0};
		
		
		for (int i = 0; i < carNumField.length; i++) {
			
			// Instances of TextFields and Labels
			carNumField[i] = new TextField();        carNumField[i].setMaxWidth(40);                
			carNumLabel[i] = new Label();            carNumLabel[i].setText("Car " + (i + 1) + ": ");
			
			hBox.getChildren().addAll(carNumLabel[i], carNumField[i]);   // Add all the TextFields and Labels
			
			// Instances of Rectangle, Circle, Polygon and lines
			carLowerBodyRect [i]     = new Rectangle(50, 10);  carLowerBodyRect [i].setFill(Color.AQUA); 
			carTiresCircle   [i]     = new Circle(7);          // Will instantiate 1 - 4 Rectangles
			carTiresCircle   [i + 4] = new Circle(7);          // Will instantiate 5 - 8 Rectangles
			carUpperBodyPoly [i]     = new Polygon();    carUpperBodyPoly [i].setFill(Color.BLUE);
			carHorizontalPath[i]     = new Line();
			carHorizontalPath[i+4]   = new Line();      // Will instantiate 5 - 8  Lines
			carHorizontalPath[i+8]   = new Line();      // Will instantiate 9 - 12 Lines
			carHorizontalPath[i+12]  = new Line();      // Will instantiate 13 - 16 lines
			
			// Add to group Pane
			group.getChildren().addAll(carLowerBodyRect[i], carTiresCircle[i], carTiresCircle[i + 4], carUpperBodyPoly[i], carHorizontalPath[i]);
			group.getChildren().addAll(carHorizontalPath[i+4], carHorizontalPath[i+8], carHorizontalPath[i+12]);
			
			
			carUpperBodyPoly[i].getPoints().addAll(12.0, 35.0 + (i * 100), 24.0, 10.0 + (i * 100), 
					                               36.0, 10.0 + (i * 100), 48.0, 35.0 + (i * 100));  // Draw Trapezium Shape for car body
			carLowerBodyRect[i].setX(6);             carLowerBodyRect[i].setY(35 + (i * 100));       // Draw Rectangles for car body
			carTiresCircle  [i].setCenterX(17);      carTiresCircle[i].setCenterY(52 + (i * 100));   // Draw Circles for Back car tires
			carTiresCircle  [i+4].setCenterX(42);    carTiresCircle[i+4].setCenterY(52 + (i * 100)); // Draw Circles for Front car tires
			
			carHorizontalPath[i].setStartX(3);       carHorizontalPath[i].setStartY(52 + (i * 100));     // Draw start of Lines for car tracks 
			carHorizontalPath[i].setEndX(1300);      carHorizontalPath[i].setEndY(52 + (i * 100));      // to where it will ends
			
			carHorizontalPath[i+4].setStartX(3);     carHorizontalPath[i+4].setStartY(8 + (i * 100));   // Draw starting of top horizontal lines
			carHorizontalPath[i+4].setEndX(1300);    carHorizontalPath[i+4].setEndY(8 + (i * 100));    // to where it ends
			
			carHorizontalPath[i+8].setStartX(3);     carHorizontalPath[i+8].setStartY(8 + (i * 100));    // Draw vertical lines for initial car position
			carHorizontalPath[i+8].setEndX(3);       carHorizontalPath[i+8].setEndY(52 + (i * 100));       // The line ends here at this point
			
			carHorizontalPath[i+12].setStartX(1300); carHorizontalPath[i+12].setStartY(8 + (i * 100)); // Draw vertical lines for finishing point
			carHorizontalPath[i+12].setEndX(1300);   carHorizontalPath[i+12].setEndY(52 + (i * 100));    // Endpoint of a line
			
		}
		
		hBox.getChildren().add(raceBeginButton);
		
		pane.getChildren().add(group);
		
		root.setTop(hBox);
		root.setCenter(pane);
		root.setBottom(notificationLabel);  BorderPane.setAlignment(notificationLabel, Pos.CENTER);
		
		getChildren().add(root);
		
	}
	
	/**
	 * set Car movement method will shift the car 1 unit forward every time it gets called.
	 */
	private void setCarMovement() {
		
		if (carNumField[0].getText().equals(null) || /*carNumField[0].getText().equals(null) &&*/
		       carNumField[1].getText().equals(null) || /*carNumField[1].getText().equals(null) &&*/
		       carNumField[2].getText().equals(null) || /*carNumField[2].getText().equals(null) &&*/
		       carNumField[3].getText().equals(null) /*&& carNumField[3].getText().equals(null)*/) {
			
			   notificationLabel.setText("Number must be from 0 to 100");
			
		} else {
			
			try {   
				
				// Animation 1 Instance
				animation1 = new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(carNumField[0].getText())), e -> {
					
					
					if(carLowerBodyRect[0].getX() >= 1250) {
						
						animation1.stop();  // Stop the car when reaching the finishing point
							
					} else {
						
						// Update to position of a car otherwise for position
						carUpperBodyPoly[0].getPoints().set(0, m[0] += 1);
						carUpperBodyPoly[0].getPoints().set(2, m[2] += 1);
						carUpperBodyPoly[0].getPoints().set(4, m[4] += 1);
						carUpperBodyPoly[0].getPoints().set(6, m[6] += 1);
						carLowerBodyRect[0].setX(carLowerBodyRect[0].getX() + 1);
						carTiresCircle  [0].setCenterX(carTiresCircle[0].getCenterX()+1);
						carTiresCircle  [4].setCenterX(carTiresCircle[4].getCenterX()+1);
						
						// Update the colour of Trapezium as the car moves
						carUpperBodyPoly[0].setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
						
					}
					
				}));
				
				// Animation 2 Instance
				animation2 = new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(carNumField[1].getText())), e -> {
					
					if(carLowerBodyRect[1].getX() >= 1250) {
						
						animation2.stop();   // Stop the car when reaching the finishing point
							
					}else {
						
						// Update to position of a car otherwise for position
						carUpperBodyPoly[1].getPoints().set(0, n[0] += 1);
						carUpperBodyPoly[1].getPoints().set(2, n[2] += 1);
						carUpperBodyPoly[1].getPoints().set(4, n[4] += 1);
						carUpperBodyPoly[1].getPoints().set(6, n[6] += 1);
						carLowerBodyRect[1].setX(carLowerBodyRect[1].getX() + 1);
						carTiresCircle  [1].setCenterX(carTiresCircle[1].getCenterX()+1);
						carTiresCircle  [5].setCenterX(carTiresCircle[5].getCenterX()+1);
						
						// Update the colour of Trapezium as the car moves
						carUpperBodyPoly[1].setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
						
					}
					
				}));
				
				// Animation 3 Instance
				animation3 = new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(carNumField[2].getText())), e -> {
					
					if(carLowerBodyRect[2].getX() >= 1250) {
						
						animation3.stop();      // Stop the car when reaching the finishing point
							
					} else {
						
						// Update to position of a car otherwise for position
						carUpperBodyPoly[2].getPoints().set(0, o[0] += 1);
						carUpperBodyPoly[2].getPoints().set(2, o[2] += 1);
						carUpperBodyPoly[2].getPoints().set(4, o[4] += 1);
						carUpperBodyPoly[2].getPoints().set(6, o[6] += 1);
						carLowerBodyRect[2].setX(carLowerBodyRect[2].getX() + 1);
						carTiresCircle  [2].setCenterX(carTiresCircle[2].getCenterX()+1);
						carTiresCircle  [6].setCenterX(carTiresCircle[6].getCenterX()+1);
						
						// Update the colour of Trapezium as the car moves
						carUpperBodyPoly[2].setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
						carUpperBodyPoly[2].setStroke(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
						
				    }
					
				}));
				
				// Animation 4 Instance
				animation4 = new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(carNumField[3].getText())), e -> {
					
					if(carLowerBodyRect[3].getX() >= 1250) {
						
						animation4.stop();         // Stop the car when reaching the finishing point
							
					} else {
						
						// Update to position of a car otherwise for position
						carUpperBodyPoly[3].getPoints().set(0, p[0] += 1);
						carUpperBodyPoly[3].getPoints().set(2, p[2] += 1);
						carUpperBodyPoly[3].getPoints().set(4, p[4] += 1);
						carUpperBodyPoly[3].getPoints().set(6, p[6] += 1);
						carLowerBodyRect[3].setX(carLowerBodyRect[3].getX() + 1);
						carTiresCircle  [3].setCenterX(carTiresCircle[3].getCenterX()+1);
						carTiresCircle  [7].setCenterX(carTiresCircle[7].getCenterX()+1);
						
					}
					
				}));

				
			} catch(NumberFormatException ee) {
				
				System.err.println("Bad");
				notificationLabel.setText("Number must be from 0 to 100");    // Notify the user what to do
				
			} catch (NullPointerException e) {
				
				System.err.println("Worse");
				notificationLabel.setText("Number must be from 0 to 100");				// Notify the user what to do
			}
		}
		
	}

}
