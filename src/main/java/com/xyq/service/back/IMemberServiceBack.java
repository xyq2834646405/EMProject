package com.xyq.service.back;

import com.xyq.vo.Member;

import java.util.Map;

/**
 * Member类的后台服务接口
 */
public interface IMemberServiceBack {
    /**
     * 实现用户的登陆处理,再登陆处理之前需要进行如下的功能操作:
     * <li>1、实现基本的用户名和密码检测,检测成功之后需要取出mid(登陆检测)和name信息进行前台显示</li>
     * <li>2、要根据用户查询出所有的角色信息</li>
     * <li>3、要根据用户查询出所有对应的权限信息</li>
     * @param vo 包含有用户输入的用户名和密码
     * @return 返回如下数据内容:
     * <li>key=flag、value=true|false,表示登陆成功或失败的标记</li>
     * <li>key=sflag、value=1|0,表示当前登陆的账户是否是超级管理员</li>
     * <li>key=name、value=真是姓名,表示当前管理员的真是姓名,此信息要在页面上显示</li>
     * <li>key=allRoles、value=Set,保存所有的角色数据</li>
     * <li>key=allActions、value=Set,保存所有的权限数据</li>
     * @throws Exception
     */
    public Map<String,Object> login(Member vo) throws Exception;
}
