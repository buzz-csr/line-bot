package com.line.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.FlexContainer;

public class MessageServiceImplTest {

	private static final String TO = "U34b21f21232f2c9134cbb741eedfa6d2";
	private MessageService service = new MessageServiceImpl(
	        "7kyBLISMOtD9T0SVoUPhCspk732uw40q6pMr5b5/LJ/Fl1bNbefBNBl4gVLzfjdBvQXg8XQhuWOf5w8qNExJj+6CDNC6tIsyoWOuDR3Esluk/MZr/wgA7siv/r/5ZuEeb/Ie2tW/HRdSX/u4W9rZ6QdB04t89/1O/w1cDnyilFU=");

	@Test
	public void testFlexMessage() throws URISyntaxException {
		FlexComponent heroComponent = Image.builder()
		        .url(new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/header_1.JPG")).build();
		FlexContainer flexContainer = Bubble.builder().hero(heroComponent).build();
		FlexMessage flexMessage = FlexMessage.builder().altText("Message du bot...").contents(flexContainer).build();
		Assertions.assertThat(service.pushMessage(flexMessage, TO)).isTrue();
	}

	@Test
	public void testPushMessageOk() {
		Assertions.assertThat(service.pushMessage(new TextMessage("message"), TO)).isTrue();
	}

	@Test
	public void testPushMessageBadDestination() {
		Assertions.assertThat(service.pushMessage(new TextMessage("message"), null)).isFalse();
	}

	@Test
	public void testPushImage() throws URISyntaxException {
		URI uri = new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/150_rouge.jpg");
		service.pushImage(new ImageMessage(uri, uri), TO);
	}

	@Test
	@Ignore
	public void testGetGroupUsers() {
		List<String> actual = service.getGroupUsers(TO);
		Assertions.assertThat(actual).contains("");
	}

}
