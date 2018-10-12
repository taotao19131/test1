package cn.appsys.dao;

import cn.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDictionaryDao {

    void insert(DataDictionary dataDictionary);

    void delete(Integer id);

    void uodate(DataDictionary dataDictionary);

    DataDictionary selectById(Integer id);

    DataDictionary selectByTypeCodeAndValueId(@Param("typeCode") String typeCode,
                                              @Param("valueId") Integer valueId);

    List<DataDictionary> selectByTypeCode(String typeCode);

}
