package org.dyndns.buefield.vmm.form;


import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Required;

/**
 * ログインフォーム.
 * {@see AuthAction}
 *
 */
public class LoginForm {

	@Required(arg0 = @Arg(key="auth.userid.label"))
	public String userid;
	
	@Required(arg0 = @Arg(key="auth.password.label"))
	public String password;
}
