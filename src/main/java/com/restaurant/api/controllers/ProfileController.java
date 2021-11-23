package com.restaurant.api.controllers;

import com.restaurant.api.models.Profile;
import com.restaurant.api.models.User;
import com.restaurant.api.repositories.ProfileRepository;
import com.restaurant.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/users/")
@CrossOrigin
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping(value = "/{id}/profile")
    public Profile findProfileById(@PathVariable("id")Long id){
        User user = userRepository.findById(id).get();
        return user.getProfile();
    }

    @PostMapping(value = "/{id}/profile")
    public Profile saveProfileWithUsedId(@PathVariable("id")Long id,@RequestBody Profile profile){
        User user = userRepository.findById(id).get();
        Optional<Profile> userProfileOpt = Optional.ofNullable(user.getProfile());
        if(userProfileOpt.isPresent()){
            Profile userProfile = userProfileOpt.get();
            userProfile.setAddress(profile.getAddress());
            userProfile.setFirstName(profile.getFirstName());
            userProfile.setLastName(profile.getLastName());
            userProfile.setPhoneNumber(profile.getPhoneNumber());
            user.setProfile(userProfile);
        }else{
            user.setProfile(profile);
        }
        User userSaved = userRepository.save(user);
        return userSaved.getProfile();
    }


}
