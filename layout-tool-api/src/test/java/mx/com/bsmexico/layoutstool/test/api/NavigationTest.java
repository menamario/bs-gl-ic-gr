package mx.com.bsmexico.layoutstool.test.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.Layout;
import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute;
import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute.NavNode;
import mx.com.bsmexico.layoutstool.core.api.nav.Navigator;
import mx.com.bsmexico.layoutstool.core.api.nav.TreeNavNode;

/**
 * @author jchr
 *
 */

public class NavigationTest {

	/**
	 * @throws FileNotFoundException
	 */
	@Test
	public void testNavRoute() throws FileNotFoundException {
		// Creation NavRoute
		final ClassLoader classLoader = getClass().getClassLoader();
		File img = new File(classLoader.getResource("print_layout_single.png").getFile());
		final FileInputStream imgStream = new FileInputStream(img);
		final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
		final NavRoute.NavNode node = new NavRoute.NavNode("NODE3", "NODE_3", imgStream, 2, false);
		final NavRoute route = navRuoteBuilder.addNode("NODE1", "NODE_1")
				.addNode("NODE2", "NODE_2", 1, false, imgStream).addNode(node).build();
		Assert.assertTrue("/NODE_1/NODE_2/NODE_3".equals(route.toString()));
		Assert.assertArrayEquals(new String[] { "TEST" }, new String[] { route.getSection() });
		// Navigation
		List<String> track = new ArrayList<>();
		// Forward
		Assert.assertTrue(route.hasNext());
		NavRoute.NavNode currentNode = route.next();
		track.add(currentNode.getName());
		Assert.assertTrue(route.hasNext());
		currentNode = route.next();
		track.add(currentNode.getName());
		Assert.assertTrue(route.hasNext());
		currentNode = route.next();
		track.add(currentNode.getName());
		Assert.assertFalse(route.hasNext());
		route.goStart();
		Assert.assertTrue(route.hasNext());
		currentNode = route.next();
		track.add(currentNode.getName());
		// Back
		route.goEnd();
		Assert.assertFalse(route.hasNext());
		Assert.assertTrue(route.hasPrevious());
		currentNode = route.previous();
		track.add(currentNode.getName());
		Assert.assertTrue(route.hasPrevious());
		currentNode = route.previous();
		track.add(currentNode.getName());
		Assert.assertTrue(route.hasPrevious());
		currentNode = route.previous();
		track.add(currentNode.getName());
		Assert.assertFalse(route.hasPrevious());
		Assert.assertTrue(route.hasNext());
		Assert.assertTrue("NODE1,NODE2,NODE3,NODE1,NODE3,NODE2,NODE1".equals(StringUtils.join(track, ",")));

	}

	/**
	 * @throws FileNotFoundException
	 */
	@Test
	public void testNavigator() throws FileNotFoundException {
		final List<ComponentLayout> cpms = this.getListBasicComponenetsLayouts();
		class AppNavigatorImpl extends Navigator {

			public void test() {
				final List<NavRoute> routes = new ArrayList<>();
				cpms.forEach(cmp -> {
					if (cmp.getLayout() != null && cmp.getLayout().getRoute() != null) {
						routes.add(cmp.getLayout().getRoute());
					}
				});
				this.builLogicNavigationTree(routes);

				final TreeNavNode tree = root;
				Assert.assertTrue("ROOT".equals(tree.getId()));
				Assert.assertNotNull(tree);
				Assert.assertNotNull(tree.getChilden());
				Assert.assertTrue(tree.getChilden().size() == 1);
				// First Level : Sections
				TreeNavNode child = tree.getChilden().get(0);
				Assert.assertTrue("TEST".equals(child.getId()));
				// N Levels
				Assert.assertTrue(child.getChilden().size() == 1);
				child = child.getChilden().get(0);
				Assert.assertTrue("NODE1".equals(child.getId()));
				Assert.assertTrue(child.getChilden().size() == 2);
				Assert.assertTrue("NODE4".equals(child.getChilden().get(0).getId()));
				Assert.assertTrue("NODE2".equals(child.getChilden().get(1).getId()));
				Assert.assertTrue(child.getChilden().get(0).getChilden().size() == 1);
				Assert.assertTrue("NODE5".equals(child.getChilden().get(0).getChilden().get(0).getId()));
				Assert.assertTrue(child.getChilden().get(1).getChilden().size() == 2);
				Assert.assertTrue("NODE3".equals(child.getChilden().get(1).getChilden().get(0).getId()));
				Assert.assertTrue("NODE6".equals(child.getChilden().get(1).getChilden().get(1).getId()));
				// Last level
				Assert.assertTrue(child.getChilden().get(0).getChilden().get(0).getChilden().isEmpty());
				Assert.assertTrue(child.getChilden().get(1).getChilden().get(0).getChilden().isEmpty());
				Assert.assertTrue(child.getChilden().get(1).getChilden().get(1).getChilden().isEmpty());
				Assert.assertNull(child.getChilden().get(0).getChilden().get(0).getElement());
				Assert.assertNull(child.getChilden().get(1).getChilden().get(0).getElement());
				Assert.assertNull(child.getChilden().get(1).getChilden().get(1).getElement());

				Assert.assertNull(child.getChilden().get(0).getChilden().get(0).getElement());
				Assert.assertNull(child.getChilden().get(1).getChilden().get(0).getElement());
				Assert.assertNull(child.getChilden().get(1).getChilden().get(1).getElement());

				// Find Nodes
				TreeNavNode node = findTreeNode(root, "NODE6");
				Assert.assertNotNull(node);
				Assert.assertTrue(node.getId().equals("NODE6"));

				// Deep
				final int deep = node.deep();				
				Assert.assertTrue(deep == 4);

				// Get Branch
				List<TreeNavNode> branch = this.getBranchByRoute(root, routes.get(0));
				Assert.assertTrue(branch.size() == 3);
			}

			@Override
			protected Parent getGraphicNavigatorNode(NavNode navNode, NODETYPE type) {
				return null;
			}

		}

		final AppNavigatorImpl nav = new AppNavigatorImpl();
		nav.test();
	}

	/**
	 * @return
	 */
	private List<ComponentLayout> getListBasicComponenetsLayouts() {
		final List<ComponentLayout> cpms = new ArrayList<>();
		cpms.add(new ComponentLayout() {
			@Override
			public Layout getLayout() {
				final NavRoute.BuilderNavRoute navRuoteBuilder = new NavRoute.BuilderNavRoute("TEST");
				final NavRoute route = navRuoteBuilder.addNode("NODE1", "NODE_1").addNode("NODE4", "NODE_4")
						.addNode("NODE5", "NODE_5").build();
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
		return cpms;
	}

}
