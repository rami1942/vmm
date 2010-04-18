package org.dyndns.buefield.vmm.action;

import org.dyndns.bluefield.craysas.annotation.PublicAccessAuth;
import org.seasar.struts.annotation.Execute;

/**
 * トップページ
 * @author rami1942
 *
 */
public class IndexAction {
	/**
	 * サイトトップページ.
	 * トップページの性格上、公開アクセスとする。
	 * @return 常にindex.jspを返し、ページレンダリングを行う。
	 */
	@PublicAccessAuth
    @Execute(validator = false)
	public String index() {
        return "index.jsp";
	}
    
    /**
     * ポータルトップページ. ログイン成功直後に表示されるページ。
     * @return 常にtop.jspを返し、ページレンダリングを行う。
     */
    @Execute(validator = false)
    public String top() {
    	return "top.jsp";
    }
}
