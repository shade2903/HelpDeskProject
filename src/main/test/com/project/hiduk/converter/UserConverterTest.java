package com.project.hiduk.converter;

import com.project.haiduk.converter.UserConverter;
import com.project.haiduk.domain.User;
import com.project.haiduk.domain.enums.Role;
import com.project.haiduk.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class UserConverterTest {
    @InjectMocks
    UserConverter userConverter;

    @Mock
    MapperFacade mapperFacade;

    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toDto(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ilon");
        user.setLastName("Mask");
        user.setEmail("tesla@mail.com");
        user.setPassword("qwerty");
        user.setRole(Role.MANAGER);
        System.out.println(user);

        UserDto userDto = userConverter.toDto(user);
        System.out.println(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getPassword());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getRole(), userDto.getRole());

    }
}
