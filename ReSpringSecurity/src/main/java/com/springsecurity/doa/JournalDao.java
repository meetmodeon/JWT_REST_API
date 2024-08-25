package com.springsecurity.doa;

import com.springsecurity.model.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JournalDao {
    private String title;
    private String content;
    private Person person;

}
