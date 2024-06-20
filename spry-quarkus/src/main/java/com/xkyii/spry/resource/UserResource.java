package com.xkyii.spry.resource;

import com.xkyii.spry.entity.User;
import com.xkyii.spry.mapper.UserMapper;
import com.xkyii.spry.repository.UserRepository;
import com.xkyss.quarkus.rest.error.RestException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

import static com.xkyii.spry.error.ErrorCode.用户不存在;

@Path("/api/user")
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Transactional
    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User patch(@PathParam("id") Long id, User inUser) {
        User dbUser = userRepository.findById(id);
        if (dbUser == null) {
            throw new RestException(用户不存在);
        }

        userMapper.update(dbUser, inUser);
        userRepository.persist(dbUser);
        return dbUser;
    }

    @GET
    @Path("{id}/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> queryRoleCodes(@PathParam("id") Long userId) {
        return userRepository.queryRoleCodes(userId);
    }

}
