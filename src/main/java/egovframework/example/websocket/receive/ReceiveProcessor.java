package egovframework.example.websocket.receive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import egovframework.example.sample.service.SocketService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveProcessor implements Runnable {

	private final ExecutorService executor;
	private final ReceiveQueue receiveQueue;
	private final SocketService socketService;
	private final int sleepIntervalInMilliseconds;
	private boolean excute = true;

	public ReceiveProcessor(int threadNumber, int sleep, ReceiveQueue receiveQueue, SocketService socketService) {
		this.executor = Executors.newFixedThreadPool(threadNumber);
		this.sleepIntervalInMilliseconds = sleep;
		this.receiveQueue = receiveQueue;
		this.socketService = socketService;
	}

	@PreDestroy
	private void end() {
		excute = false;
		executor.shutdown();
	}

	@Override
	public void run() {
		log.info("===> receive thread start <===");
		while (excute) {
			try {
				ReceiveMessage rm = receiveQueue.poll(sleepIntervalInMilliseconds, TimeUnit.MILLISECONDS);
				if(rm != null) {
					executor.execute(new ReceiveRunnable(rm, socketService));
				}
			} catch (InterruptedException e) {
				log.error("{}", e.getMessage(), e);
			}
		}
		log.info("===> receive thread stop <===");
	}

}
