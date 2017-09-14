package com.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzUtil {
	public static Scheduler getDefaultScheduler() 
	{
		Scheduler schedule = null;
		try {
			schedule = StdSchedulerFactory.getDefaultScheduler();
			schedule.start();
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		return schedule;
	}
}
