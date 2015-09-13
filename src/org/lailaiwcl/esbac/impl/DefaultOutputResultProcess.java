package org.lailaiwcl.esbac.impl;

import java.util.Map;

import org.lailaiwcl.esbac.spi.OutputResultProcess;
import org.lailaiwcl.esbac.spi.UpdateListenerWithStatus;

public class DefaultOutputResultProcess implements OutputResultProcess {

	@Override
	public boolean process(Map<UpdateListenerWithStatus, Boolean> map) {
		for (UpdateListenerWithStatus listener : map.keySet()) {
			boolean r = map.get(listener);
			if (r == false) {
				return false;
			}
		}
		return true;
	}

}
