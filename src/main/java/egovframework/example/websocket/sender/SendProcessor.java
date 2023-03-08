package egovframework.example.websocket.sender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendProcessor implements Runnable{

	private final ExecutorService executor;
	private SendQueue sendQueue;
	private boolean excute = true;
	private final int sleepIntervalInMilliseconds;
	
	public SendProcessor(int threadNumber, int sleepIntervalInMilliseconds, SendQueue sendQueue) {
		this.executor = Executors.newFixedThreadPool(threadNumber);
		this.sleepIntervalInMilliseconds = sleepIntervalInMilliseconds;
		this.sendQueue = sendQueue;
	}
	
	@PreDestroy
	private void end() {
		excute = false;
		executor.shutdown();
	}

	@Override
	public void run() {
		log.info("===> send thread start <===");
		while (excute) {
			try {
				SendMessage sm = sendQueue.poll(sleepIntervalInMilliseconds, TimeUnit.MILLISECONDS);
				if(sm != null) {
					executor.execute(new SendRunnable(sm));
				}
			} catch (InterruptedException e) {
				log.error("{}", e.getMessage(), e);
			}
		}
		log.info("===> send thread end <===");
	}
	
	
}
