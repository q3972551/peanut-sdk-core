package com.quartz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;


public class ServerTaskQuarzIModule{
	
	private static ServerTaskQuarzIModule instance = null;
	private Map<String, CronTrigger> triggerMap = new HashMap<String, CronTrigger>();
	private Scheduler sched = null;
	
	public static ServerTaskQuarzIModule getInstance(){
		if (instance == null)
		{
			instance = new ServerTaskQuarzIModule();
		}
		return instance;
	}
	
	private ServerTaskQuarzIModule(){
		sched = QuartzUtil.getDefaultScheduler();
	}
	
	public void startJob(String time,Class<? extends Job> job){
		this.createJob(job.getClass().getName(), time, job);
	}
	
	//建立定时任务
	public void createJob(String jobName, String cronExpression, Class<? extends Job> classs){
		
		JobDetail detail = createJobDetail(jobName, Scheduler.DEFAULT_GROUP, classs);
		
		try {
			if (sched.getJobDetail(detail.getKey()) != null) {
				sched.deleteJob(detail.getKey());
			}
			CronTrigger trigger = createTrigger(jobName + "trigger", "gametimer_trigger_name", cronExpression);
			triggerMap.put(jobName, trigger);
			sched.scheduleJob(detail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JobDetail createJobDetail(String jobName, String jobGroup, Class<? extends Job> classs) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		JobDetail job = JobBuilder.newJob(classs).withIdentity(jobKey).build();
		return job;
	}
	
	public CronTrigger createTrigger(String triggerName, String triggerGroup, String cronExpression) {
		boolean flag = CronExpression.isValidExpression(cronExpression);
		if (!flag) {
			return null;
		}
		TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		return trigger;
	}

	public void destroy() {
		try {
			if (sched != null)
			{
				sched.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
