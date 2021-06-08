package cn.bw.springbootmybatisjsp.mapper;

import cn.bw.springbootmybatisjsp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 获得smbms数据库中用户的数量
     * @return
     */
    @Select("select count(id) from smbms_user")
    public int getUserCount();

    @Select("select * from smbms_user")
    public List<User> getAllUser();


}
