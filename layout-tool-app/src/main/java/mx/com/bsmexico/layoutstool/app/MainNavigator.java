package mx.com.bsmexico.layoutstool.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

	private Map<Integer, StackPane> levels;
	private Pane workArea;

	public MainNavigator(final List<ComponentLayout> components, final Pane workArea) throws IllegalArgumentException {
		super(components);
		levels = new HashMap<>();
		this.workArea = workArea;
		init();
	}

	protected void init() {
		setStyle("-fx-brackground-color : black");
		/*
		 * final Light.Distant light = new Light.Distant(); light.setAzimuth(-135);
		 * light.setElevation(30); final Lighting lighting = new Lighting();
		 * lighting.setLight(light); lighting.setSurfaceScale(5.0f);
		 * setEffect(lighting);
		 */
		setPrefSize(400, 768);
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
		// Root : main pane
		HBox rootLayout = new HBox() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				// TODO acomodar tamaños y posiciones de las secciones
			}
		};
		rootLayout.setAlignment(Pos.CENTER_LEFT);
		// first level : sections
		List<TreeNavNode> sections = root.getChilden();
		getChildren().add(rootLayout);
		if (!sections.isEmpty()) {
			sections.forEach(s -> buildNavigation(s, rootLayout));
		}
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
						setSpacing(5);
						// TODO acomodar tamaños y posiciones los nodos principales
					}
				};
				rootLayout.getChildren().add(graphicSection);
				section.getChilden().forEach(s -> {
					graphicSection.getChildren().add(s.getGraphic());
					buildLevels(s, rootLayout);
				});

			}
		}
	}

	/**
	 * @param node
	 * @param rootLayout
	 */
	private void buildLevels(final TreeNavNode node, final Pane rootLayout) {
		final Integer deepLevel = node.deep();
		StackPane levelPane = null;
		final Parent graphic = node.getGraphic();
		if (node.getType() == NODETYPE.NAV_NODE) {
			if ((levelPane = this.levels.get(deepLevel)) == null) {
				levelPane = new StackPane() {
					@Override
					protected void layoutChildren() {
						super.layoutChildren();
						// TODO acomodar tamaños y posiciones de las secciones
					}
				};
				this.levels.put(deepLevel, levelPane);
				rootLayout.getChildren().add(levelPane);
				levelPane.setVisible(false);
			}
			final VBox submenu = new VBox() {
				@Override
				protected void layoutChildren() {
					super.layoutChildren();
					// TODO acomodar tamaños y posiciones de las secciones
					setSpacing(5);
				}
			};
			// bind with id
			submenu.setId(node.getId());
			node.getChilden().forEach(s -> {
				submenu.getChildren().add(s.getGraphic());
			});
			submenu.setVisible(false);
			levelPane.getChildren().add(submenu);

			node.getChilden().forEach(n -> buildLevels(n, rootLayout));

			// Set event when click button
			graphic.setOnMouseClicked(evt -> {
				final StackPane _levelPane = levels.get(deepLevel);
				_levelPane.setVisible(true);
				_levelPane.getChildren().forEach(sm -> {
					// find submenu and set visible
					sm.setVisible(node.getId().equals(sm.getId()));
				});
				// hide others levels
				hideLevels(deepLevel);
			});

		} else {
			if (node.getType() == NODETYPE.LEAF_NODE) {
				// Set event when click button
				graphic.setOnMouseClicked(evt -> {
					// show work area
					if (node.getElement() != null && node.getElement() instanceof ComponentLayout) {
						ComponentLayout component = (ComponentLayout) node.getElement();
						if (!workArea.getChildren().isEmpty()) {
							workArea.getChildren().remove(0);
						}
						workArea.getChildren().add(component.getLayout().getNode());					
					}
				});
			}
		}

	}

	/**
	 * @param excludedLevel
	 */
	private void hideLevels(final int initLevel) {
		Set<Integer> klevels = levels.keySet();
		StackPane level = null;
		for (Integer key : klevels) {
			if (key > initLevel) {
				level = levels.get(key);
				level.setVisible(false);
				level.getChildren().forEach(nd -> {
					nd.setVisible(false);
				});
			}

		}
	}

}
