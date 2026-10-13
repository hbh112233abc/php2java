package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增用户
     */
    @PostMapping
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }
    /**
     * 修改用户
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }
    /**
     * 查询用户
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id) {
        User u = userService.getById(id);
        return Result.success(u);
    }
    /**
     * 查询所有用户
     */
    @GetMapping
    @SaCheckPermission("user.list")
    public Result list() {
        return Result.success(userService.list());
    }
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean r = userService.removeById(id);
        System.out.println("删除操作结果:"+r);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "2") Integer pageSize,
        @RequestParam(defaultValue = "") String name
    ) {
        log.info("测试分页请求/api/user/page");
        LambdaQueryWrapper<User> q = new LambdaQueryWrapper<>();
        if (!"".equals(name)) {
            q.like(User::getName, name);
        }
        return Result.success(
            userService.page(new Page<>(page, pageSize), q)
        );
    }

    // 测试登录，浏览器访问： http://localhost:8080/api/user/doLogin?username=zhang&password=123456
	// @RequestMapping("doLogin")
	// public String doLogin(String username, String password) {
	// 	// 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
	// 	if("zhang".equals(username) && "123456".equals(password)) {
	// 		StpUtil.login(10001);
	// 		return "登录成功";
	// 	}
	// 	return "登录失败";
	// }

    @RequestMapping("doLogin")
    public Result doLogin() {
        log.warn("虚假的登录");
        // 第1步，先登录上
        StpUtil.login(10001);
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，返回给前端
        return Result.success(tokenInfo);
    }

	// 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
	@RequestMapping("isLogin")
	public String isLogin() {
		return "当前会话是否登录：" + StpUtil.isLogin();
	}

}
