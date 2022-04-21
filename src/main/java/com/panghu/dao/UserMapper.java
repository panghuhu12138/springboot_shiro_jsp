package com.panghu.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.panghu.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author panghuhu
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-04-20 16:33:58
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
