package com.ruoyi.framework.satoken.dao;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import com.ruoyi.common.utils.redis.RedisUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sa-Token(RedisUtils )
 *
 * @author Lion Li
 */
public class PlusSaTokenDao implements SaTokenDao {

    /**
     * Value，
     */
    @Override
    public String get(String key) {
        return RedisUtils.getCacheObject(key);
    }

    /**
     * Value， (: )
     */
    @Override
    public void set(String key, String value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        //
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            RedisUtils.setCacheObject(key, value);
        } else {
            RedisUtils.setCacheObject(key, value, Duration.ofSeconds(timeout));
        }
    }

    /**
     * key-value ()
     */
    @Override
    public void update(String key, String value) {
        long expire = getTimeout(key);
        // -2 =
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.set(key, value, expire);
    }

    /**
     * Value
     */
    @Override
    public void delete(String key) {
        RedisUtils.deleteObject(key);
    }

    /**
     * Value (: )
     */
    @Override
    public long getTimeout(String key) {
        long timeout = RedisUtils.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    /**
     * Value (: )
     */
    @Override
    public void updateTimeout(String key, long timeout) {
        //
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // ，
            } else {
                // ，set
                this.set(key, this.get(key), timeout);
            }
            return;
        }
        RedisUtils.expire(key, Duration.ofSeconds(timeout));
    }


    /**
     * Object，
     */
    @Override
    public Object getObject(String key) {
        return RedisUtils.getCacheObject(key);
    }

    /**
     * Object， (: )
     */
    @Override
    public void setObject(String key, Object object, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        //
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            RedisUtils.setCacheObject(key, object);
        } else {
            RedisUtils.setCacheObject(key, object, Duration.ofSeconds(timeout));
        }
    }

    /**
     * Object ()
     */
    @Override
    public void updateObject(String key, Object object) {
        long expire = getObjectTimeout(key);
        // -2 =
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.setObject(key, object, expire);
    }

    /**
     * Object
     */
    @Override
    public void deleteObject(String key) {
        RedisUtils.deleteObject(key);
    }

    /**
     * Object (: )
     */
    @Override
    public long getObjectTimeout(String key) {
        long timeout = RedisUtils.getTimeToLive(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    /**
     * Object (: )
     */
    @Override
    public void updateObjectTimeout(String key, long timeout) {
        //
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getObjectTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // ，
            } else {
                // ，set
                this.setObject(key, this.getObject(key), timeout);
            }
            return;
        }
        RedisUtils.expire(key, Duration.ofSeconds(timeout));
    }


    /**
     *
     */
    @Override
    public List<String> searchData(String prefix, String keyword, int start, int size, boolean sortType) {
        Collection<String> keys = RedisUtils.keys(prefix + "*" + keyword + "*");
        List<String> list = new ArrayList<>(keys);
        return SaFoxUtil.searchList(list, start, size, sortType);
    }
}
