package com.ruoyi.framework.manager;

import cn.hutool.core.util.ReflectUtil;
import com.ruoyi.common.annotation.EncryptField;
import com.ruoyi.common.encrypt.EncryptContext;
import com.ruoyi.common.encrypt.IEncryptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 *
 * @author
 * @version 4.6.0
 */
@Slf4j
public class EncryptorManager {

    /**
     *
     */
    Map<EncryptContext, IEncryptor> encryptorMap = new ConcurrentHashMap<>();

    /**
     *
     */
    Map<Class<?>, Set<Field>> fieldCache = new ConcurrentHashMap<>();

    /**
     *
     */
    public Set<Field> getFieldCache(Class<?> sourceClazz) {
        return fieldCache.computeIfAbsent(sourceClazz, clazz -> {
            Field[] declaredFields = clazz.getDeclaredFields();
            Set<Field> fieldSet = Arrays.stream(declaredFields).filter(field ->
                    field.isAnnotationPresent(EncryptField.class) && field.getType() == String.class)
                .collect(Collectors.toSet());
            for (Field field : fieldSet) {
                field.setAccessible(true);
            }
            return fieldSet;
        });
    }

    /**
     *
     *
     * @param encryptContext
     */
    public IEncryptor registAndGetEncryptor(EncryptContext encryptContext) {
        if (encryptorMap.containsKey(encryptContext)) {
            return encryptorMap.get(encryptContext);
        }
        IEncryptor encryptor = ReflectUtil.newInstance(encryptContext.getAlgorithm().getClazz(), encryptContext);
        encryptorMap.put(encryptContext, encryptor);
        return encryptor;
    }

    /**
     *
     *
     * @param encryptContext
     */
    public void removeEncryptor(EncryptContext encryptContext) {
        this.encryptorMap.remove(encryptContext);
    }

    /**
     * ..
     *
     * @param value
     * @param encryptContext
     */
    public String encrypt(String value, EncryptContext encryptContext) {
        IEncryptor encryptor = this.registAndGetEncryptor(encryptContext);
        return encryptor.encrypt(value, encryptContext.getEncode());
    }

    /**
     *
     *
     * @param value
     * @param encryptContext
     */
    public String decrypt(String value, EncryptContext encryptContext) {
        IEncryptor encryptor = this.registAndGetEncryptor(encryptContext);
        return encryptor.decrypt(value);
    }

}
