package pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Temp extends Thread{
	@Override
	public void run() {
		System.out.println("run");
	}
}

public class ScheduledJob {
	public static void main(String[] args) {
		Temp command = new Temp();
		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> scheduledTask = scheduled.scheduleWithFixedDelay(command, 5, 1, TimeUnit.SECONDS);
	}
}
