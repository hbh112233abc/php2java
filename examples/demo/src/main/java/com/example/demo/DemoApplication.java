package com.example.demo;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.SaManager;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@SpringBootApplication
@RestController
@RequestMapping("/rest")
@MapperScan("com.example.demo.mapper")
public class DemoApplication {
    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
        System.out.println("启动成功,Sa-Token配置如下:"+SaManager.getConfig());
    }

    // @GetMapping
    // public String index() {
    //     return "GET无参请求Api方法已实现!";
    // }

    @GetMapping("/{id}")
    public String index(@PathVariable Long id) {
        System.out.printf("ID:%s\n",id);
        return "GET路径传参[PathVariable]请求,ID="+id;
    }

    @GetMapping
    public String index2(@RequestParam Long id,@RequestParam String name) {
        System.out.printf("GET ID:%d,Name:%s\n",id,name);
        return "GET传参[RequestParam],ID="+id+",Name="+name;
    }

    @PostMapping
    public String save(@RequestBody Map<String,Object> map) {
        System.out.printf("POST ",map.toString());
        return "Post请求成功,传参:"+map.toString();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Map<String,Object> map) {
        System.out.printf("PUT ID=%d, data=%s\n", id,map.toString());
        return "Put请求成功,ID="+id+",传参:"+map.toString();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        System.out.printf("DELETE ID=%d\n",id);
        return "Detele 请求成功,ID="+id;
    }


}
