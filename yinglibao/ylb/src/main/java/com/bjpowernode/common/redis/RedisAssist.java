package com.bjpowernode.common.redis;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author wangjunchen
 */
@Component
public class RedisAssist {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 按照score从高到低来排序，获取start，end之间的成员
     *
     * @param key   zest数据中的key
     * @param start 起始位置（包含在内）
     * @param end   结束位置（不包含在内）
     * @return Set<ZSetOperations.TypedTuple < String>>数据
     */
    public Set<ZSetOperations.TypedTuple<String>> getReverseFromZSet(String key, int start, int end) {
        return stringRedisTemplate.boundZSetOps(key).reverseRangeWithScores(start, end);
    }

    /**
     * 往redis添加String类型的数据
     *
     * @param key    key值
     * @param value  value值
     * @param minute 过期时间
     * @return boolean值，是否添加成功
     */
    public boolean addString(String key, String value, int minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
        Boolean b = stringRedisTemplate.hasKey(key);
        if ((b != null)) {
            return b;
        }
        return false;
    }

    /**
     * 检查key是否存在
     *
     * @param key key
     * @return boolean
     */
    public boolean exists(String key) {
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        //noinspection ReplaceNullCheck
        if (hasKey != null) {
            return hasKey;
        } else {
            return false;
        }
    }

    /**
     * 获取string类型key的value
     *
     * @param key key
     * @return value值
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * redis添加hash类型的数据
     *
     * @param key    key
     * @param data   值，map类型
     * @param minute 有效时间
     * @return boolean
     */
    public boolean addHash(String key, Map<String, String> data, int minute) {
        boolean result = false;
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps(key);
        hashOps.putAll(data);
        Boolean expire = hashOps.expire(minute, TimeUnit.MINUTES);
        if (expire != null) {
            result = expire;
        }
        return result;
    }

    /**
     * 从redis数据库中获取对应的field中的value值
     *
     * @param key   hash的key
     * @param field field
     * @return field的value
     */
    public String getFieldHash(String key, String field) {
        Object o = stringRedisTemplate.opsForHash().get(key, field);
        if (o != null) {
            return o.toString();
        }
        return null;
    }
}
