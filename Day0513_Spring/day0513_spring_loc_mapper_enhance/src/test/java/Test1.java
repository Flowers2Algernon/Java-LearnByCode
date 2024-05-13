import com.jinhong.th58.config.SpringConfig;
import com.jinhong.th58.domain.User;
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

    //需要将该对象放入Spring容器中
    @Autowired
    UserService userService;

    @Test
    public void test1(){
        userService.addUser(new User("admmin","5131530",null));
    }
}
