package com.note01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.note01.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
