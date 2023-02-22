package com.project.hiduk.converter;

import com.project.haiduk.config.WebAppConfig;
import com.project.haiduk.converter.AbstractConverter;
import com.project.haiduk.converter.UserConverter;
import com.project.haiduk.domain.User;
import com.project.haiduk.domain.enums.Role;
import com.project.haiduk.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class UserConverterTest {
    @InjectMocks
    UserConverter userConverter;

    @Mock
    MapperFacade mapperFacade;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
        mapperFacade.map(User.class, UserDto.class);
    }

    @Test
    public void toDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ilon");
        user.setLastName("Mask");
        user.setEmail("tesla@mail.com");
        user.setPassword("qwerty");
        user.setRole(Role.MANAGER);

        Mockito.when(userConverter.toDto(user)).thenReturn(mapperFacade.map(user, UserDto.class));

        UserDto userDto = userConverter.toDto(user);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getRole(), userDto.getRole());

    }
}
