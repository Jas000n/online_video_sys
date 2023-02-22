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

}
