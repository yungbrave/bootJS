package ru.herewego.bootJS.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String role;

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role.replace("ROLE_", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id && Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
