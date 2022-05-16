package pro01.spring.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pro01.spring.security.entity.Admin;

import java.util.List;
import java.util.Map;

/**
 * program: ssm
 * Date: 2022-04-08  13:44
 * Author: cym
 * Description:
 * @author cym
 */

@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        //从数据库中查询当前用户的信息
        String sql = "select  id,loginacct,userpswd,username,email,createtime from security.t_admin where loginacct = ?";
        //查询条件为传进来的用户名
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql,username);

        String loginacct = resultMap.get("loginacct").toString();
        String userpswd = resultMap.get("userpswd").toString();
        //为当前用户分配角色和权限，如果要分配角色需要加上前缀ROLE_,否则分配的是权限
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN","USER");
        //返回一个User实体对象
        return new User(loginacct,userpswd,authorities);
    }
}
