package org.dyndns.buefield.vmm.action;

import javax.annotation.Resource;

import org.dyndns.bluefield.craysas.annotation.PublicAccessAuth;
import org.dyndns.buefield.vmm.form.LoginForm;
import org.dyndns.buefield.vmm.service.AuthService;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

/**
 * 認証処理 <br/>
 * @author rami1942
 *
 */
public class AuthAction {

	@Resource protected AuthService authService;
	
	// Action form
	@ActionForm
	@Resource protected LoginForm loginForm;
	
	
	/**
	 * /auth/以下のデフォルトページ. ログインフォームを表示する.
	 * ログイン前に使用するページのため, 非認証状態でのアクセスを許可している.
	 * @return 常にindex.jspを返し,ログインフォームをレンダリングする.
	 */
	@PublicAccessAuth
	@Execute(validator=false)
	public String index() {
		return "index.jsp";
	}
	
	/**
	 * {@link AuthAction#index()}同様, ログインフォームを表示する.
	 * @return 常にindex.jspを返し,ログインフォームをレンダリングする.
	 */
	@PublicAccessAuth
	@Execute(validator=false)
	public String login() {
		return "index.jsp";
	}
	
	/**
	 * 非認証状態で認証が必要なページをアクセスした際の飛び先。
	 * @return　常にrelogin.jspを返し、メッセージつきのログインフォームをレンダリングする。
	 */
	@PublicAccessAuth
	@Execute(validator=false)
	public String relogin() {
		return "relogin.jsp";
	}
	
	/**
	 * {@link AuthAction#index()}や{@link AuthAction#login()}のSubmit先。
	 * バリデーション後認証を行い, 認証結果を基に遷移を決定する。
	 * @return 成功時はトップページへリダイレクト. 失敗時はバリデーション失敗例外をスローしてindex.jspへ。
	 * @throws ActionMessagesException ログイン失敗時に送出。SAStrutsのインターセプターが受け取って、エラーメッセージつきでindex.jspへ遷移。
	 */
	@PublicAccessAuth
	@Execute(input="index.jsp")
	public String doLogin() {
		if (authService.login(loginForm.userid, loginForm.password)) {
			return "../top?redirect=true";
		} else {
			throw new ActionMessagesException("errors.invalid.auth.login");
		}
	}
	
	/**
	 * ログアウト処理
	 * @return /へリダイレクト.
	 */
	@Execute(validator=false)
	public String logout() {
		authService.logout();
		return "/?redirect=true";
	}
}
