package com.restapi.restapitest.service;

import com.restapi.restapitest.dto.UserDto;
import com.restapi.restapitest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceDuplicateEmailTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserRejectsDuplicateEmail() {
        when(userRepository.existsByEmail("dup@example.test")).thenReturn(true);
        UserDto request = new UserDto();
        request.setFirstName("Ada");
        request.setLastName("Lovelace");
        request.setEmail("dup@example.test");

        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("dup@example.test");

        verify(userRepository, never()).save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    void createUserAllowsUniqueEmail() {
        when(userRepository.existsByEmail("unique@example.test")).thenReturn(false);
        when(userRepository.save(org.mockito.ArgumentMatchers.any())).thenAnswer(invocation -> {
            com.restapi.restapitest.entity.User saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });
        UserDto request = new UserDto();
        request.setFirstName("Ada");
        request.setLastName("Lovelace");
        request.setEmail("unique@example.test");

        userService.createUser(request);

        verify(userRepository).save(org.mockito.ArgumentMatchers.any());
    }
}
