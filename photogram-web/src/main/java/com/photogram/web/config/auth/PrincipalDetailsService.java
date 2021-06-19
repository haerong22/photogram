package com.photogram.web.config.auth;

import com.photogram.core.domain.entity.user.User;
import com.photogram.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found"));
        return new PrincipalDetails(userEntity);
    }
}
