package com.example.onlinemarketbe.common;

import com.example.onlinemarketbe.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

  private final int userId;

  private final String username;

  @JsonIgnore
  private final String password;

  private final boolean isActive;

  private final Collection<? extends GrantedAuthority> authorities;


  public UserDetailsImpl(int id, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
    this.userId = id;
    this.username = username;
    this.password = password;
    this.isActive = true;
    this.authorities = authorities;
  }



  public static UserDetailsImpl build(int id, String username, String password, Set<Role> roles) {
    for (Role r: roles){
      System.out.println(r.getName().name());

    }

    List<SimpleGrantedAuthority> authorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());


    return new UserDetailsImpl(id, username, password, authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public int getUserId() {
    return userId;
  }


  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(userId, user.userId);
  }
}
