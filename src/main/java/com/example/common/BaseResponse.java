package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    public int ResponseCode;
    public T data;
    public String message;
    public static <T>BaseResponse<T> success(T data){
        return new BaseResponse(200,data,"ok");
    }
    public static <T>BaseResponse<T>Error(AppHttpCodeEnum a,T data){
        return new BaseResponse(a.code,data,a.getMsg());
    }
}
