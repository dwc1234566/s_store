package com.ding.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassworrdDto {
    private String newPassword;
    private String oldPassword;
}
