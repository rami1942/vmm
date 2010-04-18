package org.dyndns.buefield.vmm.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.dyndns.bluefield.craysas.action.BaseAction;
import org.dyndns.bluefield.craysas.html.config.FormConfig;
import org.dyndns.bluefield.craysas.html.config.ListTableConfig;
import org.dyndns.bluefield.craysas.html.config.ShowBeanConfig;
import org.dyndns.bluefield.craysas.utils.FieldUtil;
import org.dyndns.buefield.vmm.entity.PhysicalHost;
import org.dyndns.buefield.vmm.entity.ResourceAllocation;
import org.dyndns.buefield.vmm.entity.VirtualMachine;
import org.dyndns.buefield.vmm.form.HypervisorForm;
import org.dyndns.buefield.vmm.service.HyperVisorCrawlerService;
import org.dyndns.buefield.vmm.service.PhysicalHostService;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class PhysicalHostAction extends BaseAction{

	// local resources
	private Logger logger = Logger.getLogger(PhysicalHostAction.class);
	
	// DI
	@Resource protected JdbcManager jdbcManager;
	@Resource protected HyperVisorCrawlerService hyperVisorCrawlerService;
	@Resource protected PhysicalHostService physicalHostService;
	// ActionForm
	@ActionForm
	@Resource protected HypervisorForm hypervisorForm;
	
	// render params
	public ListTableConfig<PhysicalHost> listConfig;
	public FormConfig<HypervisorForm> formConfig;
	
	public ListTableConfig<ResourceAllocation> resourceConfig;
	

	/**
	 * ハイパバイザー一覧ページ
	 * @return 常にindex.jspをレンダリング
	 */
	@Execute(validator=false)
	public String index() {
		// TODO: ページネーション
		// TODO: サービス化
		List<PhysicalHost> hvs = physicalHostService.findAll();
		listConfig = new ListTableConfig<PhysicalHost>(PhysicalHost.KEY_PREFIX, PhysicalHost.LIST_ITEMS, hvs);
		return "index.jsp";
	}
	
	public FormConfig<HypervisorForm> getFormConfig() {
		return new FormConfig<HypervisorForm>(hypervisorForm, "doCreate", "登録", HypervisorForm.FORM_ITEMS);
	}

	/**
	 * 新規作成フォーム表示
	 * @return 常にcreate.jspをレンダリング
	 */
	@Execute(validator=false)
	public String create() {
		return "create.jsp";
	}
	
	public ActionMessages preCreateValidator() {
		return FieldUtil.validate(hypervisorForm, HypervisorForm.FORM_ITEMS);
	}
	
	public ActionMessages postCreateValidator() {		
		ActionMessages errors = new ActionMessages();
		if (isEmptyString(hypervisorForm.password)) {
			errors.add("password", getValidationMessage("errors.required", "hypervisor.password.label"));
		}
		if (isEmptyString(hypervisorForm.passwordConfirm)) {
			errors.add("passwordConfirm", getValidationMessage("errors.required", "hypervisor.passwordConfirm.label"));
		}
		if (hypervisorForm.password != null && hypervisorForm.passwordConfirm != null && 
				!hypervisorForm.password.equals(hypervisorForm.passwordConfirm)) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hypervisor.error.password.notmatch"));
		}
		return errors;
	}

	/**
	 * 新規登録処理.
	 * 登録処理に成功した場合、一覧へのリダイレクトを行う
	 * @return "index?redirect=true"固定
	 */
	@Execute(input="create.jsp", validate="preCreateValidator,@,postCreateValidator")
	public String doCreate() {		
		// TODO: Uniqチェック, etc.
		// TODO: サービス化
		hypervisorForm.hvType = 1;
		PhysicalHost hv = new PhysicalHost();
		hv = Beans.createAndCopy(PhysicalHost.class, hypervisorForm).execute();
		
		Date now = new Date();
		hv.createDt = hv.updateDt = now;
		jdbcManager.insert(hv).execute();
		
		addFlushMsg("hypervisor.notice.create.done");
		return "index?redirect=true";
	}
	
	/**
	 * 設定編集フォーム表示
	 * @return 常に"edit.jsp"を返す。
	 */
	@Execute(validator=false)
	public String edit() {
		PhysicalHost hv = checkAndGetEntity();
		if (hv == null) {
			return "index?redirect=true";
		}
		Beans.copy(hv, hypervisorForm).execute();
		// セキュリティのため、password, passwordConfirmはnullにする
		hypervisorForm.password = hypervisorForm.passwordConfirm = null;

		formConfig = new FormConfig<HypervisorForm>(hypervisorForm, HypervisorForm.KEY_PREFIX, "doEdit", "修正", HypervisorForm.FORM_ITEMS);

		return "edit.jsp";
	}
	
	/**
	 * idが設定されているかチェック後、該当するidのエンティティを取得する.
	 * エンティティが正常に取得できない場合にはflushにエラー理由を追加する。
	 * @return 成功時はidに該当するエンティティを返す。失敗時にはnullを返す。
	 */
	private PhysicalHost checkAndGetEntity() {
		if (hypervisorForm.id == null) {
			addErrorMsg("errors.invalid.parameter");
			return null;
		}		
		PhysicalHost hv = jdbcManager.from(PhysicalHost.class).where("id=?", hypervisorForm.id).getSingleResult();
		if (hv == null) {
			addErrorMsg("errors.nosuchentity");
			return null;
		}
		return hv;
	}
	/**
	 * 設定変更処理.
	 * 作成日時については更新対象外。また、パスワードについては値が設定されている(かつ、確認と一致した場合のみ)更新する。
	 * また、更新日時については現時刻を設定する。
	 * 処理後は正常終了の場合には一覧ページへリダイレクト、バリデーション失敗時には設定編集フォームへforwardさせる。
	 * @return 常に"index?redirect=true"を返す。
	 */
	@Execute(input="edit.jsp", validate="@")
	public String doEdit() {
		PhysicalHost hv = checkAndGetEntity();
		if (hv == null) {
			return "index?redirect=true";
		}

		// パスワードが空白の場合には保存する。createDtも保存する。
		Beans.copy(hypervisorForm, hv).excludes("password", "createDt").execute();
		
		hv.hvType = PhysicalHost.HYPERVISOR_ESXI;
		hv.updateDt = new Date();
		if (!isEmptyString(hypervisorForm.password)) {
			hv.password = hypervisorForm.password;
		}
		jdbcManager.update(hv).execute();
		
		addFlushMsg("hypervisor.notice.edit.done");
		return "index?redirect=true";
	}
	
	/**
	 * ハイパバイザーに仮想マシン情報を問い合わせ、結果をDBへ反映する。
	 * 処理の状況(成功・失敗についてはフラッシュメッセージとして次ページで表示する。
	 * @return 常に"index?redirect=true"を返す。
	 */
	@Execute(validator=false)
	public String crawl() {
		PhysicalHost hv = checkAndGetEntity();
		if (hv == null) {
			return "index?redirect=true";
		}
		
		try {
			hyperVisorCrawlerService.crawl(hv);
			hv.lastCrawlDt = new Date();
			jdbcManager.update(hv).execute();
			addFlushMsg("hypervisor.notice.crawl.done");
		} catch (Exception e) {
			logger.error(e.toString());
			addErrorMsg("hypervisor.error.crawl.fail");			
		}
		return "index?redirect=true";
	}

	@SuppressWarnings("unchecked")
	protected static int number2int(Map m, String key) {
		Number i = (Number)m.get(key);
		return i == null ? 0 : i.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Execute(validator=false)
	public String allocation() {
		String sql = "select a.id, a.name, sum(b.vcpu) as vcpu, a.core, sum(b.memory) as vmem, a.memory from hypervisor a left join virtualmachine b on a.id=b.run_on group by a.name order by a.name ";
		List<Map> list = jdbcManager.selectBySql(Map.class, sql).getResultList();
		List<ResourceAllocation> allocs = new LinkedList<ResourceAllocation>();
		
		for (Map m : list) {
			ResourceAllocation r = new ResourceAllocation();
			r.id = (Long)m.get("id");
			r.name = (String)m.get("name");
			r.vmem = number2int(m, "vmem");
			r.vcpu = number2int(m, "vcpu");
			r.core = number2int(m, "core");
			r.memory = number2int(m, "memory");

			r.calloc = (r.core != 0) ? (float)r.vcpu / (float)(r.core * 2) * 100 : 0;
			r.malloc = (r.memory != 0L) ? (float)r.vmem / (float)r.memory * 100 : 0;
			allocs.add(r);
		}
		resourceConfig = new ListTableConfig<ResourceAllocation>(
				ResourceAllocation.KEY_PREFIX, ResourceAllocation.RESOURCE_ITEMS, allocs);
		return "allocation.jsp";
	}
	
	
	public ListTableConfig<VirtualMachine> childConfig;
	
	@Execute(validator=false)
	public String show() {
		PhysicalHost hv = checkAndGetEntity();
		if (hv == null) {
			return "index?redirect=true";
		}
		Beans.copy(hv, hypervisorForm).execute();
		formConfig = new ShowBeanConfig<HypervisorForm>(hypervisorForm, HypervisorForm.KEY_PREFIX, HypervisorForm.FORM_ITEMS);
		
		List<VirtualMachine> children = jdbcManager.from(VirtualMachine.class).where("run_on=?", hv.id).getResultList();
		childConfig = new ListTableConfig<VirtualMachine>(VirtualMachine.KEY_PREFIX, VirtualMachine.CHILDREN_ITEMS, children);
		return "show.jsp";
	}
	

}
