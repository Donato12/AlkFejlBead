package hu.elte.MiniNeptun.controllers;

import hu.elte.MiniNeptun.entities.Group;
import hu.elte.MiniNeptun.entities.User;
import hu.elte.MiniNeptun.repositories.GroupRepository;
import hu.elte.MiniNeptun.repositories.UserRepository;
import java.security.SecureRandom;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("users")
@RestController
public class UserController extends BaseController<User, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Secured({"ROLE_ADMIN"})
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println(user);
        user.setUsername(generateUsername());   
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        user.setEnabled(true);
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    private String generateUsername() {
        SecureRandom rnd = new SecureRandom();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int unamelength = 8;
        StringBuilder sb;
        Optional<User> oUser;
        do{
            sb = new StringBuilder(unamelength);
            for( int i = 0; i < unamelength; i++ ) 
               sb.append( chars.charAt( rnd.nextInt(chars.length()) ) );               
            oUser = userRepository.findByUsername(sb.toString());
        } while(oUser.isPresent());
        return sb.toString();
    }

    @PostMapping("login")
    public ResponseEntity<User> login() {
        User user = authenticatedUser.getUser();                
        return (ResponseEntity<User>)ResponseEntity.ok(user);   
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User user = authenticatedUser.getUser();
        if(user.getRoleId() != User.Role.ROLE_ADMIN) {
            if(user.getId() != id) {
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }       
        }
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return (ResponseEntity<User>)ResponseEntity.ok(oUser.get());
        } 
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity delete(@PathVariable Long id) {    
        User user = authenticatedUser.getUser();
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            if(oUser.get().getRoleId() == User.Role.ROLE_ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            oUser.get().setEnabled(false);
            for(Group g : oUser.get().getGroups()) {
                g.getMembers().remove(oUser.get());
                groupRepository.save(g);
            }
            oUser.get().getGroups().clear();
            return ResponseEntity.ok(userRepository.save(oUser.get()));
        }
        return ResponseEntity.notFound().build();
    }    
    
    /***
     * Valid RequestBody is handled on the frontend to avoid excessive null checking.
     * They don't exist due to NOTNULL, but what about requests?  
     */    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User u) {
        User user = authenticatedUser.getUser();
        if(user.getId() != id) {
            if(u.getRoleId() == User.Role.ROLE_ADMIN || user.getRoleId() != User.Role.ROLE_ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }           
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            oUser.get().setName(u.getName());
            oUser.get().setGender(u.getGender());
            oUser.get().setPassword(passwordEncoder.encode(u.getPassword())); 
            return ResponseEntity.ok(userRepository.save(oUser.get()));
        }
        return ResponseEntity.notFound().build();
    }    
    
    @GetMapping("/{id}/groups")
    @Secured({"ROLE_STUDENT","ROLE_ADMIN"})
    public ResponseEntity<Iterable<Group>> getGroups(@PathVariable Long id) {
        User user = authenticatedUser.getUser();
        if(user.getRoleId() != User.Role.ROLE_ADMIN) {
            if(user.getId() != id) {
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }       
        }
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get().getGroups());
        } 
        return ResponseEntity.notFound().build();
    }    
    
    @PostMapping("/{id}/groups")
    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    public ResponseEntity addToGroup(@PathVariable Long id, @RequestBody Group g) {
        System.out.println("QUERY");
        User user = authenticatedUser.getUser();
        if (user.getRoleId() != User.Role.ROLE_ADMIN) {
            if( user.getId() != id) {
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }       
        }
        Optional<Group> oGroup = groupRepository.findById(g.getId());
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            if (oGroup.isPresent()) {
               if(oGroup.get().isFull()) {
                   return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
               } 
               if(oUser.get().getGroups().contains(oGroup.get())) {
                   return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
               }
               oUser.get().getGroups().add(oGroup.get());
               oGroup.get().getMembers().add(oUser.get());
               userRepository.save(oUser.get()); 
               groupRepository.save(oGroup.get());
               return ResponseEntity.ok().build();       
            }

        } 
        return ResponseEntity.notFound().build();
    }    
    
    @DeleteMapping("/{id}/groups")
    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})    
    public ResponseEntity deleteFromGroup(@PathVariable Long id, @RequestBody Group g) {
        User user = authenticatedUser.getUser();
        if(user.getRoleId() != User.Role.ROLE_ADMIN) {
            if(user.getId() != id) {
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }       
        }
        Optional<Group> oGroup = groupRepository.findById(g.getId());
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            if(!oUser.get().getGroups().contains(oGroup.get())) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
            oUser.get().getGroups().remove(oGroup.get());
            oGroup.get().getMembers().remove(oUser.get());
            userRepository.save(oUser.get());
            groupRepository.save(oGroup.get());
            return ResponseEntity.ok().build();
        } 
        return ResponseEntity.notFound().build();
    }      
}