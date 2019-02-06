package com.ict.scheduler;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.ict.scheduler.core.CornTaskScheduler;
import com.ict.scheduler.core.TaskRunner;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
	
	@Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(2);
    }

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		Reflections reflections = new Reflections("com.ict.scheduler");
		
		Set<Class<? extends CornTaskScheduler>> subTypes = reflections.getSubTypesOf(CornTaskScheduler.class);
		for(Class<? extends CornTaskScheduler> taskClass : subTypes) {
			try {
				if(!StringUtils.endsWith(taskClass.getName(), "AbstractCornTaskScheduler")){
					CornTaskScheduler taks = taskClass.newInstance();
					System.out.println("Value :: " + taks.getExpression());
					TaskRunner runner = new TaskRunner(taks);
					CronTask cronTask  = new CronTask(runner, taks.getExpression());
					taskRegistrar.addCronTask(cronTask);
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
		taskRegistrar.setScheduler(taskExecutor());
	}
}