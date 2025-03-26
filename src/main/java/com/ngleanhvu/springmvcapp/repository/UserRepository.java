package com.ngleanhvu.springmvcapp.repository;

import com.ngleanhvu.springmvcapp.pojo.User;

public interface UserRepository {
    User getUserByUsername(String username);
}
