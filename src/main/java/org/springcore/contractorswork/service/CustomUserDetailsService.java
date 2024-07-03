/*
package org.springcore.contractorswork.service;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.GenContractor;
import org.springcore.contractorswork.repository.GenContractorRepository;
import org.springcore.contractorswork.service.GenContractorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final GenContractorRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<GenContractor> userByUsername = repository.findUserByName(username);
        if(userByUsername.isPresent()){
            var user = userByUsername.get();
            return (UserDetails) GenContractor.builder()
                    .name(user.getName())
                    .password(user.getPassword())
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
*/
