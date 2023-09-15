package com.austral.triviagoservice.security;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class LuchoDecode {

    public static JSONObject decodePayload(String token){
        //Decodes token and returns the payload as a JSONObject
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            return new JSONObject(payload);
    }
}
