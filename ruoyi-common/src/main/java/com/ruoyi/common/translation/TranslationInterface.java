package com.ruoyi.common.translation;

/**
 *  ( {@link com.ruoyi.common.annotation.TranslationType} )
 *
 * @author Lion Li
 */
public interface TranslationInterface<T> {

    /**
     *
     *
     * @param key ()
     * @return
     */
    T translation(Object key, String other);
}
