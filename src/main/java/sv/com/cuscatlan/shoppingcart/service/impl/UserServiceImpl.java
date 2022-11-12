package sv.com.cuscatlan.shoppingcart.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sv.com.cuscatlan.shoppingcart.controller.request.RoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.RoleResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.UserResponse;
import sv.com.cuscatlan.shoppingcart.model.Role;
import sv.com.cuscatlan.shoppingcart.model.User;
import sv.com.cuscatlan.shoppingcart.repository.RolRepository;
import sv.com.cuscatlan.shoppingcart.repository.UserRepository;
import sv.com.cuscatlan.shoppingcart.service.UserService;
import sv.com.cuscatlan.shoppingcart.service.mapper.RolMapper;
import sv.com.cuscatlan.shoppingcart.service.mapper.UserMapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final RolRepository rolRepository;

    @NonNull
    private final UserMapper userMapper;

    @NonNull
    private final RolMapper rolMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));

        log.info(">> User found in the database: {}", username);
        Collection<SimpleGrantedAuthority> auth = new ArrayList<>();
        user.getRoles().forEach(role -> {
            auth.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auth);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        log.info(">> saving new user {} to the h2", userRequest.getUserName());
        return userMapper.toUserResponse(userRepository.save(
                userMapper.toUser(userRequest)));
    }

    @Override
    public RoleResponse saveRol(RoleRequest roleRequest) {
        return rolMapper.toRoleResponse(rolRepository.save(
                rolMapper.toRole(roleRequest)
        ));
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Role rol = rolRepository.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.getRoles().add(rol);
    }

    @Override
    public UserResponse getUser(String userName) {
        log.info(">> fetching the user {} ", userName);
        return userMapper.toUserResponse(
                userRepository.findByUserName(userName).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info(">> fetching all user");
        List<User> users = userRepository.findAll();
        List<UserResponse> res = new ArrayList<>();

        for(User u : users) {
            res.add(userMapper.toUserResponse(u));
        }
        return res;
    }

}
