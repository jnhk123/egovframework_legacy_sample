package egovframework.example.websocket;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionManager {

	Map<String, Session> sessionMap = new LinkedHashMap<String, Session>();
	
	public void put(String key,  Session session) {
		sessionMap.put(key, session);
	}
	
	public Session get(String key) {
		return sessionMap.get(key);
	}
	
	public void remove(String key) {
		sessionMap.remove(key);
	}
	
}
