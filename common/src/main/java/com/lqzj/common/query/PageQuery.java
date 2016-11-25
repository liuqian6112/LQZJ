package com.lqzj.common.query;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PageQuery {

    @NotNull(message = "pageSize不能为空")
    @Min(value = 1, message = "pageSize必须大于等于1")
    private Integer pageSize;

    @NotNull(message = "pageIndex不能为空")
    @Min(value = 0, message = "pageIndex必须大于等于0")
    private Integer pageIndex;

    private Integer pageStart;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageStart() {
        return pageIndex * pageSize;
    }

}
