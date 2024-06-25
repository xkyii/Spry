package com.xkyii.spry.dto.dept;

import com.xkyii.spry.entity.Dept;

import java.util.List;

public class DeptDto extends Dept {
    private DeptDto parent;
    private List<DeptDto> children;

    public DeptDto getParent() {
        return parent;
    }

    public void setParent(DeptDto parent) {
        this.parent = parent;
    }

    public List<DeptDto> getChildren() {
        return children;
    }

    public void setChildren(List<DeptDto> children) {
        this.children = children;
    }
}
