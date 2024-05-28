package com.cskaoyan.th58;

import com.cskaoyan.th58.dao.User;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class redisson {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setCodec(new JsonJacksonCodec());
        RedissonClient redissonClient = Redisson.create(config);
        Map<Object, Object> userMap = redissonClient.getMap("user");
        userMap.clear();
        User user = new User("zs",10,false);
        userMap.put("user",user);
        User user2 = new User("ls",20,true);
        userMap.put("user2",user2);
//        Object o = userMap.get("user1");
        Set<Map.Entry<Object, Object>> entries = userMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
