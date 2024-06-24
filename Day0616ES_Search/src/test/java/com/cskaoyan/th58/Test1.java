package com.cskaoyan.th58;

import com.cskaoyan.th58.repository.GoodsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Test1 {

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    public void test() {
        System.out.println(goodsRepository);
    }

    @Test
    public void testSimple(){

    }
}
