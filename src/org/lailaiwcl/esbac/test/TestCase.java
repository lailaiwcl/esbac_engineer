package org.lailaiwcl.esbac.test;

import org.lailaiwcl.esbac.api.ApplicationContext;
import org.lailaiwcl.esbac.event.Request;
import org.lailaiwcl.esbac.impl.DefaultInitialization;
import org.lailaiwcl.esbac.impl.DefaultInputAdapter;
import org.lailaiwcl.esbac.impl.DefaultOutputResultProcess;
import org.lailaiwcl.esbac.spi.Initialization;
import org.lailaiwcl.esbac.spi.InputAdapter;
import org.lailaiwcl.esbac.spi.OutputResultProcess;
import org.lailaiwcl.esbac.spi.UpdateListenerWithStatus;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EventBean;

public class TestCase {
	public static void main(String[] args) throws InterruptedException {
		Configuration configuration = new Configuration();
		configuration.configure("esper.cfg.xml");
		ApplicationContext context = new ApplicationContext(configuration);
		Initialization initialization = new DefaultInitialization();
		context.addInitialization(initialization);
		InputAdapter inputAdapter = new DefaultInputAdapter();
		OutputResultProcess output = new DefaultOutputResultProcess();
		context.setInputAdapter(inputAdapter);
		context.setOutput(output);
		String epl1 = "select * from UserWindow as u,RoleWindow as r,ResourceWindow as re,User_RoleWindow as ur,Role_ResourceWindow as rr,Session.win:length(1) as s where u.uid = s.userid and u.uid = ur.userid and ur.userid = r.id and ur.roleid = rr.roleid and rr.resourceid = re.id and re.url=s.url";
		UpdateListenerWithStatus listener1 = new UpdateListenerWithStatus(
				context.getEngneer(), false) {
			@Override
			public boolean hasPermission(EventBean[] newEvents,
					EventBean[] oldEvents) {
				return true;
			}

			@Override
			public String toString() {
				return "static";
			}

		};
		context.addEPLAndListener(epl1, listener1);

		String epl2 = "select count(*) as c from Session.win:time(1 sec) group by userid";
		UpdateListenerWithStatus listener2 = new UpdateListenerWithStatus(
				context.getEngneer(), false) {
			@Override
			public boolean hasPermission(EventBean[] newEvents,
					EventBean[] oldEvents) {
				if (Integer.valueOf(newEvents[0].get("c").toString()) > 3) {
					return false;
				}
				return true;
			}

			@Override
			public String toString() {
				return "dynamic";
			}

		};
		context.addEPLAndListener(epl2, listener2);
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article")));
		System.out.println(context.getEngneer().getResultData());
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article1")));
		System.out.println(context.getEngneer().getResultData());
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article")));
		System.out.println(context.getEngneer().getResultData());
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article")));
		System.out.println(context.getEngneer().getResultData());
		Thread.sleep(1100);
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article")));
		System.out.println(context.getEngneer().getResultData());
		System.out.println(context.sendData(new Request(1,
				"/private/cms/article")));
		System.out.println(context.getEngneer().getResultData());
	}

}
