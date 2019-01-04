package hu.elte.MiniNeptun.controllers;

import hu.elte.MiniNeptun.entities.BaseEntity;
import hu.elte.MiniNeptun.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController
        <T extends BaseEntity, 
         R extends CrudRepository<T, Long>> {  
    
    @Autowired 
    protected AuthenticatedUser authenticatedUser;    
}
