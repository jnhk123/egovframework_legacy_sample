package egovframework.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import egovframework.example.websocket.ReceiveQueue;
import egovframework.example.websocket.handler.SocketTextHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final ReceiveQueue receiveQueue;
	
    public WebSocketConfig(ReceiveQueue receiveQueue) {
		this.receiveQueue = receiveQueue;
	}

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketTextHandler(), "ws/socket")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
    
    @Bean
    public SocketTextHandler socketTextHandler() {
    	return new SocketTextHandler(receiveQueue);
    }
    

    
}
