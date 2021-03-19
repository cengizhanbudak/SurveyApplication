package com.uniyaz.core.service;

import com.uniyaz.core.dao.SecenekDao;
import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Secenek;

public class SecenekService {
    SecenekDao secenekDao;


    public void saveSoru(Secenek secenek) {


            secenekDao.saveSecenek(secenek);
    }


}
