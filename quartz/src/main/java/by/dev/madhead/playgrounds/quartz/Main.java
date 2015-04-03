package by.dev.madhead.playgrounds.quartz;

import by.dev.madhead.playgrounds.quartz.jobs.BrokenStatefulJob;
import by.dev.madhead.playgrounds.quartz.jobs.StatefulJob;
import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;

public class Main {
	public static void main(String[] args) throws SchedulerException {
		DirectSchedulerFactory.getInstance().createScheduler(
				new SimpleThreadPool(5, Thread.NORM_PRIORITY),
				new RAMJobStore()
		);

		final Scheduler scheduler = DirectSchedulerFactory.getInstance().getScheduler();

		scheduler.scheduleJob(JobBuilder.newJob(BrokenStatefulJob.class).build(), TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3))
				.startNow()
				.build());

		scheduler.scheduleJob(JobBuilder.newJob(StatefulJob.class)
						.usingJobData("counter", 0)
						.build(),
				TriggerBuilder.newTrigger()
						.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3))
						.startNow()
						.build());

		scheduler.start();
	}
}
