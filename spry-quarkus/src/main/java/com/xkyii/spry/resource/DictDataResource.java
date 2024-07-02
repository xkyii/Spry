package com.xkyii.spry.resource;

import com.xkyii.spry.entity.DictData;
import com.xkyii.spry.mapper.DictDataMapper;
import com.xkyii.spry.repository.DictDataRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import static com.xkyii.spry.error.ErrorCode.角色不存在;


@Path("/api/dict-data")
public class DictDataResource {

    @Inject
    DictDataRepository dictDataRepository;

    @Inject
    DictDataMapper dictDataMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DictData patch(@PathParam("id") Long id, DictData inDictData) {
        DictData dbDictData = dictDataRepository.findById(id);
        if (dbDictData == null) {
            throw new RestException(角色不存在);
        }

        dictDataMapper.update(dbDictData, inDictData);
        dictDataRepository.persist(dbDictData);
        return dbDictData;
    }
}
