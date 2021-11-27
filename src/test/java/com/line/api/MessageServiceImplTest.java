package com.line.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.TextMessage.Emoji;
import com.linecorp.bot.model.message.TextMessage.Emoji.EmojiBuilder;
import com.linecorp.bot.model.message.TextMessage.TextMessageBuilder;

public class MessageServiceImplTest {

	private MessageService service = new MessageServiceImpl();

	@Test
	public void testPushMessageEmoji() {
		TextMessageBuilder builder = TextMessage.builder();
		EmojiBuilder emoBuilder = Emoji.builder();
		emoBuilder.productId("5ac21a8c040ab15980c9b43f");
		emoBuilder.emojiId("001");
		emoBuilder.index(0);

		builder.emojis(Arrays.asList(emoBuilder.build()));
		builder.text("$ coucou");

		TextMessage message = builder.build();
		Assertions.assertThat(service.pushMessage(message)).isTrue();
	}

	@Test
	public void testPushMessageOk() {
		Assertions.assertThat(service.pushMessage(new TextMessage("message"))).isTrue();
	}

	@Test
	public void testPushImage() throws URISyntaxException {
		URI uri = new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/150_rouge.jpg");
		service.pushImage(new ImageMessage(uri, uri));
	}
}
