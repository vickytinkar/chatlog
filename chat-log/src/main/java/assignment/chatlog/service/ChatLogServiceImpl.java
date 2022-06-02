package assignment.chatlog.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import assignment.chatlog.model.Chat;
import assignment.chatlog.reopsitory.ChatRepository;
import exception.MessageNotFoundException;

@Service
public class ChatLogServiceImpl implements ChatLogService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public String saveLog(String User, Chat chat) {
		chat.setUser(User);
		Chat c = chatRepository.save(chat);
		return c.getChatId().toString();
	}

	@Override
	public void deleteChat(String user) {

		chatRepository.deleteByUserId(user);

	}

	@Override
	public void deleteMessage(String messageId) {
		chatRepository.deleteById(UUID.fromString(messageId));
	}

	@Override
	public List<Chat> getUserChat(String user, Optional<Integer> limit, Optional<String> start) throws MessageNotFoundException {
		int top = 10;
		if (limit.isPresent())
			top = limit.get();
		
		List<Chat> chatLog;
		if (start.isPresent()) {
			String uuid = start.get();
			Optional<Chat> c = chatRepository.findByChatIdAndUserId(UUID.fromString(uuid), user);
			if (c.isPresent()) {

				chatLog = chatRepository.findTop10ByUserIdAndTimestampGreaterThanEqualOrderByTimestampDesc(user, c.get().getTimestamp());
				if(top==10)
				{
					return chatLog;
				}
				else
				{
					return chatLog.stream().limit(top).collect(Collectors.toList());
				}

			}
			else
			{
				throw new MessageNotFoundException("MessageId :"+start.get()+" is not found");
			}

		}
		else
		{
							
			chatLog = chatRepository.findTop10ByUserIdOrderByTimestampDesc(user);
			if(top!=10)
				chatLog = chatLog.stream().limit(top).collect(Collectors.toList());
			
			
		}

		return chatLog;
	}

}
