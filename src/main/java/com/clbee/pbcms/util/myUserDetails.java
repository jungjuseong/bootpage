package com.clbee.pbcms.util;

import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clbee.pbcms.vo.MemberVO;

@Data
public class myUserDetails implements UserDetails {

    private static final long serialVersionUID = -7306690067344994732L;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private MemberVO memberVO;
    private boolean isEnabled;
    private boolean isBook;

    public myUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         MemberVO memberVO, boolean isEnabled, boolean isBook) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.memberVO = memberVO;
        this.isEnabled = isEnabled;
        this.isBook = isBook;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return isEnabled;
    }
}
