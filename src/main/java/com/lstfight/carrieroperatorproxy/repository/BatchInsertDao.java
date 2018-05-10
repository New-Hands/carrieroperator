package com.lstfight.carrieroperatorproxy.repository;

import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import java.util.List;
import java.util.Map;

/**
 *
 * <p>实现接口的细节，如果接口有父类的东西，实现接口可以不实现</p>
 * <P>使用反射自动获取对象属性</P>
 *
 * @author lst
 */
public class BatchInsertDao extends MapperTemplate {

    public BatchInsertDao(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String listBatchInsert(List<Object> list) {
        //第二个花括号是代码块，代码块内在对象初始化时就可以被执行
        return new SQL() {{
            INSERT_INTO("");
        }}.toString();
    }

    public String mapBatchInsert(Map map) {
        return new SQL() {{

        }}.toString();
    }

}
