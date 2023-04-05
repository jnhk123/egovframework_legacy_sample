package egovframework.example.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import egovframework.example.sample.ChartData;
import egovframework.example.sample.ChartDataRepository;
import egovframework.example.sample.service.SocketService;
import egovframework.example.websocket.SessionManager;
import egovframework.example.websocket.sender.SendMessage;
import egovframework.example.websocket.sender.SendQueue;

@Service
public class SocketServiceImpl extends EgovAbstractServiceImpl implements SocketService{
	
	private final ChartDataRepository chartDataRepository;
	private final SendQueue sendQueue;
	private final SessionManager sessionManager;

	public SocketServiceImpl(ChartDataRepository chartDataRepository
			, @Qualifier("sendQueue") SendQueue sendQueue
			, @Qualifier("sessionManager") SessionManager sessionManager
			) {
		this.chartDataRepository = chartDataRepository;
		this.sendQueue = sendQueue;
		this.sessionManager = sessionManager;
	}

	@Override
	public boolean add(Map<String, Object> body) {
		ChartData chartData = new ChartData();
		chartData.setValue(MapUtils.getLong(body, "value"));
		boolean result = chartDataRepository.add(chartData);
		
		if(result) {
			sendAll();
		}
		return result;
	}

	@Override
	public void sendAll() {
		//TODO Handler에서 remove되지 않은 Session을 가져올때 ??
	 	List<WebSocketSession> webSocketSessions = sessionManager.getAllWebSocketSession();
	 	
	 	// add 메소드에서 추가한 session_id , session
	 	String allDatas = chartDataRepository.getAllDatas();
	 	
	 	webSocketSessions.forEach(session -> sendQueue.add(
	 			SendMessage.builder()
	 				.message(allDatas)
	 				.sendTarget(session)
	 				.build())
	 			);
	}

	@Override
	public void sendAllToTarget(WebSocketSession session) {
		String allDatas = chartDataRepository.getAllDatas();
		sendQueue.add(
					SendMessage.builder()
						.message(allDatas)
						.sendTarget(session)
						.build()
				);
	}

	
	
	
	
}
