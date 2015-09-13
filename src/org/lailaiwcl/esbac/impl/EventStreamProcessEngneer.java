package org.lailaiwcl.esbac.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lailaiwcl.esbac.spi.Initialization;
import org.lailaiwcl.esbac.spi.UpdateListenerWithStatus;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

/**
 * 事件流执行引擎，对Esper的事件流执行引擎的封装。
 * 
 * @author wucl(lailaiwcl@gmail.com)
 * 
 */
public class EventStreamProcessEngneer {
	private EPServiceProvider epService = null;
	private EPAdministrator admin = null;
	private EPRuntime runtime = null;
	/**
	 * 用户配置类。
	 */
	private Configuration configuration = null;
	/**
	 * 初始化类。
	 */
	private List<Initialization> listInitialization = new ArrayList<Initialization>();
	/**
	 * Context是否执行过初始化方法标记位。
	 */
	private boolean isInit = false;

	private Map<UpdateListenerWithStatus, String> mapListenerAndEpl = new HashMap<UpdateListenerWithStatus, String>();

	private Map<UpdateListenerWithStatus, Boolean> resultData = new HashMap<UpdateListenerWithStatus, Boolean>();;

	private Map<UpdateListenerWithStatus, Boolean> defaultResult = new HashMap<UpdateListenerWithStatus, Boolean>();

	public EventStreamProcessEngneer() {
		epService = EPServiceProviderManager.getDefaultProvider();
		admin = epService.getEPAdministrator();
		runtime = epService.getEPRuntime();
	}

	public EventStreamProcessEngneer(Configuration configuration) {
		if (configuration != null) {
			this.configuration = configuration;
			epService = EPServiceProviderManager
					.getDefaultProvider(configuration);
		} else {
			epService = EPServiceProviderManager.getDefaultProvider();
		}
		admin = epService.getEPAdministrator();
		runtime = epService.getEPRuntime();
	}

	/**
	 * 事件流引擎初始化方法。
	 */
	public void init() {
		if (listInitialization != null || listInitialization.size() > 0) {
			for(Initialization Initialization : listInitialization){
				Initialization.init(this);
			}
		}
		for (UpdateListenerWithStatus listner : mapListenerAndEpl.keySet()) {
			defaultResult.put(listner, listner.isStatus());
			EPStatement statement = epService.getEPAdministrator().createEPL(
					mapListenerAndEpl.get(listner));
			statement.addListener(listner);
		}
		isInit = true;
	}

	/**
	 * 向引擎发送事件
	 * 
	 * @param theEvent
	 */
	public void sendEvent(Object theEvent) {
		if (!isInit) {
			init();
		}
		if (resultData != null) {
			resultData.clear();
		}
		resultData.putAll(defaultResult);
		runtime.sendEvent(theEvent);

	}

	public EPServiceProvider getEpService() {
		return epService;
	}

	public void setEpService(EPServiceProvider epService) {
		this.epService = epService;
	}

	public EPAdministrator getAdmin() {
		return admin;
	}

	public void setAdmin(EPAdministrator admin) {
		this.admin = admin;
	}

	public EPRuntime getRuntime() {
		return runtime;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	public Map<UpdateListenerWithStatus, Boolean> getResultData() {
		return resultData;
	}

	public void setResultData(Map<UpdateListenerWithStatus, Boolean> resultData) {
		this.resultData = resultData;
	}

	public Map<UpdateListenerWithStatus, String> getMapListenerAndEpl() {
		return mapListenerAndEpl;
	}
	
	/**
	 * 注册EPL语句和监听
	 */
	public void addEPLAndListener(UpdateListenerWithStatus listener, String epl) {
		mapListenerAndEpl.put(listener, epl);
	}

	public List<Initialization> getListInitialization() {
		return listInitialization;
	}

	public void addInitialization(Initialization initialization) {
		listInitialization.add(initialization);
	}
	
	

}
