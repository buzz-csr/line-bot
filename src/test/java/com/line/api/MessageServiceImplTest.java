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
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.container.BubbleStyles;
import com.linecorp.bot.model.message.flex.container.BubbleStyles.BlockStyle;
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexPaddingSize;

public class MessageServiceImplTest {

	private static final String TO = "U34b21f21232f2c9134cbb741eedfa6d2";
	private MessageService service = new MessageServiceImpl(
	        "7kyBLISMOtD9T0SVoUPhCspk732uw40q6pMr5b5/LJ/Fl1bNbefBNBl4gVLzfjdBvQXg8XQhuWOf5w8qNExJj+6CDNC6tIsyoWOuDR3Esluk/MZr/wgA7siv/r/5ZuEeb/Ie2tW/HRdSX/u4W9rZ6QdB04t89/1O/w1cDnyilFU=");

	@Test
	public void testFlexMessage() throws URISyntaxException {
		FlexMessage flexMessage = createFlexMessage();
		Assertions.assertThat(service.pushMessage(flexMessage, TO)).isTrue();
	}

	private FlexMessage createFlexMessage() throws URISyntaxException {
		FlexComponent heroComponent = Image.builder().size(ImageSize.FULL_WIDTH).aspectMode(ImageAspectMode.Cover)
		        .aspectRatio("10:2").url(new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/Alliance1.png"))
		        .build();
		FlexContainer flexContainer = Bubble.builder()
		        .styles(BubbleStyles.builder()
		                .body(BlockStyle.builder().backgroundColor("#000000").separatorColor("#ffffff").build())
		                .build())
		        .hero(heroComponent).size(BubbleSize.GIGA).body(body()).build();
		FlexMessage flexMessage = FlexMessage.builder().altText("Message du bot...").contents(flexContainer).build();
		return flexMessage;
	}

	private Box body() {
		return Box.builder().width("100%").paddingAll(FlexPaddingSize.LG).layout(FlexLayout.VERTICAL)
		        .contents(createLine(), separator(), createLine()).build();
	}

	private Box createLine() {
		Text cell1 = Text.builder().flex(2).text("Lundi").color("#ffffff").build();
		Box cell2 = Box.builder().flex(5).paddingStart(FlexPaddingSize.LG).layout(FlexLayout.VERTICAL)
		        .contents(cell("User 1 un peu lonf"), separator(), cell("User 2"), separator(),
		                cell("User 3 encore plus"))
		        .build();
		Box cell3 = Box.builder().flex(3).layout(FlexLayout.VERTICAL).contents(cellEnd("25 sur le 70"), separator(),
		        cellEnd("150 sur le 70"), separator(), cellEnd("10 sur le 30")).build();
		Box cell4 = Box.builder().flex(3).layout(FlexLayout.VERTICAL)
		        .contents(cellEnd("20:55:28"), separator(), cellEnd("20:55:28"), separator(), cellEnd("20:55:28"))
		        .build();
		return Box.builder().paddingTop(FlexPaddingSize.LG).layout(FlexLayout.HORIZONTAL)
		        .contents(cell1, separator(), cell2, cell3, cell4).build();
	}

	private Separator separator() {
		return Separator.builder().color("#ffffff").build();
	}

	private Text cell(String name) {
		return Text.builder().align(FlexAlign.START).text(name).color("#ffffff").wrap(true).build();
	}

	private Text cellEnd(String name) {
		return Text.builder().align(FlexAlign.END).text(name).color("#ffffff").wrap(true).build();
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
		URI uri = new URI("https://mod.csr-lesnains.fr/csr-admin/images/line/150_team1.jpg");
		service.pushImage(new ImageMessage(uri, uri), TO);
	}

	@Test
	@Ignore
	public void testGetGroupUsers() {
		List<String> actual = service.getGroupUsers(TO);
		Assertions.assertThat(actual).contains("");
	}

}
