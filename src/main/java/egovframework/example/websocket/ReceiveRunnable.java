package egovframework.example.websocket;

public class ReceiveRunnable implements Runnable {
	
	private final ReceiveMessage receiveMessage;
	
	public ReceiveRunnable(ReceiveMessage receiveMessage) {
		this.receiveMessage = receiveMessage;
	}

	@Override
	public void run() {
		System.out.println(receiveMessage.getMessage());
		Thread.interrupted();
	}

}
