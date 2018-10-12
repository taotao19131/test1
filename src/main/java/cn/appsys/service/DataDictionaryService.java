package cn.appsys.service;

import cn.appsys.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {

    void add(DataDictionary dataDictionary);

    void remove(Integer id);

    void detail(DataDictionary dataDictionary);

    DataDictionary findById(Integer id);

    List<DataDictionary> findByTypeCode(String typeCode);

}
