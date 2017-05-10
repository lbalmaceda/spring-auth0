package com.github.fromi.openidconnect.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private final String sub;
    private final String email;
    private final String name;
    private final String givenName;
    private final String familyName;
    private final String picture;

    @JsonCreator
    public UserInfo(@JsonProperty("sub") String sub,
                    @JsonProperty("email") String email,
                    @JsonProperty("name") String name,
                    @JsonProperty("given_name") String givenName,
                    @JsonProperty("family_name") String familyName,
                    @JsonProperty("picture") String picture) {
        this.sub = sub;
        this.email = email;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
    }

    public String getSub() {
        return sub;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPicture() {
        return picture;
    }
}
