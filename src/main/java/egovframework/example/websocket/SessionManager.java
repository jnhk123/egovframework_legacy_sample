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
		// session 값들을 담을 List 생성
		List<WebSocketSession> webSocketSessions = new ArrayList<WebSocketSession>();
		Iterator<String> keys = sessionMap.keySet().iterator();
		// keySet() 메소드 : Map 객체의 key 데이터들을 ( Set 자료형 ) 가져옴 --> sessionId 
		while(keys.hasNext()) {
			String key = keys.next();
			System.out.println(key);
			Session s = sessionMap.get(key);
			// value (session값 추출)
			webSocketSessions.add(s.getSession()); 
		}
		return webSocketSessions;
	}
	
}
