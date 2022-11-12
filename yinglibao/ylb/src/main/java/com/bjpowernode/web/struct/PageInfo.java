package com.bjpowernode.web.struct;

import lombok.Data;

/**
 * @author wangjunchen
 */
@Data
public class PageInfo {
    private long pageNo;
    private long pageSize;
    private long totalRecords;
    private long totalPages;

    /**
     * 分页信息
     *
     * @param pageNo       当前页数
     * @param pageSize     每一页的数据量
     * @param totalRecords 总计多少条数据
     */
    public PageInfo(long pageNo, long pageSize, long totalRecords) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;

        //根据总数据量和每一页的数据量计算获得总页数
        if (totalRecords % pageSize == 0) {
            this.totalPages = totalRecords / pageSize;
        } else {
            this.totalPages = totalRecords / pageSize + 1;
        }

    }
}
