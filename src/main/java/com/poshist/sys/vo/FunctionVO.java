package com.poshist.sys.vo;

import com.poshist.sys.entity.Function;

import java.util.ArrayList;
import java.util.List;

public class FunctionVO {
    private String name;
    private String url;
    private String memo;
    private String status;
    private Long id;
    private List<FunctionVO> childFunction = new ArrayList<FunctionVO>();

    public  FunctionVO(Function function) {
        this.name = function.getName();
        this.url = function.getUrl();
        this.memo = function.getMemo();
        this.id = function.getId();
    }

    public void addChild(FunctionVO functionVO) {
        childFunction.add(functionVO);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FunctionVO> getChildFunction() {
        return childFunction;
    }

    public void setChildFunction(List<FunctionVO> childFunction) {
        this.childFunction = childFunction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
