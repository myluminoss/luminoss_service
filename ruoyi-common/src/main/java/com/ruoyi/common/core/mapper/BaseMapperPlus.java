package com.ruoyi.common.core.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.ruoyi.common.utils.BeanCopyUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  Mapper ,
 *
 * @param <M> mapper
 * @param <T> table
 * @param <V> vo
 * @author Lion Li
 * @since 2021-05-13
 */
@SuppressWarnings("unchecked")
public interface BaseMapperPlus<M, T, V> extends BaseMapper<T> {

    Log log = LogFactory.getLog(BaseMapperPlus.class);

    default Class<V> currentVoClass() {
        return (Class<V>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseMapperPlus.class, 2);
    }

    default Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseMapperPlus.class, 1);
    }

    default Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseMapperPlus.class, 0);
    }

    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }

    /**
     *
     */
    default boolean insertBatch(Collection<T> entityList) {
        return Db.saveBatch(entityList);
    }

    /**
     *
     */
    default boolean updateBatchById(Collection<T> entityList) {
        return Db.updateBatchById(entityList);
    }

    /**
     *
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList) {
        return Db.saveOrUpdateBatch(entityList);
    }

    /**
     * ()
     */
    default boolean insertBatch(Collection<T> entityList, int batchSize) {
        return Db.saveBatch(entityList, batchSize);
    }

    /**
     * ()
     */
    default boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return Db.updateBatchById(entityList, batchSize);
    }

    /**
     * ()
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return Db.saveOrUpdateBatch(entityList, batchSize);
    }

    /**
     * ()
     */
    default boolean insertOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }

    default V selectVoById(Serializable id) {
        return selectVoById(id, this.currentVoClass());
    }

    /**
     *  ID
     */
    default <C> C selectVoById(Serializable id, Class<C> voClass) {
        T obj = this.selectById(id);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return BeanCopyUtils.copy(obj, voClass);
    }

    default List<V> selectVoBatchIds(Collection<? extends Serializable> idList) {
        return selectVoBatchIds(idList, this.currentVoClass());
    }

    /**
     * （ID ）
     */
    default <C> List<C> selectVoBatchIds(Collection<? extends Serializable> idList, Class<C> voClass) {
        List<T> list = this.selectBatchIds(idList);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanCopyUtils.copyList(list, voClass);
    }

    default List<V> selectVoByMap(Map<String, Object> map) {
        return selectVoByMap(map, this.currentVoClass());
    }

    /**
     * （ columnMap ）
     */
    default <C> List<C> selectVoByMap(Map<String, Object> map, Class<C> voClass) {
        List<T> list = this.selectByMap(map);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanCopyUtils.copyList(list, voClass);
    }

    default V selectVoOne(Wrapper<T> wrapper) {
        return selectVoOne(wrapper, this.currentVoClass());
    }

    /**
     *  entity ，
     */
    default <C> C selectVoOne(Wrapper<T> wrapper, Class<C> voClass) {
        T obj = this.selectOne(wrapper);
        if (ObjectUtil.isNull(obj)) {
            return null;
        }
        return BeanCopyUtils.copy(obj, voClass);
    }

    default List<V> selectVoList(Wrapper<T> wrapper) {
        return selectVoList(wrapper, this.currentVoClass());
    }

    /**
     *  entity ，
     */
    default <C> List<C> selectVoList(Wrapper<T> wrapper, Class<C> voClass) {
        List<T> list = this.selectList(wrapper);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return BeanCopyUtils.copyList(list, voClass);
    }

    default <P extends IPage<V>> P selectVoPage(IPage<T> page, Wrapper<T> wrapper) {
        return selectVoPage(page, wrapper, this.currentVoClass());
    }

    /**
     * VO
     */
    default <C, P extends IPage<C>> P selectVoPage(IPage<T> page, Wrapper<T> wrapper, Class<C> voClass) {
        List<T> list = this.selectList(page, wrapper);
        IPage<C> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        if (CollUtil.isEmpty(list)) {
            return (P) voPage;
        }
        voPage.setRecords(BeanCopyUtils.copyList(list, voClass));
        return (P) voPage;
    }

}
