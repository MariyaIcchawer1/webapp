package com.example.Webappex.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> roles = null;

        return new User("user","xyz",new ArrayList<>());



       /* if(username.equals("admin"))
        {
            roles=Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("admin","xyz",roles);
        }
        if(username.equals("user"))
        {
            roles=Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("user","xyz",roles);
        }
        throw new UsernameNotFoundException("User not found with the name "+ username);*/
    }

}
