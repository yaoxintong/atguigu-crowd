import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.MyadminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


//在类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {

    @Autowired
    private MyadminMapper myadminMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testInsertAdmin(){

        System.out.println(adminMapper);
        List<Admin> list=myadminMapper.select();
        System.out.println("受影响的行数"+list);
    }


    @Test
    public void  insertAdmin(){
        Admin admin=new Admin();
        admin.setUserName("渝新欧欣桐");
        admin.setId(2);


        System.out.println(adminMapper.insert(admin));
    }


}
