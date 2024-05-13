import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test1 {
    @Autowired
    GoodsService goodsService;

    @Test
    public void test1(){
        goodsService.addOne();
    }
    @Test
    public void test2(){
        goodsService.selectOne();
    }
}
