package core.job.executor;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import core.job.Job;

@Component
public class AsyncJobExecutor {

	private static final Logger RUNTIME_LOGGER = Logger
			.getLogger("runtimeLogger");
	private static final Logger LOGGER = Logger.getLogger("mainLogger");

	@Async
	public void execute(Job job) {
		try {
			job.execute();
		} catch (Exception e) {
			LOGGER.error("Runtime Error in Async");
			RUNTIME_LOGGER.error("Runtime Error", e);
		}
	}
}
