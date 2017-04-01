package com.mwb.dao.filter;

import com.mwb.controller.api.PagingResult;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class SearchResult <T> {
    private boolean paged;
    private PagingResult pagingResult;
    private List<T> result;

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public PagingResult getPagingResult() {
        return pagingResult;
    }

    public void setPagingResult(PagingResult pagingResult) {
        this.pagingResult = pagingResult;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
