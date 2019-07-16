package com.xyq.service.abs;

import com.xyq.dao.IActionDao;
import com.xyq.dao.impl.ActionDaoImpl;
import com.xyq.util.factory.DAOFactory;

public abstract class AbstractService {
    /**
     * 进行相关用户的权限认证查询
     * @param mid 用户的编号
     * @param actionFlag 权限的标记信息
     * @return 如果具备有指定的权限,返回true,否则返回false
     * @throws Exception
     */
    public boolean auth(String mid,String actionFlag) throws Exception{
        IActionDao actionDao = DAOFactory.getInstance(ActionDaoImpl.class);
        return actionDao.findExists(mid,actionFlag);
    }
}
