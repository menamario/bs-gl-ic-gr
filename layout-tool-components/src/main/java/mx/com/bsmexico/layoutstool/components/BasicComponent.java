package mx.com.bsmexico.layoutstool.components;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.Layout;
import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute;

public class BasicComponent implements ComponentLayout {
	@Override
	public Layout getLayout() {
		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		NavRoute route = null;
		route = navRuoteBuilder.addNode("NODE1", "NODE_1"/* ,0,false,getImageInput("pdf.jpeg", 100, 100) */)
				.addNode("NODE4", "NODE_4").addNode("NODE5", "NODE_5").build();
		final Pane pane = new Pane();
		pane.getChildren().add(new Button("Component 1"));
		return new Layout.LayoutBuilder("l1").route(route).node(pane).build();
	}

}
