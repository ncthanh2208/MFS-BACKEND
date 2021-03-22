package com.dxc.fresher.apigateway.api;



import com.dxc.fresher.apigateway.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE")
public interface UserApi {

    @GetMapping("/users/admin/{userName}")
    public UserModel loadByUserName(@PathVariable("userName") String userName);

}
