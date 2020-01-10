package com.clbee.pbcms.security;

import com.clbee.pbcms.domain.Role;
import com.clbee.pbcms.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -7306690067344994732L;

    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private MemberDto member;
    private boolean isEnabled;
    private boolean isBook;

    public UserPrincipal(MemberDto member, Collection<? extends GrantedAuthority> authorities,
                         boolean isEnabled, boolean isBook) {
        super();

        this.authorities = authorities;
        this.member = member;
        this.isEnabled = isEnabled;
        this.isBook = isBook;
    }

    public String getUsername() { return member.getUserId(); }
    public String getPassword() { return member.getUserPw(); }

    public MemberDto getMember() { return member; }

    public static UserPrincipal create(MemberDto member) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getUserStatus() == "4") {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN_SERVICE.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.COMPANY_USER.getValue()));
        }

        return new UserPrincipal(member, authorities, true,false);
    }

    public Long getId() { return Long.valueOf(member.getUserSeq()); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }
}
