package com.javamentor.backend.service;

import com.javamentor.backend.model.GenderEnum;
import com.javamentor.backend.model.Profile;
import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.ProfileRepository;
import com.javamentor.backend.util.AvatarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;
    private final AvatarUtil avatarUtil;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, AvatarUtil avatarUtil) {
        this.profileRepository = profileRepository;
        this.avatarUtil = avatarUtil;
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    /**
     * Метод для добавления профиля при создании юзера.
     *
     * Необходим для заполнения полей значениями по умолчанию и привязки профиля к юзеру.
     *
     * Метод работает только для новых юзеров.
     * Если в метод передать юзера с профилем, то метод ничего не сделает (данные не перезапишет)
     *
     * @param user пользователь, которому создается профиль (новый пользователь, не имеющий профиля)
     * @return false если у юзера уже есть профиль.
     * true если профиль создался.
     */
    @Override
    public boolean saveDefaultProfile(User user) {

        if(profileRepository.existsByUserId(user.getId())){
            return false;
        }

        Profile profile = new Profile();
        profile.setAvatar(avatarUtil.getDefaultProfileAvatar());
        profile.setGender(GenderEnum.OTHER);
        profile.setRegisteredDate(LocalDate.now());
        profile.setUser(user);
        profileRepository.save(profile);

        return true;

    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }

}
