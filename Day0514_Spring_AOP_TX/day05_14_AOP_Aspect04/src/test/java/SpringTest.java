import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    GoodsService goodsService;

    @Test
    public void test1(){
        goodsService.addOne();

    }
}
