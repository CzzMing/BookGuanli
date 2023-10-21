//package com.buba.service;
//
//import redis.clients.jedis.Jedis;
//
//public class pingTest {
//    public static void main(String[] args) {
//        // 1. new Jedis 对象
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        // 如果设置密码 需要认证，没有设置忽略下面这条语句
//        jedis.auth("password");
//        /// jedis 所有的命令(方法)都是之前学的命令
//        System.out.println(jedis.ping());// 测试连接
//    }
//}
