package com.xkyii.spry.resource;

import com.xkyii.spry.entity.Dept;
import com.xkyii.spry.entity.User;
import com.xkyii.spry.mapper.DeptMapper;
import com.xkyii.spry.repository.DeptRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
}
