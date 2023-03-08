package egovframework.example.sample.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.sample.service.SocketService;

@RestController
@RequestMapping(SocketController.SOCKET_CONTROLLER_PREFIX)
public class SocketController {
	public final static String SOCKET_CONTROLLER_PREFIX = "/socket";
	
	private final SocketService socketService;
	
	public SocketController(SocketService socketService) {
		this.socketService = socketService;
	}

	@PostMapping("/chart")
	public ResponseEntity<?> add(@RequestBody Map<String, Object> body) {
		if(socketService.add(body)) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
