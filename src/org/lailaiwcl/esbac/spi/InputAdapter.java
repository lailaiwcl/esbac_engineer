package org.lailaiwcl.esbac.spi;

import org.lailaiwcl.esbac.event.BaseEvent;
/**
 * 输入适配器接口。
 * @author wucl(lailaiwcl@gmail.com)
 *
 */
public interface InputAdapter {
	public BaseEvent input(Object obj);

}
