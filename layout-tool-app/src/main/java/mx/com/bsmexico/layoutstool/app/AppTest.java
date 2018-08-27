package mx.com.bsmexico.layoutstool.app;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mx.com.bsmexico.layoutstool.core.api.layouts.GeneralLayoutFactory;

/**
 * Application
 * 
 * @author jchr
 *
 */
public class AppTest extends Application {
	// Pane root
	private VBox root;

	@Override
	public void init() throws Exception {
		root = new VBox();
		final ClassLoader classLoader = getClass().getClassLoader();
		final File layout = new File(classLoader.getResource("xml/layouts/beneficiariosLayout.xml").getFile());
		final BeneficiarioTable table = new BeneficiarioTable(new GeneralLayoutFactory(layout, false),
				new ColumnBeneficiarioFactory());
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(25, 25, 25, 25));
		root.getChildren().add(table);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(root, 1024, 768, Color.web("#f4f4f4"));
		// scene.getStylesheets().add(getClass().getClassLoader().getResource("app.css").toExternalForm());
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
