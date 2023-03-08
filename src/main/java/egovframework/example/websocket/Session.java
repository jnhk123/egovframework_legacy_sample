package egovframework.example.websocket;

import org.springframework.web.socket.WebSocketSession;

public class Session {
	
	private WebSocketSession session;
	private String key;
	
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
