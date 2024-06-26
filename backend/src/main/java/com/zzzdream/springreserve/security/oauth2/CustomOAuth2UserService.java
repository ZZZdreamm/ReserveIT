package com.zzzdream.springreserve.security.oauth2;

import com.zzzdream.springreserve.exception.OAuth2AuthenticationProcessingException;
import com.zzzdream.springreserve.model.auth.AuthProvider;
import com.zzzdream.springreserve.model.auth.Role;
import com.zzzdream.springreserve.model.auth.RoleType;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.mailMessages.MailMessage;
import com.zzzdream.springreserve.repository.RoleRepository;
import com.zzzdream.springreserve.repository.UserRepository;
import com.zzzdream.springreserve.security.UserPrincipal;
import com.zzzdream.springreserve.security.oauth2.user.OAuth2UserInfo;
import com.zzzdream.springreserve.security.oauth2.user.OAuth2UserInfoFactory;
import com.zzzdream.springreserve.util.MailSender;
import com.zzzdream.springreserve.util.MessageUtils;
import com.zzzdream.springreserve.util.RandomCodeGenerator;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;

@Service
public class    CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailSender mailSender;
    public CustomOAuth2UserService(UserRepository userRepository, RoleRepository roleRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mailSender = mailSender;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER);

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user.setRoles(Collections.singletonList(role));

        if(user.getName() == null) {
            user.setName(user.getEmail());
        }else if(user.getEmail() == null) {
            user.setEmail(user.getName());
        }

        user.setEmailVerified(true);
        User result = userRepository.save(user);

//        User result;
//        if(oAuth2UserInfo.getAttributes().get("email_verified") == "true") {
//            user.setEmailVerified(true);
//            result = userRepository.save(user);
//        } else {
//            result = userRepository.save(user);
//            String randomCode = RandomCodeGenerator.generateRandomCode(12);
//            user.setVerificationCode(randomCode);
//            MailMessage mailMessage = MessageUtils.getMailMessage(result, randomCode);
//            mailSender.sendMessage(mailMessage);
//        }

        return result;
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        if(existingUser.getName() == null) {
            existingUser.setName(oAuth2UserInfo.getEmail());
        }
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}
