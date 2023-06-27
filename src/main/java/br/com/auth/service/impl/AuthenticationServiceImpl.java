package br.com.auth.service.impl;

import br.com.auth.domain.entity.UserEntity;
import br.com.auth.exception.impl.ExpiredUserException;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        validateUser(user);
        return user;
    }

    private void validateUser(UserEntity user) {
        if (Objects.isNull(user)) {
            throw new UnauthorizedException();
        } else if (!user.isStatus()) {
            throw new ExpiredUserException();
        }
    }

}
