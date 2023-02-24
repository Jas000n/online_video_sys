package com.neu.mp_demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neu.mp_demo.entity.User;
import com.neu.mp_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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
//    public static void main(String[] args) {
//        System.out.println("控制台颜色测试：");
//        System.out.println(getFormatLogString("[ 红色 ]", 31, 0));
//        System.out.println(getFormatLogString("[ 黄色 ]", 32, 0));
//        System.out.println(getFormatLogString("[ 橙色 ]", 33, 0));
//        System.out.println(getFormatLogString("[ 蓝色 ]", 34, 0));
//        System.out.println(getFormatLogString("[ 紫色 ]", 35, 0));
//        System.out.println(getFormatLogString("[ 绿色 ]", 36, 0));
//    }
    //控制台带颜色
    private static String getFormatLogString(String content, int color, int type) {
        boolean hasType = type != 1 && type != 3 && type != 4;
        if (hasType) {
            return String.format("\033[%dm%s\033[0m", color, content);
        } else {
            return String.format("\033[%d;%dm%s\033[0m", color, type, content);
        }
    }

    @Test
    public void test_batch_select(){
        //批量查询
        List<User> users = usermapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        System.out.println(getFormatLogString(users.toString(),31,0));

    }

    @Test
    public void testPage(){
        //创建page对象
        Page<User> page = new Page<>(1,3);
        //调用mp分页查询，会把分页数据封装到page对象里
        usermapper.selectPage(page,null);
        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数
    }


}
