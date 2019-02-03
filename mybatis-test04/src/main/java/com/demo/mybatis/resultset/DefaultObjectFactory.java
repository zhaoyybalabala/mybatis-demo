package com.demo.mybatis.resultset;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * com.demo.mybatis.resultset
 *
 * @author Zyy
 * @date 2019/2/3 18:05
 *  直接复制了mybatis里面的
 *  mybatis ：org.apache.ibatis.reflection.factory
 */
public class DefaultObjectFactory implements ObjectFactory, Serializable {

    private static final long serialVersionUID = -8855120656740914948L;

    public DefaultObjectFactory() {
    }

    public <T> T create(Class<T> type) throws Exception {
        return (T) this.create(type, (List)null, (List)null);
    }

    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) throws Exception {
        Class<?> classToCreate = this.resolveInterface(type);
        return (T) this.instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
    }

    public void setProperties(Properties properties) {
    }

    <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) throws Exception {
        try {
            Constructor constructor;
            if (constructorArgTypes != null && constructorArgs != null) {
                constructor = type.getDeclaredConstructor((Class[])constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }

                return (T) constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
            } else {
                constructor = type.getDeclaredConstructor();
                if (!constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }

                return (T) constructor.newInstance();
            }
        } catch (Exception var9) {
            StringBuilder argTypes = new StringBuilder();
            if (constructorArgTypes != null && !constructorArgTypes.isEmpty()) {
                Iterator var6 = constructorArgTypes.iterator();

                while(var6.hasNext()) {
                    Class<?> argType = (Class)var6.next();
                    argTypes.append(argType.getSimpleName());
                    argTypes.append(",");
                }

                argTypes.deleteCharAt(argTypes.length() - 1);
            }

            StringBuilder argValues = new StringBuilder();
            if (constructorArgs != null && !constructorArgs.isEmpty()) {
                Iterator var11 = constructorArgs.iterator();

                while(var11.hasNext()) {
                    Object argValue = var11.next();
                    argValues.append(String.valueOf(argValue));
                    argValues.append(",");
                }

                argValues.deleteCharAt(argValues.length() - 1);
            }

            throw new Exception("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + var9, var9);
        }
    }

    protected Class<?> resolveInterface(Class<?> type) {
        Class classToCreate;
        if (type != List.class && type != Collection.class && type != Iterable.class) {
            if (type == Map.class) {
                classToCreate = HashMap.class;
            } else if (type == SortedSet.class) {
                classToCreate = TreeSet.class;
            } else if (type == Set.class) {
                classToCreate = HashSet.class;
            } else {
                classToCreate = type;
            }
        } else {
            classToCreate = ArrayList.class;
        }

        return classToCreate;
    }

    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }
}
