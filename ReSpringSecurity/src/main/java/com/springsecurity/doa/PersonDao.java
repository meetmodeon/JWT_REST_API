package com.springsecurity.doa;

import com.springsecurity.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDao {
    private String name;
    private String email;
    private String password;
    private Role role;
}
