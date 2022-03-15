package com.line.api;

import java.util.List;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;

public interface MessageService {

	boolean pushMessage(Message message, String to);

	boolean pushImage(ImageMessage imgMessage, String to);

	List<String> getGroupUsers(String groupId);
}
