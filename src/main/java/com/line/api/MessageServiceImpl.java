package com.line.api;

import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

public class MessageServiceImpl implements MessageService {

	private static final String CHANNEL_TOKEN = "eVXK3prAepp2PqZ2VvN//E1xpXGhya8+ofCDsx6KplRb/5OAfHcCeBg+reXMnFk1OvEyWEcFpPaha0zMpfzPKF6CAUWQBlfLC3F4VCEcCYiAhHj+eE2h6XyrSTiFKhlatKGrELxWak58dfL2vmAvHQdB04t89/1O/w1cDnyilFU=";

	private Logger log = Logger.getLogger(MessageServiceImpl.class);

	@Override
	public boolean pushMessage(TextMessage message) {
		CompletableFuture<BotApiResponse> botApiResponse = null;
		LineMessagingClient client = LineMessagingClient.builder(CHANNEL_TOKEN).build();

		botApiResponse = client.broadcast(new Broadcast(message));
		if (log.isDebugEnabled()) {
			log.debug(botApiResponse);
		}
		return botApiResponse != null;
	}

	@Override
	public boolean pushImage(ImageMessage imgMessage) {
		CompletableFuture<BotApiResponse> botApiResponse = null;
		LineMessagingClient client = LineMessagingClient.builder(CHANNEL_TOKEN).build();

		botApiResponse = client.broadcast(new Broadcast(imgMessage));
		if (log.isDebugEnabled()) {
			log.debug(botApiResponse);
		}
		return botApiResponse != null;
	}

}
