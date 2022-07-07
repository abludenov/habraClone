package com.javamentor.backend.service;

import com.javamentor.backend.model.Profile;
import com.javamentor.backend.model.User;

public interface ProfileService {


    /**
     * @see ProfileServiceImpl#saveDefaultProfile(User) 
     */
    boolean saveDefaultProfile(User user);
    void updateProfile(Profile profile);
    Profile getProfileByUserId(Long userId);

}
