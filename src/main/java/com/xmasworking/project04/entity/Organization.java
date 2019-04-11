package com.xmasworking.project04.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2019/4/11 - 10:52 AM
 * Created by IntelliJ IDEA.
 */
public class Organization extends IDBaseEntity{

    /**
     * 组织机构名称
     */
    private String name;
    /**
     * 父编号
     */
    private Long parentId;
    /**
     * 父编号列表，如1/2/
     */
    private String parentIds;
    private Boolean available = Boolean.FALSE;
}
