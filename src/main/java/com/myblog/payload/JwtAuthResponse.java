package com.myblog.payload;
import lombok.Data;
@Data
public class JwtAuthResponse {

        private String accessToken;
        private String tokentype = "Bearer";

        public JwtAuthResponse(String accessToken){
            this.accessToken = accessToken;
        }
    }

