package mx.com.bsmexico.layoutstool.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.Layout;
import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute;

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
		final MainNavigator navigator = new MainNavigator(this.getListBasicComponenetsLayouts());
		root.add(navigator, 0, 0);
		/*final MenuBar bar = new MenuBar();
		final Menu menu = new Menu();		
		menu.setGraphic(getImage("print_layout_single.png",100,100));
		menu.show();
		final Menu subMenu = new Menu();
		subMenu.setGraphic(getImage("menu.jpeg",100,100));
		subMenu.getItems().add(new CustomMenuItem(getImage("pdf.jpeg",100,00)));
		subMenu.getItems().add(new CustomMenuItem(getImage("pdf.jpeg",100,00)));
		menu.getItems().add(subMenu);
		bar.getMenus().add(menu);
		root.add(bar, 0, 0);*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(root, 1024, 768, Color.web("#f4f4f4"));
		scene.getStylesheets().add(getClass().getClassLoader().getResource("app.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@SuppressWarnings("unused")
	private ImageView getImage(final String file, double h, double w) throws FileNotFoundException {
		final FileInputStream input = new FileInputStream(getClass().getClassLoader().getResource(file).getFile());
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(h);
		imageView.setFitWidth(w);
		return imageView;
		
	}
	@SuppressWarnings("unused")
	private FileInputStream getImageInput(final String file, double h, double w) throws FileNotFoundException {
		final FileInputStream input = new FileInputStream(getClass().getClassLoader().getResource(file).getFile());		
		return input;
		
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	private List<ComponentLayout> getListBasicComponenetsLayouts() {
		final List<ComponentLayout> cpms = new ArrayList<>();
		cpms.add(new ComponentLayout() {
			@Override
			public Layout getLayout() {
				final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
				NavRoute route = null;
				try {
					route = navRuoteBuilder.addNode("NODE1", "NODE_1",0,false,getImageInput("pdf.jpeg", 100, 100)).addNode("NODE4", "NODE_4")
							.addNode("NODE5", "NODE_5").build();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new Layout.LayoutBuilder("l1").route(route).node(new Pane()).build();
			}

		});

		cpms.add(new ComponentLayout() {
			@Override
			public Layout getLayout() {
				final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
				final NavRoute route = navRuoteBuilder.addNode("NODE1", "NODE_1").addNode("NODE2", "NODE_2")
						.addNode("NODE3", "NODE3_").build();
				return new Layout.LayoutBuilder("l1").route(route).node(new Pane()).build();
			}

		});

		cpms.add(new ComponentLayout() {
			@Override
			public Layout getLayout() {
				final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
				final NavRoute route = navRuoteBuilder.addNode("NODE1", "NODE_1").addNode("NODE2", "NODE_2")
						.addNode("NODE6", "NODE_6").build();
				return new Layout.LayoutBuilder("l1").route(route).node(new Pane()).build();
			}

		});
		
		cpms.add(new ComponentLayout() {
			@Override
			public Layout getLayout() {
				final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
				NavRoute route = null;
				try {
					route = navRuoteBuilder.addNode("NODE10", "NODE_10",0,false,getImageInput("pdf.jpeg", 100, 100)).addNode("NODE11", "NODE_11")
							.addNode("NODE12", "NODE_12").build();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new Layout.LayoutBuilder("l1").route(route).node(new Pane()).build();
			}

		});
		return cpms;
	}

}
