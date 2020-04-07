package com.sac;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author ShiAC
 * @date 2020/4/3
 */
public class TestPing {
    public static void main(String[] args) {
        //1.new jedis对象
        Jedis jedis = new Jedis("139.196.178.48", 6379);
        jedis.flushDB();
        //jedis所有的命令就是redis的所有指令
        //System.out.println(jedis.ping());
        JSONObject js = new JSONObject();
        js.put("hello", "world");
        js.put("name", "sac");
        //开启事务
        Transaction multi = jedis.multi();
        String result = js.toJSONString();
        try {
            multi.set("user1", result);
            multi.set("user2", result);
            int i = 1 / 0;//制造错误，让事务执行失败
            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();//关闭连接
        }
    }
}
