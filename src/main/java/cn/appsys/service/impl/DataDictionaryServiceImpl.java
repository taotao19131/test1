package cn.appsys.service.impl;

import cn.appsys.dao.DataDictionaryDao;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryDao dataDictionaryDao;

    @Override
    public void add(DataDictionary dataDictionary) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public void detail(DataDictionary dataDictionary) {

    }

    @Override
    public DataDictionary findById(Integer id) {
        return null;
    }

    @Override
    public List<DataDictionary> findByTypeCode(String typeCode) {
        return dataDictionaryDao.selectByTypeCode(typeCode);
    }
}
