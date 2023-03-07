package egovframework.example.websocket.handler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import egovframework.example.websocket.ReceiveMessage;
import egovframework.example.websocket.ReceiveQueue;

public class SocketTextHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ReceiveQueue receiveQueue;

    public SocketTextHandler(ReceiveQueue receiveQueue) {
    	this.receiveQueue = receiveQueue;
	}

	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	ReceiveMessage receiveMessage = new ReceiveMessage();
    	receiveMessage.setMessage(message.getPayload());
    	receiveQueue.add(receiveMessage); 
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

}
