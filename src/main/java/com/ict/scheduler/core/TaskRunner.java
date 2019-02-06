package com.ict.scheduler.core;

public class TaskRunner implements Runnable {

	private final CornTaskScheduler taskScheduler;
	
	public TaskRunner(CornTaskScheduler taskScheduler) {
		this.taskScheduler = taskScheduler;
	}

	@Override
	public void run() {
		this.taskScheduler.processesTask();
	}

}
