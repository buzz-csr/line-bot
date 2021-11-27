package com.line.api;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;

public interface MessageService {

	boolean pushMessage(TextMessage message, String to);

	boolean pushImage(ImageMessage imgMessage, String to);

}
