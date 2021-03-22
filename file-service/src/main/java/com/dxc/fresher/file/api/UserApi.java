package com.dxc.fresher.file.api;




import com.dxc.fresher.file.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "USER-SERVICE")
public interface UserApi {

    @GetMapping("/users/{userName}")
    public UserModel findByUserName(@PathVariable("userName") String userName);

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody UserModel user, @PathVariable("id") int id);
}
