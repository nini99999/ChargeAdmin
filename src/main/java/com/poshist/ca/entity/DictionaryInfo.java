package com.poshist.ca.entity;

import com.poshist.common.AbstractEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "t_ca_dictionary_info")
@EntityListeners(AuditingEntityListener.class)
public class DictionaryInfo  extends AbstractEntity {
    private String type;
    private String name;
    private Integer status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
