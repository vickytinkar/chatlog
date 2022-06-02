package assignment.chatlog.service;

import java.util.List;
import java.util.Optional;

import assignment.chatlog.model.Chat;
import exception.MessageNotFoundException;

public interface ChatLogService {
	
	public String saveLog(String user,Chat chat);
	
	public List<Chat> getUserChat(String user, Optional<Integer> limit, Optional<String> start) throws MessageNotFoundException;
	
	public void deleteChat(String user);
	
	public void deleteMessage(String messageId);

}
