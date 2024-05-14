import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test1 {

    @Autowired
    TransferService transferService;

    @Test
    public void test1(){
        transferService.transfer("boss","lisi",10000.0);
    }
}
