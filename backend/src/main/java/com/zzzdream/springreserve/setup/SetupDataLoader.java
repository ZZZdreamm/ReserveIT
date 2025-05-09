package com.zzzdream.springreserve.setup;

import com.zzzdream.springreserve.model.auth.*;
import com.zzzdream.springreserve.repository.PrivilegeRepository;
import com.zzzdream.springreserve.repository.RoleRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        PrivilegeType[] privilegeTypes = PrivilegeType.values();
        Privilege[] privileges = new Privilege[privilegeTypes.length];
        for (int i = 0; i < privilegeTypes.length; i++) {
            Privilege privilege = createPrivilegeIfNotFound(privilegeTypes[i]);
            privileges[i] = privilege;
        }

        List<Privilege> userPrivileges = Collections.singletonList(privileges[0]);
        List<Privilege> staffPrivileges = Arrays.asList(privileges[0], privileges[1]);
        List<Privilege> adminPrivileges = Arrays.asList(privileges);
        createRoleIfNotFound(RoleType.ROLE_USER, userPrivileges);
        createRoleIfNotFound(RoleType.ROLE_STAFF, staffPrivileges);
        createRoleIfNotFound(RoleType.ROLE_ADMIN, adminPrivileges);

        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN);
        User admin = userRepository.findByEmail("test@test.com").orElse(null);
        if (admin != null) {
            alreadySetup = true;
            return;
        }
        User user = new User();
        user.setName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Collections.singletonList(adminRole));
        user.setProvider(AuthProvider.local);
        user.setEmailVerified(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(PrivilegeType privilegeType) {

        Privilege privilege = privilegeRepository.findByPrivilegeType(privilegeType);
        if (privilege == null) {
            privilege = new Privilege(privilegeType);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public void createRoleIfNotFound(RoleType roleType, Collection<Privilege> privileges) {
        Role role = roleRepository.findByRoleType(roleType);
        if (role == null) {
            role = new Role(roleType);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
    }
}