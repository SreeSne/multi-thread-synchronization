//Vinitha Gadiraju 
//CIS 212 Assignment 7

import java.util.concurrent.*;
public class Main {
	private static final int BUFFER_SIZE = 100000;
	// set variable to false to perform without extra credit
	private static final boolean isExtraCredit = true;
	public static void main(String[] args) throws InterruptedException {
		SynchronizedBuffer sync_buffer = new SynchronizedBuffer(BUFFER_SIZE);
		SharedQueue shared_buffer = new SharedQueue(BUFFER_SIZE);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		if (isExtraCredit) {
			executorService.execute(new Producer(sync_buffer));
			executorService.execute(new Consumer(sync_buffer, 1));
			executorService.execute(new Consumer(sync_buffer, 2));
		}
		else
		{
			executorService.execute(new Producer(shared_buffer));
			executorService.execute(new Consumer(shared_buffer, 1));
			executorService.execute(new Consumer(shared_buffer, 2));
		}
		
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
	}
}