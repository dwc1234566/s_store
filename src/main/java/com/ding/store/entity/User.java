package com.ding.store.entity;

import lombok.*;


@Data()
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    private static final long serialVersionUID = 6450022333397425035L;

    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer gender;
    private String phone;
    private String email;
    private String avatar;
    private Integer isDelete;
}
