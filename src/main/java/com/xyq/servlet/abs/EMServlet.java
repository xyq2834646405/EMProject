package com.xyq.servlet.abs;

import com.xyq.util.servlet.DispatcherServlet;

import java.util.Set;

public class EMServlet extends DispatcherServlet {

    /**
     * 登陆之后所有的角色和权限都保存在session之中,所以此时直接通过session取得数据验证即可
     * @param actionFlag
     * @return
     */
    public boolean auth(String actionFlag){//检测指定的用户是否具备有指定的权限
        Set<String> allActions = (Set<String>) getSession().getAttribute("allActions");
        return allActions.contains(actionFlag);//判断是否具备有指定的权限
    }

    /**
     * 取得当前用户的编号
     * @return
     */
    public String getMid(){
        return getSession().getAttribute("mid").toString();
    }

    public String getDefaultColumn() {
        return null;
    }

    public String getUploadDir() {
        return null;
    }

    public String getType() {
        return null;
    }
}
