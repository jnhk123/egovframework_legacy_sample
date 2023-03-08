package egovframework.example.websocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class SessionManager {

	private Map<String, Session> sessionMap = new LinkedHashMap<String, Session>();
	
	public void put(String key,  Session session) {
		sessionMap.put(key, session);
	}
	
	public Session get(String key) {
		return sessionMap.get(key);
	}
	
	public void remove(String key) {
		sessionMap.remove(key);
	}
	
	public List<WebSocketSession> getAllWebSocketSession() {
		List<WebSocketSession> webSocketSessions = new ArrayList<WebSocketSession>();
		Iterator<String> keys = sessionMap.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			Session s = sessionMap.get(key);
			webSocketSessions.add(s.getSession()); 
		}
		return webSocketSessions;
	}
	
}
