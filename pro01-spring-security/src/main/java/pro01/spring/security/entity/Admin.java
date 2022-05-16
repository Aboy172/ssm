package pro01.spring.security.entity;

import lombok.Data;

/**
 * program: ssm
 * Date: 2022-04-08  13:47
 * Author: cym
 * Description:
 */
@Data
public class Admin {

    private Integer id;
    private String loginAcct;
    private String username;
    private String userpswd;
    private String email;
}
