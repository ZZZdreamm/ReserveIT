package com.zzzdream.springreserve.security;


import com.zzzdream.springreserve.exception.ResourceNotFoundException;
import com.zzzdream.springreserve.exception.UserNotExistingException;
import com.zzzdream.springreserve.exception.UserNotVerifiedException;
import com.zzzdream.springreserve.model.auth.Privilege;
import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getRoles());
        return UserPrincipal.create(user, authorities);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UserNotExistingException("id" , String.valueOf(id))
        );
        if(!user.getEmailVerified()){
            throw new UserNotVerifiedException("id", String.valueOf(id));
        }
        Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getRoles());
        return UserPrincipal.create(user, authorities);
    }


    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getRoleType().name());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getPrivilegeType().name());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}