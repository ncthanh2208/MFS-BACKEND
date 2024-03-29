package com.dxc.fresher.authservice.api;


import com.dxc.fresher.authservice.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE")
public interface UserApi {

    @GetMapping("/users/admin/{userName}")
    public UserModel loadByUserName(@PathVariable("userName") String userName);

    @GetMapping("/users/{userName}")
    public UserModel findByUserName(@PathVariable("userName") String userName);
}
