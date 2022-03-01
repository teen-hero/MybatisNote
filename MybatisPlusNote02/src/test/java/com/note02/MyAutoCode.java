package com.note02;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.assertj.core.internal.Arrays;

import java.util.ArrayList;

//代码自动生成器
public class MyAutoCode {
    public static void main(String[] args) {
        //构建一个代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();

        //全局配置(GlobalConfig不要导错包)
        GlobalConfig gc = new GlobalConfig();
        //获取用户程序当前路径（项目根的路径）
        //这里的propertyPath是D:\Java\资料及练习\MybatisPlus练习
        String propertyPath = System.getProperty("user.dir");
        //生成文件的输出目录
        gc.setOutputDir(propertyPath+"/MybatisPlusNote02/src/main/java");
        //开发人员
        gc.setAuthor("济沧海");
        //是否打开输出目录
        gc.setOpen(false);
        //把全局配置添加到代码生成器主类
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        //包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("autocode");
        pc.setParent("com.note02");
        pc.setEntity("autoUser");
        pc.setMapper("autoUserMapper");
        pc.setService("autoService");
        pc.setServiceImpl("autoServiceImpl");
        pc.setXml("autoXml");
        mpg.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置要映射的表名
        strategy.setInclude("autouser");
        //设置数据库表映射到实体的命名策略:驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置lombok
        strategy.setEntityLombokModel(true);
        //设置逻辑删除使用的字段名
        strategy.setLogicDeleteFieldName("deleted");
        //配置自动填充
        //注意，这个地方create_time及update_time要写数据库中的字段名，不能写对应生成的实体类属性名，否则无法实现自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList list = new ArrayList();
        list.add(createTime);
        list.add(updateTime);
        strategy.setTableFillList(list);
        //设置乐观锁字段名称
        strategy.setVersionFieldName("version");
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();

    }

}
