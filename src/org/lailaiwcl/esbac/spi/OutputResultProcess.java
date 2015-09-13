package org.lailaiwcl.esbac.spi;

import java.util.Map;


/**
 * 授权函数接口。
 * 
 * @author wucl(lailaiwcl@gmail.com)
 * 
 */
public interface OutputResultProcess {
	boolean process(Map<UpdateListenerWithStatus, Boolean> map);

}
