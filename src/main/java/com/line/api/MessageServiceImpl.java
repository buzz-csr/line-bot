package com.line.api;

import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

public class MessageServiceImpl implements MessageService {

	private static final String CHANNEL_TOKEN = "eVXK3prAepp2PqZ2VvN//E1xpXGhya8+ofCDsx6KplRb/5OAfHcCeBg+reXMnFk1OvEyWEcFpPaha0zMpfzPKF6CAUWQBlfLC3F4VCEcCYiAhHj+eE2h6XyrSTiFKhlatKGrELxWak58dfL2vmAvHQdB04t89/1O/w1cDnyilFU=";

	private Logger log = Logger.getLogger(MessageServiceImpl.class);

	@Override
	public boolean pushMessage(String content, String to) {
		BotApiResponse botApiResponse = null;
		LineMessagingClient client = LineMessagingClient.builder(CHANNEL_TOKEN).build();

		TextMessage textMessage = new TextMessage("hello");
		PushMessage pushMessage = new PushMessage("U34b21f21232f2c9134cbb741eedfa6d2", textMessage);

		try {
			botApiResponse = client.pushMessage(pushMessage).get();
			if (log.isDebugEnabled()) {
				log.debug(botApiResponse);
			}
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error sending Line message", e);
		}
		return botApiResponse != null;
	}

}
