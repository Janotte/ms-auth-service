package br.net.supptech.authservice.models;

import br.net.supptech.authservice.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;
    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 80)
    private String fullName;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @JsonIgnore
    @Column(nullable = false, length = 50)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserStatus userStatus;
    @ManyToMany
    @JoinTable(name = "TB_USERS_ROLES", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<RoleModel> roles = new HashSet<>();
    @Column(length = 20)
    private String cellPhoneNumber;
    @Column
    private String imageUrl;
    @CreationTimestamp
    private OffsetDateTime createdDate;
    @UpdateTimestamp
    private OffsetDateTime updatedDate;
}
