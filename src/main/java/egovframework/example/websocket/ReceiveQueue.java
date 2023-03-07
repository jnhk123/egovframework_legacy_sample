package egovframework.example.websocket;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

@Component
public class ReceiveQueue {
	
	private final BlockingQueue<ReceiveMessage> queue;
	
	public ReceiveQueue() {
		queue = new LinkedBlockingQueue<ReceiveMessage>();
	}

	public void add(ReceiveMessage message) {
		queue.add(message);
	}
	
	public void addAll(Collection<ReceiveMessage> messages) {
		queue.addAll(messages);
	}
	
	public ReceiveMessage poll() {
		return queue.poll();
	}
	
	public ReceiveMessage take() throws InterruptedException {
		return queue.take();
	}
	
	public int getSize() {
		return queue.size();
	}

}
