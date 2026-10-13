package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.RedisUtil;

// 明确指定测试使用的配置类，避免模块内存在多个 @SpringBootApplication 时无法确定入口
@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

	@Test
	void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(userList.size() >= 5, "");
        userList.forEach(System.out::println);
	}

    @Test
    void testUserService() {
        List<User> userList = userService.list();
        Assert.isTrue(userList.size() >= 5, "");
        userList.forEach(System.out::println);
    }

    @Test
    void testRedisUtil() {
        List<User> userList = userService.list();
        redisUtil.set("userList",userList);
    }

}
