package egovframework.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import egovframework.example.websocket.ReceiveProcessor;
import egovframework.example.websocket.ReceiveQueue;
import egovframework.example.websocket.SessionManager;
import egovframework.example.websocket.handler.SocketTextHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private static final int THREAD_NUMBER = 10;
	
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
    
    @Bean 
    public ReceiveProcessor receiveProcessor() {
    	System.out.println("==> ?????");
    	ReceiveProcessor processor = new ReceiveProcessor(receiveQueue(), THREAD_NUMBER);
    	processor.start();
    	return processor;
    }
    
    @Bean
    public ReceiveQueue receiveQueue() {
    	return new ReceiveQueue();
    }
    
    @Bean
    public SessionManager sessionManager() {
    	return new SessionManager();
    }
    

    
}
