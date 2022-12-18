package com.ding.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer uid;
    private String username;
    private Integer gender;
    private String phone;
    private String email;
    private String avatar;
}
