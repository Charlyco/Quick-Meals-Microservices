package com.quickmeals.authservice.entities;

import com.quickmeals.authservice.customtypes.AccountStatus;
import com.quickmeals.authservice.customtypes.Location;
import com.quickmeals.authservice.customtypes.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer userId;

    @Getter
    @Column(unique = true)
    private String userName;

    private String password;

    @Getter
    private String fullName;

    @Getter
    @Column(unique = true)
    private String email;

    @Getter
    @Column(unique = true)
    private String phoneNumber;

    @Getter
    private String address;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Getter
    private Location currentLocation;

    public User(String userName,
                String password,
                String fullName,
                String email,
                String phoneNumber,
                String address,
                Role role,
                Location currentLocation) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.currentLocation = currentLocation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(fullName, user.fullName) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(address, user.address) && role == user.role && Objects.equals(currentLocation, user.currentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, fullName, email, phoneNumber, address, role, currentLocation);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", currentLocation=" + currentLocation +
                '}';
    }
}
