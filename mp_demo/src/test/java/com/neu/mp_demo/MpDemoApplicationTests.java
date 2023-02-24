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
        user.setName("Jason111");
        user.setAge(23);
        user.setEmail("@126.com");
        int result = usermapper.insert(user);
        System.out.println("result:"+result);

}
    @Test
    public void updateUser(){
        //这里的l是把int变成long
        User user = new User(1L,null,18,"email111333",null,null);

        int result = usermapper.updateById(user);
        System.out.println("result"+result);
    }


}
