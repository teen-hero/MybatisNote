package com.note01;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.note01.dao.UserMapper;
import com.note01.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class MainApplicationTest {

    //不要忘记注入属性
    @Autowired
    UserMapper userMapper;

    //初体验
    @Test
    void contextLoads(){
        List<User> users = userMapper.selectList(null);
        for (User user:users){
            System.out.println(user);
        }
        //userMapper.insert(new User(new Long(12540450471L),"哈哈",20,"1566546w4w4"));
    }

    @Test
    public void test(){
        //插入
        //userMapper.insert(new User(null, "可达鸭", 18, "wda4654654"));
        User user = new User();
        //user.setId(1324565L);
        user.setAge(18);
        user.setName("吉安娜9999");
        user.setEmail("jan26465");
        userMapper.insert(user);
    }

    @Test
    public void test2(){
        //更新 （在后面练习中不小心改了数据库，我删库重新建库后数据可能和这些前面的练习不一致了）
        User user = new User();
        user.setId(1495591079993409537L);
        user.setName("更新娜");
        user.setEmail("dw6a48");
        userMapper.updateById(user);
    }
    //自动填充测试
    @Test
    public void test3(){
        User user = new User();
        user.setName("自动填充测试呀666");
        user.setEmail("dsa64158");
        user.setAge(98);
        userMapper.insert(user);
    }
    //乐观锁测试（可观察用户名修改为宁红叶还是胡桃来判断测试结果）
    @Test
    public void test4(){
        //模拟用户1进行修改操作
        User user1 = userMapper.selectById(1L);
        user1.setName("宁红叶");
        //模拟插入用户2进行修改操作
        User user2 = userMapper.selectById(1L);
        user2.setName("胡桃");
        userMapper.updateById(user2);
        //模拟用户1进行修改操作
        userMapper.updateById(user1);
    }

    @Test
    public void test5(){
        //批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for (User user:users){
            System.out.println(user);
        };
        System.out.println("-----------------------");
        //条件查询
        HashMap hashMap = new HashMap();
        hashMap.put("name","胡桃");
        List list = userMapper.selectByMap(hashMap);
        //遍历集合
        list.forEach(System.out::println);
    }
    //分页测试
    @Test
    public void test6(){
        Page<User> userPage = new Page<>(2,3);
        userMapper.selectPage(userPage, null);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
        long total = userPage.getTotal();
        System.out.println("getTotal是总记录数"+total);
    }

    @Test
    public void test7(){
        //删除
        userMapper.deleteById(4L);
    }

    //逻辑删除测试
    @Test
    public void test8(){
        //删除5号信息（此时应该是逻辑删除了）
        //逻辑删除原理
        //逻辑删除执行的其实不是删除操作，而是更新操作，讲deleted属性值改为1
        //然后查询操作sql中会在后面拼接deleted值的判断，值为1的就查询不来，实现逻辑删除
        userMapper.deleteById(5L);
        //查询检测是不是逻辑删除
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //条件构造器练习
    //重要总结：wrapper不加额外条件的话，只传入一个新new的wrapper就是查询全部，
    //其实wrapper就是用来生成sql的where条件的，不加条件，肯定 就是查询全部喽
    @Test
    public void test9(){
        //1.查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //Wrapper支持链式编程，可以连.方法，因为前面的.方法返回值就是Wrapper
        //关于wrapper这些方法比如下面这些.方法等其他没举出来的可以参考MybatisPlus官方文档
        userQueryWrapper.isNotNull("name").isNotNull("email").ge("age",12);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    //条件构造器练习
    @Test
    public void test10(){
        //2. 查询名字是胡桃的信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name","胡桃");
        User user = userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
    }
    //条件构造器练习
    @Test
    public void test11(){
        //3.查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.between("age",20,30);
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }
    //条件构造器练习
    @Test
    public void test12(){
        //4.模糊查询
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //姓名不包括“胡”字  name NOT LIKE '%胡%'
        userQueryWrapper.notLike("name","胡");
        //likeLeft就是拼接sql中为email like %a
        //likeRight就是拼接sql中为email like a%
        userQueryWrapper.likeLeft("email","a");
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }
    //条件构造器练习
    @Test
    public void test13(){
        //5.id在子查询中查出来
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id","select id from user where id<5");
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }
    //条件构造器练习
    @Test
    public void test14(){
        //6.通过id进行排序
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //从大到小排序
        userQueryWrapper.orderByDesc("id");
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }


}
