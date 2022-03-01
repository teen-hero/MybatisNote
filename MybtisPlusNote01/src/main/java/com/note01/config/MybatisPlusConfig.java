package com.note01.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//设置MybatisPlus配置类
@Configuration
public class MybatisPlusConfig {
    //配置乐观锁
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //配置分页
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    //配置逻辑删除
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    //配置性能分析插件
    @Bean
    @Profile({"dev","test"})   //设置在dev、test环境开启，保证我们的效率  //注意还要在SpringBoot配置文件中配置开发环境为dev或者test才可以
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //设置sql可执行的最大时间，如果超过了则不执行并会报错，从而找出哪些sql时间过长，进而对其进行优化
        performanceInterceptor.setMaxTime(100);
        //设置sql语句是否格式化（其实就是按照一定标准来标准化而已）
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
