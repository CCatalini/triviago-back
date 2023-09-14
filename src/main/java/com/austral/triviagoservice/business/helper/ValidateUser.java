package com.austral.triviagoservice.business.helper;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.security.LuchoDecode;
import org.json.JSONObject;

public class ValidateUser {

    public static void validate(Long actualId, String token) throws InvalidContentException {
        JSONObject json = LuchoDecode.decodePayload(token);
        if(!actualId.equals(json.getLong("id"))){throw new InvalidContentException("Invalid user id");}
    }
}
