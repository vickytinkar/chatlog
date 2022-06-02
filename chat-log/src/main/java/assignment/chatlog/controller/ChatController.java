package assignment.chatlog.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assignment.chatlog.model.Chat;
import assignment.chatlog.service.ChatLogService;
import exception.MessageNotFoundException;

@RestController
@RequestMapping("/chatlogs")
@Validated
public class ChatController {

	@Autowired
	private ChatLogService chatLogService;

	@PostMapping("/{user}")
	public ResponseEntity<String> saveLogs(
			@PathVariable @Pattern(regexp = "^[A-Za-z0-9]+$") @Size(max = 16) String user, @RequestBody Chat chat) {
		String messgaeId = chatLogService.saveLog(user, chat);
		return new ResponseEntity<String>(messgaeId, HttpStatus.CREATED);

	}

	@GetMapping("/{user}")
	public ResponseEntity<List<Chat>> getLogs(
			@PathVariable @Pattern(regexp = "^[A-Za-z0-9]+$", message = "The user must  alphanumeric string of less than 16 characters ") @Size(max = 16, message = "The user must  alphanumeric string of less than 16 characters") String user,
			@RequestParam Optional<Integer> limit, @RequestParam  Optional<String> start) throws MessageNotFoundException {
		List<Chat> chatLogs = chatLogService.getUserChat(user,limit,start);
		if(chatLogs.isEmpty())
		return new ResponseEntity<List<Chat>>(chatLogs, HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Chat>>(chatLogs, HttpStatus.OK);

	}

	@DeleteMapping("/{user}")
	public ResponseEntity<String> deleteByUser(
			@PathVariable @Pattern(regexp = "^[A-Za-z0-9]+$", message = "The user must  alphanumeric string of less than 16 characters ") @Size(max = 16, message = "The user must  alphanumeric string of less than 16 characters") String user) {
		chatLogService.deleteChat(user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@DeleteMapping("/{user}/{messageId}")
	public ResponseEntity<String> deleteByMessageId(
			@PathVariable @Pattern(regexp = "^[A-Za-z0-9]+$", message = "The user must  alphanumeric string of less than 16 characters ") @Size(max = 16, message = "The user must  alphanumeric string of less than 16 characters") String user,
			@PathVariable @NotBlank @Size(min = 16, max = 16, message = "messageId is not valid") String messageId) {
		chatLogService.deleteChat(messageId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
