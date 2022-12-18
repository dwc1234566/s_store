package com.ding.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = 2072016042797539034L;
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;
    private Integer state;

    public static Result ok(){
        return new Result(true, null, null, null,200);
    }
    public static Result ok(Object data){
        return new Result(true, null, data, null,200);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, total,200);
    }
    public static Result fail(String errorMsg,Integer state){
        return new Result(false, errorMsg, null, null,state);
    }
}
