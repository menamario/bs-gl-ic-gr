package mx.com.bsmexico.layoutstool.app;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.nav.ComponentsLayoutNavigator;
import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute.NavNode;
import mx.com.bsmexico.layoutstool.core.api.nav.TreeNavNode;

/**
 * @author jchr
 *
 */
public class MainNavigator extends ComponentsLayoutNavigator {

	public MainNavigator(List<ComponentLayout> components) throws IllegalArgumentException {
		super(components);
		init();
	}

	protected void init() {
		setStyle("-fx-brackground-color : black");
		final Light.Distant light = new Light.Distant();
		light.setAzimuth(-135);
		light.setElevation(30);
		final Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0f);
		setEffect(lighting);
		//setPrefSize(200, 200);
		buildControl();
		setVisible(true);
		// TODO acomodar tamaños y posiciones de
		
		
	}

	@Override
	protected Parent getGraphicNavigatorNode(NavNode navNode, NODETYPE type) {
		Parent graphic = null;
		switch (type) {
		case ROOT_NODE: {
			break;
		}
		case SECTION_NODE: {
			break;
		}
		case NAV_NODE: {
			if (navNode.getImg() == null) {
				graphic = new ToggleButton(navNode.getTitle());
			} else {
				graphic = new ToggleButton(navNode.getTitle(), new ImageView(new Image(navNode.getImg())));
			}
			graphic.setId(navNode.getName());
			// TODO implementar style class
			// graphic.getStyleClass().add(navNode.getStyleClass());
			break;
		}
		case LEAF_NODE: {
			if (navNode.getImg() == null) {
				graphic = new ToggleButton(navNode.getTitle());
			} else {
				graphic = new ToggleButton(navNode.getTitle(), new ImageView(new Image(navNode.getImg())));
			}
			graphic.setId(navNode.getName());
			// TODO implementar style class
			// graphic.getStyleClass().add(navNode.getStyleClass());

			break;
		}
		default: {
			graphic = new Pane();
		}

		}

		return graphic;
	}

	/**
	 * 
	 */
	private void buildControl() {
		// Root first level : main pane
		Pane rootLayout = new Pane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();				
				// TODO acomodar tamaños y posiciones de las secciones
			}
		};
		// second level : sections
		List<TreeNavNode> sections = root.getChilden();
		if (!sections.isEmpty()) {
			sections.forEach(s -> buildNavigation(s, rootLayout));
		}
		
		getChildren().add(rootLayout);

	}

	/**
	 * @param section
	 * @param rootLayout
	 */
	private void buildNavigation(final TreeNavNode section, final Pane rootLayout) {
		if (section != null) {
			if (!section.getChilden().isEmpty()) {
				final VBox graphicSection = new VBox() {
					@Override
					protected void layoutChildren() {
						super.layoutChildren();
						setAlignment(Pos.TOP_LEFT);
						setSpacing(5);
						// TODO acomodar tamaños y posiciones los nodos principales
					}
				};
				section.getChilden().forEach(s -> {
					graphicSection.getChildren().add(s.getGraphic());
				});
				rootLayout.getChildren().add(graphicSection);
			}
		}
	}

}
