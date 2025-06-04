package com.starter.mugisha.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.starter.mugisha.enums.EGender;
import com.starter.mugisha.enums.EStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users",uniqueConstraints = {@UniqueConstraint(columnNames = {"mobile"}),@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private EStatus status = EStatus.ACTIVE;
    @JsonManagedReference(value = "user-roles")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name =  "user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();
    @JsonManagedReference(value = "user-purchases")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Purchased> purchases;
    @JsonIgnore
    private String password;
    public User(String email,String firstName,String lastName,String mobile,EGender gender,String password){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.mobile = mobile;
        this.gender = gender;
        this.password=password;
    }
}
