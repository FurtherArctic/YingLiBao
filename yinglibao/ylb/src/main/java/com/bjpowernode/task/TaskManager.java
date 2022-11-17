package com.bjpowernode.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务处理类
 *
 * @author wangjunchen
 */
@Component
public class TaskManager {
    /**
     * 测试定时任务，定时任务的方法遵循以下规则：
     * 1. public方法
     * 2. 没有返回值
     * 3. 没有参数
     * 4. 方法上面加上注解，添加cron表达式，表示定时规则
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void addTask() {
        System.out.println("定时任务执行了：" + new Date());
    }

}
