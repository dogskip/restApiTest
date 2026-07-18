package com.restapi.restapitest.service;

import com.restapi.restapitest.dto.UserDto;
import com.restapi.restapitest.entity.User;
import com.restapi.restapitest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsersMapsEntitiesToDtos() {
        User first = user(1L, "Ada", "Lovelace", "ada@example.test", "010-0000-0001");
        User second = user(2L, "Grace", "Hopper", "grace@example.test", "010-0000-0002");
        when(userRepository.findAll()).thenReturn(List.of(first, second));

        List<UserDto> result = userService.getAllUsers();

        assertThat(result)
                .extracting(UserDto::getId, UserDto::getFirstName, UserDto::getEmail)
                .containsExactly(
                        org.assertj.core.groups.Tuple.tuple(1L, "Ada", "ada@example.test"),
                        org.assertj.core.groups.Tuple.tuple(2L, "Grace", "grace@example.test")
                );
        verify(userRepository).findAll();
    }

    @Test
    void createUserPersistsAndReturnsMappedDto() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User saved = invocation.getArgument(0);
            saved.setId(42L);
            return saved;
        });
        UserDto request = new UserDto();
        request.setFirstName("Katherine");
        request.setLastName("Johnson");
        request.setEmail("katherine@example.test");
        request.setPhoneNumber("010-0000-0042");

        UserDto result = userService.createUser(request);

        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.getFirstName()).isEqualTo("Katherine");
        assertThat(result.getLastName()).isEqualTo("Johnson");
        assertThat(result.getEmail()).isEqualTo("katherine@example.test");
        assertThat(result.getPhoneNumber()).isEqualTo("010-0000-0042");

        ArgumentCaptor<User> savedUser = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(savedUser.capture());
        assertThat(savedUser.getValue().getEmail()).isEqualTo("katherine@example.test");
    }

    private static User user(Long id, String firstName, String lastName, String email, String phoneNumber) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
