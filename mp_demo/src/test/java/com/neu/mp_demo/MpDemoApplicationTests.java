package com.neu.mp_demo;

import com.neu.mp_demo.entity.User;
import com.neu.mp_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MpDemoApplicationTests {

    @Autowired
    private UserMapper usermapper;



    @Test
    public void findAll() {
        List<User> AllUsers = usermapper.selectList(null);
        System.out.println(AllUsers);
    }
    @Test
    public void addUser(){
        User user = new User();
        user.setName("test_opt_locker");
        user.setAge(23);
        user.setEmail("@126.com");
        int result = usermapper.insert(user);
        System.out.println("result:"+result);

}
    @Test
    //测试乐观锁需要先查再改，故下面这种方法不行
    public void updateUser(){
        //这里的l是把int变成long
        User user = new User();
        user.setId(1629024436759855106L);
        user.setName("opt_locker");

        int result = usermapper.updateById(user);
        System.out.println("result"+result);
    }
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User test_user = usermapper.selectById(1629024436759855106L);
        test_user.setName("Jason_test");
        int result = usermapper.updateById(test_user);
        System.out.println("result is : "+result);

    }


}
