package com.ding.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 8785244366116521578L;

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
