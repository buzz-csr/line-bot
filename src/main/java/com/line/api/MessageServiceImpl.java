package com.line.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.profile.MembersIdsResponse;
import com.linecorp.bot.model.response.BotApiResponse;

public class MessageServiceImpl implements MessageService {

	private Logger log = Logger.getLogger(MessageServiceImpl.class);

	private String channelToken;

	public MessageServiceImpl(String channelToken) {
		this.channelToken = channelToken;
	}

	@Override
	public boolean pushMessage(Message message, String to) {
		BotApiResponse botApiResponse = null;
		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();

		PushMessage pushMessage = new PushMessage(to, message);

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

	@Override
	public boolean pushImage(ImageMessage imgMessage, String to) {
		BotApiResponse botApiResponse = null;
		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();

		try {
			PushMessage pushMessage = new PushMessage(to, imgMessage);
			botApiResponse = client.pushMessage(pushMessage).get();
			if (log.isDebugEnabled()) {
				log.debug(botApiResponse);
			}
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error sending Line message", e);
		}
		return botApiResponse != null;
	}

	@Override
	public List<String> getGroupUsers(String groupId) {
		List<String> response = null;
		LineMessagingClient client = LineMessagingClient.builder(channelToken).build();
		CompletableFuture<MembersIdsResponse> groupMembersIds = client.getGroupMembersIds(groupId, null);
		try {
			MembersIdsResponse membersIdsResponse = groupMembersIds.get();
			if (membersIdsResponse != null) {
				response = membersIdsResponse.getMemberIds();
			}
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error get users list", e);
		}

		return response;
	}

}
