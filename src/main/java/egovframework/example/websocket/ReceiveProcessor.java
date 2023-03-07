package egovframework.example.websocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

@Service
public class ReceiveProcessor {
	
	private final ExecutorService executor;
	private final ReceiveQueue receiveQueue;
	
	public ReceiveProcessor(ReceiveQueue receiveQueue) {
		this.executor = Executors.newFixedThreadPool(10);
		this.receiveQueue = receiveQueue;
		start();
	}

	public void start() {
		Thread thread = new Thread(new Processor(executor, receiveQueue));
		thread.start();
	}

}

class Processor implements Runnable {

	private final ExecutorService executor;
	private final ReceiveQueue receiveQueue;
	
	public Processor(ExecutorService executor, ReceiveQueue receiveQueue) {
		this.executor = executor;
		this.receiveQueue = receiveQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ReceiveMessage message = receiveQueue.take();
				ReceiveRunnable receiveRunnable = new ReceiveRunnable(message);
				executor.execute(receiveRunnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

