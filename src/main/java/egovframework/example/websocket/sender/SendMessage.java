package egovframework.example.websocket.sender;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessage {
	private String message;
	private WebSocketSession sendTarget;
	
	public void send() throws IOException {
		sendTarget.sendMessage(new TextMessage(message));
	}
}
