package org.dyndns.buefield.vmm.action;

import java.util.List;

import javax.annotation.Resource;

import org.dyndns.bluefield.craysas.action.BaseAction;
import org.dyndns.bluefield.craysas.html.config.FormConfig;
import org.dyndns.bluefield.craysas.html.config.ListTableConfig;
import org.dyndns.buefield.vmm.entity.PjUser;
import org.dyndns.buefield.vmm.form.PjUserForm;
import org.dyndns.buefield.vmm.service.PjUserService;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

public class PjUserAction extends BaseAction {
	@Resource protected PjUserService pjUserService;
	
	@ActionForm
	@Resource protected PjUserForm pjUserForm;
	
	public ListTableConfig<PjUser> indexConfig;
	
	@Execute(validator=false)
	public String index() {
		List<PjUser> list = pjUserService.findAll();
		indexConfig = new ListTableConfig<PjUser>(PjUserForm.KEY_PREFIX, PjUserForm.LIST_FIELDS, list);
		return "index.jsp";
	}
	
	public FormConfig<PjUserForm> getCreateConfig() {
		return new FormConfig<PjUserForm>(pjUserForm, "doCreate", "登録", PjUserForm.EDIT_FIELDS);
	}	
	
	@Execute(validator=false)
	public String create() {
		return "create.jsp";
	}
	
	@Execute(input="create.jsp")
	public String doCreate() {
		PjUser u = Beans.createAndCopy(PjUser.class, pjUserForm).execute();
		pjUserService.insert(u);
		return "index?redirect=true";
	}
	
	public FormConfig<PjUserForm> getEditConfig() {
		return new FormConfig<PjUserForm>(pjUserForm, "doEdit", "更新", PjUserForm.EDIT_FIELDS);
	}

	@Execute(validator=false)
	public String edit() {
		PjUser u = pjUserService.findById(pjUserForm.id);
		if (u == null) {
			throw new ActionMessagesException("errors.nosuchentity");
		}
		Beans.copy(u, pjUserForm).execute();
		return "edit.jsp";
	}
	
	@Execute(input="edit.jsp")
	public String doEdit() {
		PjUser u = pjUserService.findById(pjUserForm.id);
		if (u == null) {
			throw new ActionMessagesException("errors.nosuchentity");
		}
		Beans.copy(pjUserForm, u).execute();
		pjUserService.update(u);
		addFlushMsg("notice.updated");
		return "index?redirect=true";
	}
}
