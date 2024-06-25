package com.xkyii.spry.resource;

import com.xkyii.spry.dto.dept.DeptDto;
import com.xkyii.spry.entity.Dept;
import com.xkyii.spry.entity.User;
import com.xkyii.spry.mapper.DeptMapper;
import com.xkyii.spry.repository.DeptRepository;
import com.xkyss.core.lang.tree.Tree;
import com.xkyss.core.lang.tree.TreeBuilder;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import static com.xkyii.spry.error.ErrorCode.用户不存在;
import static com.xkyii.spry.error.ErrorCode.部门不存在;

@Path("/api/dept")
public class DeptResource {

    @Inject
    DeptRepository deptRepository;

    @Inject
    DeptMapper deptMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Dept patch(@PathParam("id") Long id, Dept inDept) {
        Dept dbDept = deptRepository.findById(id);
        if (dbDept == null) {
            throw new RestException(部门不存在);
        }

        deptMapper.update(dbDept, inDept);
        deptRepository.persist(dbDept);
        return dbDept;
    }

    @GET
    @Path("{id}/tree")
    public DeptDto getTree(@PathParam("id") Long id) {
        // 查询部门列表
        List<Dept> depts = deptRepository.findTree(id);
        if (depts.isEmpty()) {
            return null;
        }

        // 构建树
        TreeBuilder<Long, Dept> builder = new TreeBuilder<>();
        builder.setIdGetter(Dept::getId);
        builder.setPidGetter(Dept::getParentId);
        builder.add(depts);
        Tree<Long, Dept> tree = builder.build();

        // 转换为DTO
        return parse(tree);
    }

    private DeptDto parse(Tree<Long, Dept> tree) {
        if (tree == null) {
            return null;
        }

        DeptDto dto = deptMapper.toDto(tree.getValue());
        List<DeptDto> children = tree.getChildren().stream()
            .map(this::parse)
            .toList();
        dto.setChildren(children);
        return dto;
    }
}
