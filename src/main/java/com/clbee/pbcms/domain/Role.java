package com.clbee.pbcms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    COMPANY_MIDDLE_ADMIN("ROLE_COMPANY_MIDDLEADMIN"),
    COMPANY_MEMBER("ROLE_COMPANY_MEMBER"),
    COMPANY_DISTRIBUTOR("ROLE_COMPANY_DISTRIBUTOR"),
    COMPANY_CREATOR("ROLE_COMPANY_CREATOR"),
    COMPANY_USER("ROLE_COMPANY_USER"),
    INDIVIDUAL_MEMBER("ROLE_INDIVIDUAL_MEMBER"),
    ADMIN_SERVICE("ROLE_ADMIN_SERVICE");

    private String value;
}