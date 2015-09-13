package org.lailaiwcl.esbac.impl;

import org.lailaiwcl.esbac.event.BaseEvent;
import org.lailaiwcl.esbac.spi.InputAdapter;

public class DefaultInputAdapter implements InputAdapter{

	@Override
	public BaseEvent input(Object obj) {
		return (BaseEvent)obj;
	}

}
