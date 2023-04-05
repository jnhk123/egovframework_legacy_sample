package egovframework.example.websocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import egovframework.example.websocket.Session;
import egovframework.example.websocket.SessionManager;
import egovframework.example.websocket.receive.ReceiveMessage;
import egovframework.example.websocket.receive.ReceiveQueue;

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
		// ws/socket 으로 클라이언트가 요청하면 session 값을 
		sessionInstance.setKey(session.getId());
		sessionInstance.setSession(session);
		System.out.println("check.jsp 요청 성공 ---> session id : " + session.getId() +" session : " + session);
		sessionManager.put(session.getId(), sessionInstance);
		
//		sessionManager.put
//		public void put(String key,  Session session) {
//			sessionMap.put(key, session);
//		}
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// chart.jsp 에서 {"code" : 1 } 을 send
		System.out.println("check session id --- " + session.getId());
		//TODO receiveQueue 에 ReceiveMessage 의 message와 session을 add 하고 ReceiveMessage에도 set을 하는건가요..?
		receiveQueue.add(
				ReceiveMessage.builder()
					.message(message.getPayload())
					.session(session)
					.build()
				);
	}

	// LinkedBlockingQueue -> LinkedBlockingQueue는 크기에 제한이 없는 큐로, 요소를 계속 추가할 수 있습니다. 
	// 큐의 용량(capacity)을 초과하여 요소를 추가하려는 경우, 새로운 요소를 추가할 때까지 블로킹(blocking) 상태가 됩니다.
	
	// lombok @Builder -> 객체.builder().setField(값).build()
	// User 객체를 생성하는 데 필요한 모든 값을 설정하고, 인스턴스를 만들어서 반환해주는 빌더 메소드를 쉽게 구현할 수 있습니다.
	
	
	//TODO 언제 afterConnectionClosed가 일어날까요?
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionManager.remove(session.getId());
	}

}
