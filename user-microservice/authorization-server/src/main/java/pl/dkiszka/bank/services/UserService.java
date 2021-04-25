package pl.dkiszka.bank.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dkiszka.bank.models.User;
import pl.dkiszka.bank.repositories.UserRepository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project bank-application
 * @date 25.04.2021
 */
@Service(value = "userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAllByUsername(username)
                .map(this::convertToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect Username / Password supplied!"));
    }

    private UserDetails convertToUserDetails(User user) {
        var account = user.getAccount();
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

}
