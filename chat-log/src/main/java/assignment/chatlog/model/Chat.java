package assignment.chatlog.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chat{
	
	@Id
    @GeneratedValue
    private UUID chatId;
	
	private String message;
	
	private Timestamp timestamp;
	
	private Boolean isSent;
	
	
	private String userId;

	public Chat(UUID chatId, String message, Timestamp timestamp, Boolean isSent, String user) {
		super();
		this.chatId = chatId;
		this.message = message;
		this.timestamp = timestamp;
		this.isSent = isSent;
		this.userId = user;
	}

	public Chat() {
	
	}

	/**
	 * @return the chatId
	 */
	public UUID getChatId() {
		return chatId;
	}

	/**
	 * @param chatId the chatId to set
	 */
	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the isSent
	 */
	public Boolean getIsSent() {
		return isSent;
	}

	/**
	 * @param isSent the isSent to set
	 */
	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}
	
	/**
	 * @return the user
	 */
	@JsonIgnore
	public String getUser() {
		return userId;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.userId = user;
	}

	
	

}
