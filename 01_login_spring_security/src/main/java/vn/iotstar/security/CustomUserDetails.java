package vn.iotstar.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.iotstar.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Custom UserDetails that exposes fullName, email, and roleName
 * so that header.html can display user info without extra DB queries.
 */
public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final String fullName;
    private final String roleName;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.roleName = user.getRole().getName();
        this.enabled = user.isEnabled();
        // Spring Security roles with hasRole() expect "ROLE_" prefix stripped.
        // DB stores e.g. "USER" or "ADMIN" — Spring's roles() helper adds "ROLE_" prefix.
        // We store "USER"/"ADMIN" in DB and use ROLE_ prefix in GrantedAuthority.
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleName() {
        return roleName;
    }

    // UserDetails interface

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /** Spring Security uses getUsername() as the principal name (shown by sec:authentication="name") */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
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
}
