package com.poshist.sys.entity;

import com.poshist.common.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_SYS_ROLE")
@EntityListeners(AuditingEntityListener.class)
public class Role extends AbstractEntity {
    private String name;
    private String memo;
    private String status;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<RoleFunction> roleFunctions;

    public List<RoleFunction> getRoleFunctions() {
        return roleFunctions;
    }

    public void setRoleFunctions(List<RoleFunction> roleFunctions) {
        this.roleFunctions = roleFunctions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
