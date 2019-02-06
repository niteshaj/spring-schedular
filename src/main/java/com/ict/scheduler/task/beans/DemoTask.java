package com.ict.scheduler.task.beans;

import com.ict.scheduler.core.AbstractCornTaskScheduler;
import java.util.Date;

public class DemoTask extends AbstractCornTaskScheduler {

	@Override
	public void processesTask() {
		System.out.println("I am scheduled for every 10 sec...." + new Date());
	}
}
