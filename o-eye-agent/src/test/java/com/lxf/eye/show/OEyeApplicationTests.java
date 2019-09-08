package com.lxf.eye.show;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class OEyeApplicationTests {
	@Resource
	private AmqpTemplate rabbitTemplate;
	@Test
	public void contextLoads() {
		Date date = new Date();
		String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
		System.out.println("[string] send msg:" + dateString);
		// 第一个参数为刚刚定义的队列名称
		this.rabbitTemplate.convertAndSend("string", dateString);
	}

}
