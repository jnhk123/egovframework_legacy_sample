package egovframework.example.websocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import egovframework.example.websocket.ReceiveMessage;
import egovframework.example.websocket.ReceiveQueue;
import egovframework.example.websocket.Session;
import egovframework.example.websocket.SessionManager;

public class SocketTextHandler extends TextWebSocketHandler {

	private final ReceiveQueue receiveQueue;
	private final SessionManager sessionManager;

	public SocketTextHandler(ReceiveQueue receiveQueue, SessionManager sessionManager) {
		this.receiveQueue = receiveQueue;
		this.sessionManager = sessionManager;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Session sessionInstance = new Session();
		sessionInstance.setKey(session.getId());
		sessionInstance.setSession(session);
		sessionManager.put(session.getId(), sessionInstance);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ReceiveMessage receiveMessage = new ReceiveMessage();
		receiveMessage.setMessage(message.getPayload());
		receiveQueue.add(receiveMessage);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionManager.remove(session.getId());
	}

}
