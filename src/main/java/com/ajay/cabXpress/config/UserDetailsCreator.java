package com.ajay.cabXpress.config;

import com.ajay.cabXpress.model.Admin;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Driver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCreator implements UserDetails {

    String username;

    String password;

    List<GrantedAuthority> authorities;

    public UserDetailsCreator(Object object){
        if(object instanceof Customer){
            Customer customer = (Customer)object;
            this.username = customer.getEmail();
            this.password = customer.getPassword();
            this.authorities = new ArrayList<>();
            String roles[] = customer.getRole().split(",");

            for(String role : roles){
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(simpleGrantedAuthority);
            }

        }else if(object instanceof Driver){
            Driver driver = (Driver)object;
            this.username = driver.getEmail();
            this.password = driver.getPassword();
            this.authorities = new ArrayList<>();
            String roles[] = driver.getRole().split(",");

            for(String role : roles){
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(simpleGrantedAuthority);
            }
        }else if(object instanceof Admin){
            Admin admin = (Admin) object;
            this.username = admin.getEmail();
            this.password = admin.getPassword();
            this.authorities = new ArrayList<>();
            String roles[] = admin.getRole().split(",");

            for(String role : roles){
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(simpleGrantedAuthority);
            }
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
