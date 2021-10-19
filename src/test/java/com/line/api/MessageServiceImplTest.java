package com.line.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MessageServiceImplTest {

	private MessageService service = new MessageServiceImpl();

	@Test
	public void testPushMessageOk() {
		Assertions.assertThat(service.pushMessage("message", "U34b21f21232f2c9134cbb741eedfa6d2")).isTrue();
	}

	@Test
	public void testPushMessageBadDestination() {
		Assertions.assertThat(service.pushMessage("message", null)).isTrue();
	}
}
