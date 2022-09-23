//package peaksoft.model;
//
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class User implements UserDetails {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
//    private Long id;
//    @Column(nullable = false)
//    private String name;
//    @Column(unique = true)
//    private String password;
//    private String email;
//    @CreatedDate
//    private LocalDateTime created;
//    private boolean isActive = true;
//
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Role role : roles) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}