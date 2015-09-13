package org.lailaiwcl.esbac.impl;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 默认监听器，什么也不做.
 * 
 * @author wucl(lailaiwcl@gmail.com)
 * 
 */
public class DefalutUpdateListener implements UpdateListener {

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
	}

}
