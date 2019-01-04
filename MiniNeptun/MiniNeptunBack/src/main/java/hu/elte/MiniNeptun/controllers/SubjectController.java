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
@RequestMapping("subjects")
@RestController
public class SubjectController extends BaseController<Subject, SubjectRepository> {

    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired 
    private UserRepository userRepository;
    
    @GetMapping("/{id}")
    public ResponseEntity<Subject> get(@PathVariable Long id) {
        Optional<Subject> oSubj = subjectRepository.findById(id);
        if (oSubj.isPresent()) {
            return ResponseEntity.ok(oSubj.get());
        } 
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/groups")
    public ResponseEntity<Iterable<Group>> getGroups(@PathVariable Long id) {
        Optional<Subject> oSubj = subjectRepository.findById(id);
        if (oSubj.isPresent()) {
            return ResponseEntity.ok(oSubj.get().getGroups());
        } 
        return ResponseEntity.notFound().build();
    }    

    @GetMapping("")
    public ResponseEntity<Iterable<Subject>> getAll() {
        return new ResponseEntity(subjectRepository.findAll(), HttpStatus.OK);
    }     
    
    @PostMapping("")
    @Secured({"ROLE_ADMIN"})    
    public ResponseEntity createNew(@RequestBody Subject s) {
        subjectRepository.save(s);
        return ResponseEntity.ok().build();
    }       
    
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Subject> oSubj = subjectRepository.findById(id);
        if (oSubj.isPresent()) {
            for(Group g : groupRepository.findAllBySubject(oSubj.get())) {
                for(User u : g.getMembers()) {
                    u.getGroups().remove(g);
                    userRepository.save(u);
                }
                groupRepository.delete(g);
            }
            subjectRepository.delete(oSubj.get());
            groupRepository.deleteAll(groupRepository.findAllBySubject(oSubj.get()));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity update(@PathVariable Long id, @RequestBody Subject s) {
        Optional<Subject> oSubj = subjectRepository.findById(id);
        if (oSubj.isPresent()) {
            s.setId(id);
            subjectRepository.save(s);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }       
}