package myOTP.OTP.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class MessageRequest {
    private String BRAND_NAME ;
    private String phone ;

}
