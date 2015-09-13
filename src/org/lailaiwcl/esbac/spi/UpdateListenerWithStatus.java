package org.lailaiwcl.esbac.spi;

import org.lailaiwcl.esbac.impl.EventStreamProcessEngneer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 监听器组件实现类。
 * 
 * @author wucl(lailaiwcl@gmail.com)
 * 
 */
public abstract class UpdateListenerWithStatus implements UpdateListener {

	/**
	 * 当前监听器的权限结果
	 */
	private boolean status;
	/**
	 * 上下文
	 */
	private EventStreamProcessEngneer context = null;

	public UpdateListenerWithStatus(EventStreamProcessEngneer context) {
		super();
		this.context = context;
	}

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param status
	 *            如果监听没有接收到通知事件，默认权限输出结果.
	 */
	public UpdateListenerWithStatus(EventStreamProcessEngneer context,
			boolean status) {
		this(context);
		this.status = status;
	}

	@Override
	public final void update(EventBean[] newEvents, EventBean[] oldEvents) {
		status = hasPermission(newEvents, oldEvents);
		this.context.getResultData().put(this, status);
	}

	public abstract boolean hasPermission(EventBean[] newEvents,
			EventBean[] oldEvents);

	public boolean isStatus() {
		return status;
	}

	public final EventStreamProcessEngneer getContext() {
		return context;
	}

	public final void setContext(EventStreamProcessEngneer context) {
		this.context = context;
	}

}
