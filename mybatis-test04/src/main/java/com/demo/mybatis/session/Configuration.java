package com.demo.mybatis.session;

import com.demo.mybatis.binding.MapperMethod;
import com.demo.mybatis.binding.MapperRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.demo.mybatis.session
 *
 * @author Zyy
 * @date 2019/2/2 22:01
 * 读取xml文件到内存中
 */
public class Configuration {
    private InputStream inputStream;
    //mapper的注册中心
    private MapperRegistry mapperRegistry = new MapperRegistry();

    //解析配置文件
    public void loadConfigurations() throws IOException {
        try {
            Document document = new SAXReader().read(inputStream);
            Element root = document.getRootElement();
            List<Element> mappers = root.element("mappers").elements("mapper");
            for (Element mapper : mappers) {
                if (mapper.attribute("resource") != null) {
                    mapperRegistry.setKnownMappers(loadXMLConfiguration(mapper.attribute("resource").getText()));
                }
                if (mapper.attribute("class") != null) {
                }
            }
        } catch (Exception e) {
            System.out.println("读取配置文件失败");
        } finally {
            inputStream.close();
        }

    }
    //加载解析配置文件并将解析的内容放入到map中
    private Map<String, MapperMethod> loadXMLConfiguration(String resource) throws IOException {
        Map<String,MapperMethod> map = new HashMap<String, MapperMethod>();
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
            Document document = new SAXReader().read(inputStream);
            Element root = document.getRootElement();
            if (root.getName().equalsIgnoreCase("mapper")) {
                String namespace = root.attribute("namespace").getText();
                for (Element select : (List<Element>)root.elements("select")) {
                    MapperMethod mapperModel = new MapperMethod();
                    //sql : select * from user;
                    mapperModel.setSql(select.getText().trim());
                    //type : resultType="com.demo.mybatis.pojo.User"
                    mapperModel.setType(Class.forName(select.attribute("resultType").getText()));
                    //namespace+id : com.demo.mybatis.mapper.UserMapper.selectByPrimaryKey
                    map.put(namespace + "." + select.attribute("id").getText(),mapperModel);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return map;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }
}
