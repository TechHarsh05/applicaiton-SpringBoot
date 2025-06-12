package com.emp.application.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private byte[] userImage;

    @NotNull
    private String email;

    @JsonIgnore // Prevent sending password back in responses
    public String getPassword() {
        return password;
    }

    @JsonProperty // Allow password to be set via incoming JSON
    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    private Long phone;
}
