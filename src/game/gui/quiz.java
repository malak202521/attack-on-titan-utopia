package game.gui;



	import java.util.ArrayList;

	import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.TextArea;
	import javafx.scene.layout.AnchorPane;
	import javafx.scene.text.Font;
	import javafx.stage.Stage;

	public class quiz extends Application {

		public final ArrayList<String> pizzaDir = fillPizza();
		public final ArrayList<String> beverageDir = fillBeverage();
		public Button pizza;
		public Button beverage;
		public TextArea text;
		public AnchorPane anchorpane;
		public Scene scene;
		public Stage stage;
		private ArrayList<String> fillPizza() {
			ArrayList<String> a = new ArrayList<>();
			a.add("Pizza1_100_R");
			a.add("Pizza2_200_F");
			a.add("Pizza3_300_R");
			a.add("Pizza4_400_F");
			a.add("Pizza5_500_L");
			return a;
		}

		private ArrayList<String> fillBeverage() {
			ArrayList<String> a = new ArrayList<>();
			a.add("Beverage1_100_R_Cold");
			a.add("Beverage2_200_F_Hot");
			a.add("Beverage3_300_R_Hot");
			a.add("Beverage4_400_F_Cold");
			a.add("Beverage5_500_L_Cold");
			return a;
		}
		public void start(Stage arg0) throws Exception {
			anchorpane= new AnchorPane();
			scene= new Scene(anchorpane,1000, 500);
			stage= new Stage();
			stage.show();
			
		}


}
