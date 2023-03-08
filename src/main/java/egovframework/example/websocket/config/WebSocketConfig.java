package egovframework.example.websocket.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import egovframework.example.sample.service.SocketService;
import egovframework.example.websocket.SessionManager;
import egovframework.example.websocket.handler.SocketTextHandler;
import egovframework.example.websocket.receive.ReceiveProcessor;
import egovframework.example.websocket.receive.ReceiveQueue;
import egovframework.example.websocket.sender.SendProcessor;
import egovframework.example.websocket.sender.SendQueue;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private static final int THREAD_NUMBER = 10;
	private static final int SLEEP_INTERVAL_IN_MILLISECONDS = 100;
	private final ExecutorService executor;
	
	public WebSocketConfig() {
		executor = Executors.newFixedThreadPool(2);
	}
	
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketTextHandler(), "ws/socket")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
	
    @Bean
    public SocketTextHandler socketTextHandler() {
    	return new SocketTextHandler(receiveQueue(), sessionManager());
    }
    
    // 소켓의 세션을 관리하는 클래스
    @Bean("sessionManager")
    public SessionManager sessionManager() {
    	return new SessionManager();
    }

    // Receive 관련 설정
    @Bean("receiveQueue")
    public ReceiveQueue receiveQueue() {
    	return new ReceiveQueue();
    }
    
    @Bean
    public ReceiveProcessor receiveProcessor(SocketService socketService) {
    	ReceiveProcessor processor = new ReceiveProcessor(THREAD_NUMBER, SLEEP_INTERVAL_IN_MILLISECONDS, receiveQueue(), socketService);
    	executor.execute(processor);
    	return processor;
    }
    
    // Send 관련 설정
    @Bean("sendQueue")
    public SendQueue sendQueue() {
    	return new SendQueue();
    }
    
    @Bean
    public SendProcessor sendProcessor() {
    	SendProcessor processor = new SendProcessor(THREAD_NUMBER, SLEEP_INTERVAL_IN_MILLISECONDS, sendQueue());
    	executor.execute(processor);
    	return processor;
    }
    
    @PreDestroy
    private void end() {
    	executor.shutdown();
    }
    
    

    
}
