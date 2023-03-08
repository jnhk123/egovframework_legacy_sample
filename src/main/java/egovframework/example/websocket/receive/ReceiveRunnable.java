package egovframework.example.websocket.receive;

import egovframework.example.sample.service.SocketService;

public class ReceiveRunnable implements Runnable {
	
	private final ReceiveMessage receiveMessage;
	private final SocketService socketService;
	private static int CODE_INIT = 1;
	
	public ReceiveRunnable(ReceiveMessage receiveMessage, SocketService socketService) {
		this.receiveMessage = receiveMessage;
		this.socketService = socketService;
	}

	@Override
	public void run() {
		if(CODE_INIT == receiveMessage.getCode()) {
			initialize();
		}
		Thread.interrupted();
	}
	
	public void initialize() {
		socketService.sendAllToTarget(receiveMessage.getSession());
	}

}
