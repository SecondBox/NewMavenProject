package models.ldap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdLogin {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

}
