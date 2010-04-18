package org.dyndns.buefield.vmm.action;

import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

import static org.seasar.framework.unit.S2Assert.*;

@RunWith(Seasar2.class)
public class AuthActionTest {
	private TestContext ctx;

	private AuthAction authAction;
	
	public void before() {
		ctx.register(AuthAction.class);
	}
	
	public void testIndex() {
		assertEquals("index.jsp", authAction.index());
	}
}
