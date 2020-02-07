package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.TouristMapper;
import cn.hfut.huangshan.pojo.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 游客
 * @author pcy
 */
@Service
public class TouristService {

    @Autowired
    TouristMapper touristMapper;

    @Transactional
    public List<Tourist> getAllTourists(){
        List<Tourist> tourists = touristMapper.getAllTourists();
        return tourists;
    }
}
