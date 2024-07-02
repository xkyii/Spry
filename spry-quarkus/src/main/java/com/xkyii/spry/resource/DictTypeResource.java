package com.xkyii.spry.resource;

import com.xkyii.spry.entity.DictType;
import com.xkyii.spry.mapper.DictTypeMapper;
import com.xkyii.spry.repository.DictTypeRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import static com.xkyii.spry.error.ErrorCode.角色不存在;


@Path("/api/dict-type")
public class DictTypeResource {

    @Inject
    DictTypeRepository dictTypeRepository;

    @Inject
    DictTypeMapper dictTypeMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DictType patch(@PathParam("id") Long id, DictType inDictType) {
        DictType dbDictType = dictTypeRepository.findById(id);
        if (dbDictType == null) {
            throw new RestException(角色不存在);
        }

        dictTypeMapper.update(dbDictType, inDictType);
        dictTypeRepository.persist(dbDictType);
        return dbDictType;
    }
}
