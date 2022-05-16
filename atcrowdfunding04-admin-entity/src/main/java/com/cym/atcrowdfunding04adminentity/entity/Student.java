package com.cym.atcrowdfunding04adminentity.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * program: ssm
 * Date: 2022-03-29  21:36
 * Author: cym
 * Description:
 */
@Data
public class Student {
    private Integer stuId;
    private String stuName;
    private String mobile;
    private Address address;
    private List<School> schoolList;
    private Map<String, String> scoreMap;
}
