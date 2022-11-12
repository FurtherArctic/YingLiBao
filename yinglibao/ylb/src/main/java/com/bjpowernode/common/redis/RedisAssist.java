package com.bjpowernode.common.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
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

    public boolean addString(String key, String value, int minute) {
        stringRedisTemplate.opsForValue().set(key, value, minute, TimeUnit.MINUTES);
        Boolean b = stringRedisTemplate.hasKey(key);
        if ((b != null)) {
            return b.booleanValue();
        }
        return false;
    }

    /**
     * 检查key是否存在
     */
    public boolean exists(String key) {
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey != null) {
            return hasKey.booleanValue();
        } else {
            return false;
        }
    }

    /**
     * 获取string类型key的值
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
