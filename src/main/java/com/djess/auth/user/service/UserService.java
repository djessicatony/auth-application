    package com.djess.auth.user.service;

    import com.djess.auth.user.dto.UpdateUserRequestDto;
    import com.djess.auth.user.dto.UserResponseDto;
    import com.djess.auth.user.exception.ResourceNotFoundException;
    import com.djess.auth.user.mapper.UserMapper;
    import com.djess.auth.user.model.User;
    import com.djess.auth.user.model.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class UserService {

        private final UserRepository userRepository;
        private final UserMapper userMapper;

        @PreAuthorize("hasRole('ADMIN')")
        public UserResponseDto getCurrentUser() {
            User user = getCurrentUserEntity();
            return userMapper.toResponseDto(user);
        }

        public UserResponseDto updateCurrentUser(UpdateUserRequestDto requestDto){
            User user = getCurrentUserEntity();
            userMapper.updateEntity(requestDto, user);
            User updatedUser = userRepository.save(user);
            return userMapper.toResponseDto(updatedUser);
        }

        @PreAuthorize("hasRole('ADMIN')")
        public Page<UserResponseDto> getAllUsers(Pageable pageable) {
            Page<User> users = userRepository.findAll(pageable);
            return users.map(userMapper::toResponseDto);
        }

        public void deleteUser(Long id){

            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User", "id", id.toString());
            }

            userRepository.deleteById(id);
        }

        private User getCurrentUserEntity(){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        }
    }
