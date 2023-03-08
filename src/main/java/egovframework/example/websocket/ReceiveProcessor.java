package egovframework.example.websocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReceiveProcessor {

	private final ExecutorService executor;
	private final ReceiveQueue receiveQueue;

	public ReceiveProcessor(ReceiveQueue receiveQueue, int threadNumber) {
		this.executor = Executors.newFixedThreadPool(threadNumber);
		this.receiveQueue = receiveQueue;
	}

	public void start() {
		System.out.println("===> thread start");
		new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					executor.execute(new ReceiveRunnable(receiveQueue.take()));
				} catch (InterruptedException e) {
					Thread.interrupted();
					e.printStackTrace();
				}

			}
		}).start();
	}

}
