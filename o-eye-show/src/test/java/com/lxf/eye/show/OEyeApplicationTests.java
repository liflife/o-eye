package com.lxf.eye.show;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OEyeApplicationTests {
	@Resource
	private EyeShowConf eyeShowConf;
	@Test
	public void contextLoads() {
		System.out.printf("eyeShowConf:"+eyeShowConf.version);
	}

}
