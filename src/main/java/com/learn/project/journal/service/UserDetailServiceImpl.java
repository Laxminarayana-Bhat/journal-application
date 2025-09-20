package com.learn.project.journal.service;

import com.learn.project.journal.model.User;
import com.learn.project.journal.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntryRepository.findByUserName(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        //// ❌ WRONG - This converts List to String incorrectly
        //.roles(String.valueOf(user.getRoles())) - its like "[USER,ADMIN]" in one string
        //
        //// ✅ CORRECT - Convert List<String> to String[]
        //.roles(user.getRoles().toArray(new String[0])) - here its [ "USER","ADMIN" ]
        return null;
    }
}
