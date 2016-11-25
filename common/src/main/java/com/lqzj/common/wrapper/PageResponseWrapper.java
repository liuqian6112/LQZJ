package com.lqzj.common.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseWrapper<T> {
    private Integer totalCount;
    private List<T> pageItems;
    private Integer pageSize;
    private Integer pageIndex;

}
