package hu.elte.MiniNeptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Group extends BaseEntity implements Serializable{
    
    @NotNull                   
    @Column                   
    private String teacherName;
    
    @NotNull
    @Column
    private int studentLimit; 
    
    @ManyToOne(targetEntity = Subject.class)
    @JoinColumn
    private Subject subject;
   
    @NotNull
    @Column
    private String location; 
    
    @NotNull
    @Column
    private String timeframe; 
   
    @JsonIgnore
    @ManyToMany(targetEntity = User.class)
    @JoinTable
    private List<User> members;
    
    @Column
    private String comment;
    
    public int getMemberCount() {
        return members.size();
    }
    
    @JsonIgnore
    public boolean isFull() {
        return this.members.size() == this.studentLimit;
    }
}
