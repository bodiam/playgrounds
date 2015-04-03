package by.dev.madhead.playgrounds.quartz.jobs;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
public class StatefulJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		final int counter = context.getJobDetail().getJobDataMap().getInt("counter");

		System.out.println("StatefulJob counter: " + counter);

		context.getJobDetail().getJobDataMap().put("counter", counter + 1);
	}
}
