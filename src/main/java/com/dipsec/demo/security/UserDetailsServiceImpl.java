package com.dipsec.demo.security;

import com.dipsec.demo.model.entities.UserCredential;
import com.dipsec.demo.model.entities.UserRole;
import com.dipsec.demo.repositories.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(userCredential)) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        Set<UserRole> userRoles = userCredential.getRoles();
        if (!CollectionUtils.isEmpty(userRoles)) {
            userRoles.stream().map(UserRole::getRole).forEach(role -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
                grantList.add(authority);
            });
        }

        //TODO - need to add roles
        UserDetails userDetails = new User(userCredential.getUsername(), userCredential.getPassword(), grantList);
        return userDetails;
    }
}
