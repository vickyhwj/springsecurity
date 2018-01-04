package com.erdangjiade.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import db.Db;

public class MyUserDetailService implements UserDetailsService {

    //登陆验证时，通过username获取用户的所有权限信息，
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
   /* @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException, DataAccessException {
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();

        final GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_NO");
        final GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER");

        if(username.equals("lcy")){
            auths=new ArrayList<GrantedAuthority>();
            auths.add(auth1);
            auths.add(auth2);
        }

        final User user = new User(username, "lcy", true, true, true, true, auths);
        return user;
        }
    }*/
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException, DataAccessException {
        final Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        final ArrayList<String> list=Db.findPowerByUsername(username);
        for(final String p:list){
            final GrantedAuthorityImpl auth=new GrantedAuthorityImpl(p);
            auths.add(auth);

        }
        final User user = new User(username, username, true, true, true, true, auths);
        return user;
        }
    }
