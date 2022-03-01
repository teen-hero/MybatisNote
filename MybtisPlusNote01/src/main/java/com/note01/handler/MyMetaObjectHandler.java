package com.note01.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
//设置如下处理器来处理实体类属性上的 @TableField(value = "create_time",fill = FieldFill.INSERT)自动填充注解
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时策略
    @Override
    public void insertFill(MetaObject metaObject) {
        //填充创建时间createTime
        //用第二个参数new Date()填充第一个参数createTime
        setFieldValByName("createTime",new Date(),metaObject);
        //填充修改时间updateTime
        //用第二个参数new Date()填充第一个参数updateTime
        setFieldValByName("updateTime",new Date(),metaObject);
    }
    //更新时策略
    @Override
    public void updateFill(MetaObject metaObject) {
        //填充修改时间updateTime
        setFieldValByName("updateTime",new Date(),metaObject);
    }
}
