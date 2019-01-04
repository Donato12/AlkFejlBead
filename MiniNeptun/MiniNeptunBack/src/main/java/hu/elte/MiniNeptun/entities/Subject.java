package hu.elte.MiniNeptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Subject extends BaseEntity implements Serializable{
    
    @NotNull
    @Column
    private String name;
    
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)      
    private Type type;
    
    @NotNull
    @Column
    private int creditValue;    
    
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)      
    private Method gradeMethod;
    
    @NotNull
    @Column
    private int gradeValue;
    
    @OneToMany(targetEntity = Group.class, mappedBy = "subject")
    @JsonIgnore
    private List<Group> groups;
    
    public enum Type {
        TYPE_EA,
        TYPE_GY,
        TYPE_MIX,
        TYPE_OTHER
    }
    
    public enum Method {
        METHOD_EXAM,
        METHOD_HOMEWORK,    
        METHOD_OTHER
    }    
}
