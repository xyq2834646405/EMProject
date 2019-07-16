package com.xyq.service.back.impl;

import com.xyq.dao.ILevelDao;
import com.xyq.dao.impl.LevelDaoImpl;
import com.xyq.service.abs.AbstractService;
import com.xyq.service.back.ILevelServiceBack;
import com.xyq.util.factory.DAOFactory;
import com.xyq.vo.Level;

public class LevelServiceBackImpl extends AbstractService implements ILevelServiceBack {
    public Level get(int id) throws Exception {
        ILevelDao levelDao = DAOFactory.getInstance(LevelDaoImpl.class);
        return levelDao.findById(id);
    }
}
