package com.demo.mybatis.binding;

import java.util.HashMap;
import java.util.Map;

/**
 * com.demo.mybatis.binding
 *
 * @author Zyy
 * @date 2019/2/2 22:07
 */
public class MapperRegistry {

    private Map<String, MapperMethod> knownMappers = new HashMap<String, MapperMethod>();

    public Map<String, MapperMethod> getKnownMappers() {
        return knownMappers;
    }

    public void setKnownMappers(Map<String, MapperMethod> knownMappers) {
        this.knownMappers = knownMappers;
    }
}
