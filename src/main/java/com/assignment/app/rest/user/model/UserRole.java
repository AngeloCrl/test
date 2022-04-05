package com.assignment.app.rest.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user_roles")
@JsonIgnoreProperties({"userId"})
public class UserRole  implements GrantedAuthority {

    public UserRole(RoleType roleType) {
        this.role = roleType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private RoleType role;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getAuthority() {
        return role.name();
    }

}
