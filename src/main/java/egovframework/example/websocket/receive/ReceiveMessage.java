package egovframework.example.websocket.receive;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiveMessage {
	private String message;
	private WebSocketSession session;
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMessageInMap() {
		return new Gson().fromJson(message, Map.class);
	}
	
	public int getCode() {
		return MapUtils.getIntValue(getMessageInMap(), "code");
	}
	
}
