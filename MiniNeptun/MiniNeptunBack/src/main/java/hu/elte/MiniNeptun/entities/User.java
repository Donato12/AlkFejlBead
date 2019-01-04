package hu.elte.MiniNeptun.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User extends BaseEntity implements Serializable{
    
    @NotNull
    @Column
    private String name;

    @NotNull
    @Column(updatable=false)
    private String username;    
    
    @NotNull
    @Column
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @NotNull
    @Column
    @Enumerated(EnumType.STRING)  
    private Gender gender;
    
    @ManyToMany(targetEntity = Group.class, mappedBy = "members")
    @JsonIgnore
    private List<Group> groups;
    
    @NotNull
    @Column(updatable=false)
    @Enumerated(EnumType.STRING)  
    private Role roleId;

    @NotNull
    @Column
    private boolean enabled;      
    
    public enum Role {
        ROLE_STUDENT,
        ROLE_TEACHER,
        ROLE_ADMIN
    }
    
    public enum Gender {
        GENDER_M,
        GENDER_F
    }
}
