import com.cskaoyan.th58.bean.Student1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    @Test
    public void test1() throws JsonProcessingException {
        Student1 student1 = new Student1();
        student1.setPassword("asdawjd");
        student1.setUsername("admin");

        //将student对象转换为json字符串
        //使用依赖将Java对象转换为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(student1);
        System.out.println(string);
    }

    @Test
    public void test2() throws JsonProcessingException {
        //List<student>对象类型转换为json字符串
        Student1 student1 = new Student1();
        student1.setUsername("asdaw");
        student1.setPassword("safawd");
        Student1 student11 = new Student1();
        student11.setPassword("Sdawswa");
        student11.setUsername("daw213");
        ArrayList<Student1> student1s = new ArrayList<>();
        student1s.add(student11);
        student1s.add(student1);
        //[Student1(username=daw213, password=Sdawswa), Student1(username=asdaw, password=safawd)]
        System.out.println(student1s);

        //以下是转换过程
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(student1s);
        //[{"username":"daw213","password":"Sdawswa"},{"username":"asdaw","password":"safawd"}]
        System.out.println(string);
    }


    @Test
    public void test3() throws JsonProcessingException {
        //反方向，将json对象转换为java对象
        //简单情况
        String s ="{\"username\":\"admin\",\"password\":\"admin123\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Student1 student1 = objectMapper.readValue(s, Student1.class);
        System.out.println(student1);
    }
    @Test
    public void test4() throws JsonProcessingException {
        //反方向，将json对象转换为java对象
    String s = "[{\"username\":\"daw213\",\"password\":\"Sdawswa\"},{\"username\":\"asdaw\",\"password\":\"safawd\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        //构建一个复杂的类型，此处需要说明集合是什么类型，集合内部的元素是什么类型
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, Student1.class);
        //得到一个复杂type后，将其放入到转换的readValue中
        ArrayList<Student1> student1ArrayList = objectMapper.readValue(s, collectionType);
        for (Student1 student1 : student1ArrayList) {
            System.out.println(student1);
        }
    }
}
