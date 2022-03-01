package com.note01.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor //配备有参构造
@NoArgsConstructor  //配备无参构造
public class User {
    //下面的@TableId及@TableField注解的使用方法参考如下博客笔记
    //https://blog.csdn.net/weixin_42526068/article/details/113039510
    //主键生成策略练习
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //自动填充练习
    @TableField(value = "create_time",fill = FieldFill.INSERT)     //其实这里不需要指定属性名与数据库列名的映射，因为MybatisPlus默认开启了驼峰命名
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //乐观锁
    @Version
    private Integer version;
    //逻辑删除
    @TableLogic
    private Integer deleted;

}
