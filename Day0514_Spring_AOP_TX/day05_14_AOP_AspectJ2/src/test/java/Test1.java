import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.service.GoodsService;
import com.jinhong.th58.service.UserService;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test1 {
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Test
    public void test1(){
        goodsService.addOne();
        System.out.println("=========");
        goodsService.selectOne();
    }
    @Test
    public void test2(){
        userService.addOne();
        System.out.println("===========");
        userService.selectOne();
    }
}

