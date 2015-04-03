package by.dev.madhead.playgrounds.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BrokenStatefulJob implements Job {
	private int counter = 0;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("BrokenStatefulJob counter: " + counter++);
	}
}
