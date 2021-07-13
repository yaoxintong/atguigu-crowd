import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.MyadminMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


//在类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private MyadminMapper myadminMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testRoleSave(){
        for(int i = 0; i <235; i++){
            roleMapper.insert(new Role(null, "role"+i));
        }
    }

    @Test
    public void textTx(){
        Admin admin = new Admin(null,"jerry", "123456", "杰瑞", "jerry@qq.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testInsertAdmin(){

        System.out.println(adminMapper);
        List<Admin> list=myadminMapper.select();
        System.out.println("受影响的行数"+list);
    }
    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("hello i am debug");
        logger.error("hello i am erro");
        logger.info("heloow i am info");
    }
    @Test
    public void test(){
        for(int i=0;i<238;i++){
            adminMapper.insert(new Admin(null,"loginAcct"+i,"userPswd"+i,"username"+i,"email"+i,null));
        }
    }
    @Test
    public void  insertAdmin(){
        Admin admin=new Admin();
        admin.setUserName("渝新欧欣桐");
        admin.setId(3);


        System.out.println(adminMapper.insert(admin));
    }


}
