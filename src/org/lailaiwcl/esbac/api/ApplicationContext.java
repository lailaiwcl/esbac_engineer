package org.lailaiwcl.esbac.api;

import org.lailaiwcl.esbac.event.BaseEvent;
import org.lailaiwcl.esbac.impl.EventStreamProcessEngneer;
import org.lailaiwcl.esbac.spi.Initialization;
import org.lailaiwcl.esbac.spi.InputAdapter;
import org.lailaiwcl.esbac.spi.OutputResultProcess;
import org.lailaiwcl.esbac.spi.UpdateListenerWithStatus;

import com.espertech.esper.client.Configuration;

public class ApplicationContext {
	private InputAdapter inputAdapter;
	private EventStreamProcessEngneer engneer;
	private OutputResultProcess output;
	
	public ApplicationContext() {
		engneer = new EventStreamProcessEngneer();
	}

	public ApplicationContext(Configuration configuration) {
		engneer = new EventStreamProcessEngneer(configuration);
	}

	public ApplicationContext(InputAdapter inputAdapter,
			OutputResultProcess output, Configuration configuration) {
		this(configuration);
		this.inputAdapter = inputAdapter;
		this.output = output;
	}

	/**
	 * 发送数据。
	 * 
	 * @param obj
	 *            待处理的数据对象
	 * @return
	 */
	public boolean sendData(Object obj) {
		BaseEvent theEvent = inputAdapter.input(obj);
		engneer.sendEvent(theEvent);
		return output.process(engneer.getResultData());
	}

	/**
	 * 添加事件处理引擎初始化类。
	 * 
	 * @param initialization
	 */
	public void addInitialization(Initialization initialization) {
		if (initialization != null) {
			engneer.addInitialization(initialization);
		}
	}

	/**
	 * 像事件流处理引擎添加epl和监听
	 * 
	 * @param epl
	 *            事件流处理语句
	 * 
	 * @param listener
	 *            监听
	 */
	public void addEPLAndListener(String epl, UpdateListenerWithStatus listener) {
		if (listener != null && epl != null) {
			engneer.addEPLAndListener(listener, epl);
		}
	}

	public InputAdapter getInputAdapter() {
		return inputAdapter;
	}

	public void setInputAdapter(InputAdapter inputAdapter) {
		this.inputAdapter = inputAdapter;
	}

	/**
	 * 获取事件流 处理引擎。
	 * 
	 * @return
	 */
	public EventStreamProcessEngneer getEngneer() {
		return engneer;
	}

	public OutputResultProcess getOutput() {
		return output;
	}

	public void setOutput(OutputResultProcess output) {
		this.output = output;
	}

}
