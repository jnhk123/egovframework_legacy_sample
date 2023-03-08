package egovframework.example.websocket.sender;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendRunnable implements Runnable {

	private final SendMessage sendMessage;
	
	public SendRunnable(SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}

	@Override
	public void run() {
		try {
			sendMessage.send();
		} catch (IOException e) {
			log.error("{}", e.getMessage(), e);
			Thread.interrupted();
		}
	}

}
