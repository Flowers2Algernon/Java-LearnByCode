import com.cskaoyan.th58.config.SpringConfig;
import com.cskaoyan.th58.mapper.UserMapper;
import com.cskaoyan.th58.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SprintTest {
    @Test
    public void test1(){
        //使用Spring去管理容器，前提是需要有一个Spring容器
        //Spring容器在Spring框架中是由applicationContext来充当的。
        //applicationContext是一个接口，具体的子类实现我们此处选择的是读取xml来实例化容器
        //输入一个位于classpath目录下的xml文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");

        //spring 会帮助我们创建对应的实例对象，并且放入到spring容器中
        //只需要利用容器的方法来获取位于容器中的组件即可
        UserService userService = (UserService) context.getBean("userService");
        UserMapper userMapper = (UserMapper) context.getBean("userMapper1");
        System.out.println(userService);
        System.out.println(userMapper);
    }
    @Test
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Object userfb = context.getBean("userfb");
        //此处得到的是user型，因为实现FactoryBean的类调用其id时，得到的不是其xml-bean里面的全限定类名的那个类
        //而是得到其内部getObject方法的返回值结果
        System.out.println(userfb);
    }

    @Test
    public void test3(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        //利用编号从容器中来获取对象
        Object userService = context.getBean("userService");
        Object userMapper = context.getBean("userMapper");
        Object orderService = context.getBean("orderService");
        System.out.println(userService);
        System.out.println(userMapper);
        System.out.println(orderService);
    }
}
