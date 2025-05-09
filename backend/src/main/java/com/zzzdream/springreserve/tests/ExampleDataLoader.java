package com.zzzdream.springreserve.tests;

import com.zzzdream.springreserve.model.auth.AuthProvider;
import com.zzzdream.springreserve.model.auth.RoleType;
import com.zzzdream.springreserve.model.services.Servicee;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.services.Subject;
import com.zzzdream.springreserve.repository.RoleRepository;
import com.zzzdream.springreserve.repository.ServiceRepository;
import com.zzzdream.springreserve.repository.SubjectRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ExampleDataLoader {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    public ExampleDataLoader(UserRepository userRepository, ServiceRepository serviceRepository, RoleRepository roleRepository, SubjectRepository subjectRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.roleRepository = roleRepository;
        this.subjectRepository = subjectRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void populateExampleData() {
        populateExampleUsers();
        populateExampleServicesAndSubjects();
    }

    public void populateExampleUsers(){
        List<User> userList = getUserList("user");
        for (User user : userList) {
            user.setRoles(Collections.singletonList(roleRepository.findByRoleType(RoleType.ROLE_USER)));
        }
        userRepository.saveAll(userList);

        List<User> staffUserList = getUserList("staff");
        for (User user : staffUserList) {
            user.setRoles(Collections.singletonList(roleRepository.findByRoleType(RoleType.ROLE_STAFF)));
        }
        userRepository.saveAll(staffUserList);
    }

    private List<User> getUserList(String roleType) {
        User[] users = {
                new User(roleType + "1", roleType + "1@gmail.com", passwordEncoder.encode(roleType), AuthProvider.local),
                new User(roleType + "2", roleType + "2@gmail.com", passwordEncoder.encode(roleType), AuthProvider.local),
                new User(roleType + "3", roleType + "3@gmail.com", passwordEncoder.encode(roleType), AuthProvider.local),
                new User(roleType + "4", roleType + "4@gmail.com", passwordEncoder.encode(roleType), AuthProvider.local),
                new User(roleType + "5", roleType + "5@gmail.com", passwordEncoder.encode(roleType), AuthProvider.local),
        };
        return List.of(users);
    }

    public void populateExampleServicesAndSubjects(){
        Servicee[] services = {
                new Servicee("TestService1", 100.0 ,"service1 description" ),
                new Servicee("TestService2", 500.0 ,"service2 description" ),
                new Servicee("TestService3", 10.0 ,"service3 description" ),
                new Servicee("TestService4", 120.0 ,"service4 description" ),
        };
        serviceRepository.saveAll(List.of(services));

        Subject[] subjects = {
                new Subject("TestSubject1" ),
                new Subject("TestSubject2" ),
                new Subject("TestSubject3" ),
                new Subject("TestSubject4" ),
        };
        for (Subject subject : subjects) {
            subject.setServices(List.of(services));
        }
        subjectRepository.saveAll(List.of(subjects));

    }


}
