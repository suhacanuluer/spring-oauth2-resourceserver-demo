package com.suhacan.springoauth2resourceserverdemo.service.impl;

import com.suhacan.springoauth2resourceserverdemo.model.User;
import com.suhacan.springoauth2resourceserverdemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    public CustomOidcUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        System.out.println(userRequest.getIdToken().getIssuer());

        String email = oidcUser.getAttribute("email");
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setEmail(email);
        }

        user.setName(oidcUser.getAttribute("name"));
        user.setPicture(oidcUser.getAttribute("picture"));

        userRepository.save(user);

        return oidcUser;
    }

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String email = oAuth2User.getAttribute("email");
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        User user;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//        } else {
//            user = new User();
//            user.setEmail(email);
//        }
//
//        user.setName(oAuth2User.getAttribute("name"));
//        user.setPicture(oAuth2User.getAttribute("picture"));
//
//        userRepository.save(user);
//
//        return oAuth2User;
//    }
}
