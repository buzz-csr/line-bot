package com.line.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;

public class MessageServiceImplTest {

	private MessageService service = new MessageServiceImpl();

	@Test
	public void testPushMessageOk() {
		Assertions.assertThat(service.pushMessage(new TextMessage("message"), "U34b21f21232f2c9134cbb741eedfa6d2"))
		        .isTrue();
	}

	@Test
	public void testPushMessageBadDestination() {
		Assertions.assertThat(service.pushMessage(new TextMessage("message"), null)).isFalse();
	}

	@Test
	public void testPushImage() throws URISyntaxException {
		URI uri = new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/150_rouge.jpg");
		service.pushImage(new ImageMessage(uri, uri), "U34b21f21232f2c9134cbb741eedfa6d2");
	}
}
