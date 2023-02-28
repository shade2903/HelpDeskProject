package com.project.haiduk.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthenticationDto {
    private final String MESSAGE_EMPTY_FIELD = "Please fill out the required field.";
    private final String MESSAGE_VALID_FIELD = "Please make sure you are using a valid email or password";
    private final String REGEX_VALID_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-]" +
            "[A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
    private final String REGEX_VALID_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*" +
            "[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$";

    @NotEmpty(message = MESSAGE_EMPTY_FIELD)
    @Size(max = 100, message = "Email must be less than 100 characters")
    @Pattern(regexp = REGEX_VALID_EMAIL,
            message = MESSAGE_VALID_FIELD)
    private String userName;

    @NotEmpty(message = MESSAGE_EMPTY_FIELD)
    @Pattern(regexp = REGEX_VALID_PASSWORD, message =MESSAGE_VALID_FIELD)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationDto() {
    }

    public AuthenticationDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
