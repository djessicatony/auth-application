package com.djess.auth.user.controller;


import com.djess.auth.user.dto.UpdateUserRequestDto;
import com.djess.auth.user.dto.UserResponseDto;
import com.djess.auth.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        log.info("Received request to get current user");
        UserResponseDto currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto> updateCurrentUser(@RequestBody @Valid UpdateUserRequestDto requestDto) {
        log.info("Received request to update current user with data: {}", requestDto);
        UserResponseDto updatedUser = userService.updateCurrentUser(requestDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity <Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        log.info("Fetching all users, page {}", pageable.getPageNumber());
        Page<UserResponseDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
        log.info("Successfully deleted user with id: {}", id);
        return ResponseEntity.noContent().build();
    }

}
