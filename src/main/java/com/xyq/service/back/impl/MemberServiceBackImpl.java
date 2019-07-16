package com.xyq.service.back.impl;

import com.xyq.dao.IActionDao;
import com.xyq.dao.IMemberDao;
import com.xyq.dao.IRoleDao;
import com.xyq.dao.impl.ActionDaoImpl;
import com.xyq.dao.impl.MemberDaoImpl;
import com.xyq.dao.impl.RoleDaoImpl;
import com.xyq.service.abs.AbstractService;
import com.xyq.service.back.IMemberServiceBack;
import com.xyq.util.factory.DAOFactory;
import com.xyq.vo.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberServiceBackImpl extends AbstractService implements IMemberServiceBack {
    public Map<String, Object> login(Member vo) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        IMemberDao memberDao = DAOFactory.getInstance(MemberDaoImpl.class);
        Member checkVo = memberDao.findById(vo.getMid());//根据用户的id取得用户完整数据
        if(checkVo!=null){
            if (checkVo.getPassword().equals(vo.getPassword())){//密码验证通过
                IRoleDao roleDao = DAOFactory.getInstance(RoleDaoImpl.class);
                IActionDao actionDao = DAOFactory.getInstance(ActionDaoImpl.class);
                map.put("flag",true);//保存登陆成功的标识
                map.put("name",checkVo.getName());//将真是姓名保存
                map.put("sflag",checkVo.getSflag());//保存管理员标志
                map.put("allRoles",roleDao.findAllByMember(vo.getMid()));
                map.put("allActions",actionDao.findAllByMember(vo.getMid()));
            }else {
                map.put("flag",false);//保存登陆失败的标识
            }
        }else {
            map.put("flag",false);//保存登陆失败的标识
        }
        return map;
    }
}
