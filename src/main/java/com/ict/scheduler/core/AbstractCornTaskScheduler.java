package com.ict.scheduler.core;

public abstract class AbstractCornTaskScheduler implements CornTaskScheduler {

	@Override
	public String getExpression() {
		return "*/10 * * * * ?";
	}

	@Override
	public abstract void processesTask();

}
