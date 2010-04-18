package org.dyndns.buefield.vmm.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dyndns.bluefield.craysas.components.AuthComponentInterface;
import org.dyndns.buefield.vmm.entity.User;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

/**
 * 認証サービス.
 * Serviceはデフォルトではprototypeで生成されるため、httpSession使用については問題なし(個別リクエストに対してそれぞれnewされる)
 * @author rami1942
 *
 */
@Component(name="authService", instance=InstanceType.REQUEST)
public class AuthService implements AuthComponentInterface {
	public static final String USERID_KEY="user_id";
	
	@Resource protected HttpServletRequest httpServletRequest;
	@Resource protected HttpSession httpSession;
	@Resource protected JdbcManager jdbcManager;
	
	private Logger logger = Logger.getLogger(AuthService.class);
		
	/**
	 * ログイン処理.
	 * ユーザIDとパスワードを受け取り,認証を行う. 認証成功時にはセッションを初期化後,セッションに認証情報を登録する.
	 * セッションを初期化するのは意図しない操作によりセッションが汚染されていないことを保障するセキュリティ上の理由による.
	 * @param userid ログイン対象となるユーザID
	 * @param password ログイン対象となるパスワード
	 * @return ログイン成功時にはtrueを, 失敗時にはfalseを返す.
	 */
	public boolean login(String userid, String password) {
		if (userid == null || password == null) {
			logger.info("パラメータ不正状態でloginが呼ばれました");
			return false;
		}

		User user = jdbcManager.from(User.class).where("user_name=? and password_hash=sha1(?)", userid, password).getSingleResult();
		if (user == null) {
			logger.warn("ログイン失敗 :" + (userid == null ? "null" : userid));
			return false;			
		}
				
		// ログイン成功後の処理
		logger.info("ログイン成功 :" + userid);
		// セッション汚染回避のため、セッションをクリアして再生成後、ログインフラグを付与
		if (httpSession != null) {
			httpSession.invalidate();
		}
		httpSession = httpServletRequest.getSession(true);
		httpSession.setAttribute(USERID_KEY, user.id);
		return true;
	}
	
	/**
	 * 現在セッションがログイン中かどうかを確認する.
	 * @return ログイン中であればtrueを, そうでなければfalseを返す.
	 */
	public boolean isLogin() {
		Object o = httpSession.getAttribute(USERID_KEY);
		return o != null;
	}
	/**
	 * 認証エラー時の処理。
	 * @return "/auth/relogin?redirect=true"を常に返す。
	 */
	public String processErrorPage() {
		return "/auth/relogin?redirect=true";
	}
	
	/**
	 * ログアウト処理。セッションを無効化するので本メソッドを呼んだ後の遷移先は公開アクセスでないと、認証エラーページとなるので注意。
	 */
	public void logout() {
		httpSession.invalidate();
	}
}
