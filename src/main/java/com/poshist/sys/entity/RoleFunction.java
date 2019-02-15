package com.poshist.sys.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "T_SYS_ROLE_FUNCTION")
@EntityListeners(AuditingEntityListener.class)
public class RoleFunction extends  AbstractEntity {
    @OneToOne
    @JoinColumn(name = "ROLE_ID",referencedColumnName = "id")
    private Role role;
    @OneToOne
    @JoinColumn(name = "FUNCTION_ID",referencedColumnName = "id")
    private Function function;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
