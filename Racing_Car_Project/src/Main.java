import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sub.CarRacePane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		CarRacePane carRacePane = new CarRacePane();
		
		Scene sc = new Scene(carRacePane, 1350, 500);
		
		primaryStage.setTitle("RACING CAR");
		primaryStage.setScene(sc);
		primaryStage.show();

	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
