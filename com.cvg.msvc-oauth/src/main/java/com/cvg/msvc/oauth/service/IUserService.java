package com.cvg.msvc.oauth.service;

import com.cvg.msvc.oauth.models.entity.UserModel;

public interface IUserService {
	public UserModel findByUsername(String username);
}
