package mx.com.bsmexico.layoutstool.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Application
 * 
 * @author jchr
 *
 */
public class AppLayoutsTool extends Application {
	// Pane root
	private GridPane root;

	@Override
	public void init() throws Exception {
		root = new GridPane();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(root, 1024, 768, Color.web("#f4f4f4"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
