package com.bjpowernode.web.struct;

import com.bjpowernode.common.consts.AppConsts;
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

    public PageInfo(long pageNo, long pageSize, long totalRecords) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        if (totalRecords % pageSize == 0) {
            this.totalPages = totalRecords / pageSize;
        } else {
            this.totalPages = totalRecords / pageSize + 1;
        }

    }
}
