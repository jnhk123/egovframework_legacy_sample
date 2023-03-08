package egovframework.example.sample.service;

import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public interface SocketService {
	boolean add(Map<String, Object> body);
	void sendAll();
	void sendAllToTarget(WebSocketSession session);
}
