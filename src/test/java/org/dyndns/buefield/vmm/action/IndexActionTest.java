package org.dyndns.buefield.vmm.action;

import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

import static org.seasar.framework.unit.S2Assert.*;

@RunWith(Seasar2.class)
public class IndexActionTest {
	private TestContext ctx;
	
	private IndexAction indexAction;
	
	public void before() {
		ctx.register(IndexAction.class);
	}
	
	public void testIndex() {
		assertEquals("index.jsp", indexAction.index());
	}
}
