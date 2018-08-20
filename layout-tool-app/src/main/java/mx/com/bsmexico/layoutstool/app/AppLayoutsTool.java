package mx.com.bsmexico.layoutstool.app;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.services.CLServiceProvider;

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
		final CLServiceProvider provider = CLServiceProvider.getInstance();
		List<ComponentLayout> components = provider.getComponents();
		root = new GridPane();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
		final Pane workArea = new Pane();
		workArea.setPrefSize(500, 768);		
		final MainNavigator navigator = new MainNavigator(components, workArea);
		root.add(navigator, 0, 0);
		root.add(workArea, 1, 0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(root, 1024, 768, Color.web("#f4f4f4"));
		scene.getStylesheets().add(getClass().getClassLoader().getResource("app.css").toExternalForm());
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
