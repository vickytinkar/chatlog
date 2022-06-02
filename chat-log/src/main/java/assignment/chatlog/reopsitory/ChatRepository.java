package assignment.chatlog.reopsitory;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assignment.chatlog.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
	
  public List<Chat> findByUserId(String UserId);
  
  public void deleteByUserId(String User);
  
  public Optional<Chat> findByChatIdAndUserId(UUID chatId, String userId);
  
  public List<Chat> findTop10ByUserIdAndTimestampGreaterThanEqualOrderByTimestampDesc(String userId , Timestamp timestamp);
  
  public List<Chat> findTop10ByUserIdOrderByTimestampDesc(String userId);

}
