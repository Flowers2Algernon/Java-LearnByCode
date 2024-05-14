import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.domain.MarketAdmin;
import com.jinhong.th58.service.LogService;
import com.jinhong.th58.service.MarketAdminService;
import com.jinhong.th58.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class LogTest {
    @Autowired
    LogService logService;
    @Autowired
    MarketAdminService marketAdminService;
    @Autowired
    OrderService orderService;

    @Test
    public void test1(){
        MarketAdmin login = marketAdminService.login("admin", "dasd");
    }
}
