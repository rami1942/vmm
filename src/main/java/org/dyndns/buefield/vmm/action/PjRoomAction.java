package org.dyndns.buefield.vmm.action;

import java.util.List;

import javax.annotation.Resource;

import org.dyndns.bluefield.craysas.action.BaseAction;
import org.dyndns.bluefield.craysas.html.config.FormConfig;
import org.dyndns.bluefield.craysas.html.config.ListTableConfig;
import org.dyndns.buefield.vmm.entity.PjRoom;
import org.dyndns.buefield.vmm.entity.VirtualMachine;
import org.dyndns.buefield.vmm.form.PjRoomForm;
import org.dyndns.buefield.vmm.service.PjRoomService;
import org.dyndns.buefield.vmm.service.VirtualMachineService;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

/**
 * PJルーム情報
 *
 */
public class PjRoomAction extends BaseAction {

	@Resource protected PjRoomService pjRoomService;
	@Resource protected VirtualMachineService virtualMachineService;
	
	@ActionForm
	@Resource protected PjRoomForm pjRoomForm;
	
	public ListTableConfig<PjRoom> indexConfig;
	public ListTableConfig<VirtualMachine> vmListConfig;
	
	@Execute(validator=false)
	public String index() {
		List<PjRoom> list = pjRoomService.findAll();
		indexConfig = new ListTableConfig<PjRoom>(PjRoom.KEY_PREFIX, PjRoom.INDEX_CONFIG, list);
		return "index.jsp";
	}
	
	@Execute(validator=false)
	public String show() {
		PjRoom pjr = pjRoomService.findById(pjRoomForm.id);
		if (pjr == null) {
			throw new ActionMessagesException("errors.invalid.parameter");
		}
		Beans.copy(pjr, pjRoomForm).execute();

		List<VirtualMachine> list = virtualMachineService.findByRoomNo(pjr.pjNo);
		vmListConfig = new ListTableConfig<VirtualMachine>(VirtualMachine.KEY_PREFIX, VirtualMachineAction.ITEMS, list);
		return "show.jsp";
	}

	public FormConfig<PjRoomForm> getCreateConfig() {
		return new FormConfig<PjRoomForm>(pjRoomForm, PjRoom.KEY_PREFIX, "doCreate", "追加", PjRoom.CREATE_CONFIG);
	}
	
	@Execute(validator=false)
	public String create() {
		return "create.jsp";
	}
	
	@Execute(input="create.jsp")
	public String doCreate() {
		PjRoom pjr = Beans.createAndCopy(PjRoom.class, pjRoomForm).timestampConverter("yyyy/MM/dd", "assignDt").execute();
		pjRoomService.insert(pjr);
		addFlushMsg("notice.crated");
		return "index?redirect=true";
	}

	public FormConfig<PjRoomForm> getEditConfig() {
		return new FormConfig<PjRoomForm>(pjRoomForm, PjRoom.KEY_PREFIX, "doEdit", "更新", PjRoom.EDIT_CONFIG);
	}
	
	
	@Execute(validator=false)
	public String edit() {
		PjRoom pjr = pjRoomService.findById(pjRoomForm.id);
		if (pjr == null) {
			throw new ActionMessagesException("errors.invalid.parameter");
		}
		Beans.copy(pjr, pjRoomForm).execute();
		return "edit.jsp";
	}
	
	@Execute(input="edit.jsp")
	public String doEdit() {
		PjRoom pjr = Beans.createAndCopy(PjRoom.class, pjRoomForm).timestampConverter("yyyy/MM/dd", "assignDt").execute();
		pjRoomService.update(pjr);
		addFlushMsg("notice.pjroom.updated");
		return "index?redirect=true";
	}
}
