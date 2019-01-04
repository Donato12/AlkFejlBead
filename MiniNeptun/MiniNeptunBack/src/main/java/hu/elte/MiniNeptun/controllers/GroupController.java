package hu.elte.MiniNeptun.controllers;

import hu.elte.MiniNeptun.entities.Group;
import hu.elte.MiniNeptun.entities.Subject;
import hu.elte.MiniNeptun.entities.User;
import hu.elte.MiniNeptun.repositories.GroupRepository;
import hu.elte.MiniNeptun.repositories.SubjectRepository;
import hu.elte.MiniNeptun.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("groups")
@RestController
public class GroupController extends BaseController<Group, GroupRepository> {

    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<Group> get(@PathVariable Long id) {
        Optional<Group> oGroup = groupRepository.findById(id);
        if (oGroup.isPresent()) {
            return ResponseEntity.ok(oGroup.get());
            
        } 
        return ResponseEntity.notFound().build();
    } 
    
    @GetMapping("/{id}/members")
    @Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<User>> getStudents(@PathVariable Long id) {
        Optional<Group> oGroup = groupRepository.findById(id);
        if (oGroup.isPresent()) {
            return new ResponseEntity(oGroup.get().getMembers(),HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }    
   
    @PostMapping("/subject/{id}")
    @Secured({"ROLE_TEACHER","ROLE_ADMIN"})
    public ResponseEntity createNew(@PathVariable Long id, @RequestBody Group g) {
        User user = authenticatedUser.getUser();
        Optional<Subject> oSubj = subjectRepository.findById(id);
        if (oSubj.isPresent()) {
            if(user.getRoleId() != User.Role.ROLE_ADMIN) { 
                g.setTeacherName(user.getName());
            }
            g.setSubject(oSubj.get());
            groupRepository.save(g);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }       
    
    @DeleteMapping("/{id}")
    @Secured({"ROLE_TEACHER","ROLE_ADMIN"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Group> oGroup = groupRepository.findById(id);
        if (oGroup.isPresent()) {
            for(User u : oGroup.get().getMembers()) {
                u.getGroups().remove(oGroup.get());
                userRepository.save(u);           
            }
            groupRepository.delete(oGroup.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_TEACHER", "ROLE_ADMIN"})
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody Group g) {
        User user = authenticatedUser.getUser();
        Optional<Group> oGroup = groupRepository.findById(id);
        if (oGroup.isPresent()) {
            if(user.getRoleId() != User.Role.ROLE_ADMIN) {
                if(!user.getName().equals(oGroup.get().getTeacherName())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
                g.setTeacherName(user.getName());    
            }
            g.setId(id);
            g.setSubject(oGroup.get().getSubject());
            g.setMembers(oGroup.get().getMembers());
            groupRepository.save(g);
            return ResponseEntity.ok().build();   
        }
        return ResponseEntity.notFound().build();
    }     
}