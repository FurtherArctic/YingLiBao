package com.bjpowernode.common.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

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
}
