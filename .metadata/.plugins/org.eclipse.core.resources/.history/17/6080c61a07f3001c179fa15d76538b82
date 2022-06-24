package com.cvg.msvc.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cvg.msvc.oauth.models.entity.UserModel;

@FeignClient(name = "msvc-usuarios")
public interface UserFeignClient {
	@GetMapping("/users/find-email")
	UserModel findByUsername(@RequestParam("email") String email);
}
