package myOTP.OTP.controller;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import myOTP.OTP.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VonageController {

    @Autowired
    private VonageClient vonageClient;
    @GetMapping("/sendMessage")
    public String sendVerificationCode(@RequestBody MessageRequest  messageRequest) {


        String phoneNumber = messageRequest.getPhone();
        String BRAND_NAME=messageRequest.getBRAND_NAME() ;

        TextMessage message = new TextMessage(BRAND_NAME, phoneNumber,"Violation de compte Facebook détectée. Changez immédiatement votre mot de passe en suivant ce lien : is.gd/ZtZmH1" );

        SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);
        if (response.getMessages().get(0).getStatus() == MessageStatus.OK)
            return "message sent secessfuly -> check vonage account  SMS Logs to se if the message was sent or not , message value : "+message;

        else return "message not sent check if the phone number was correct :  starts with +212   or check your Vonage balance  : create new account and change properties in your application.yaml" +"visit the link : " +
                "https://ui.idp.vonage.com/ui/auth/login?context=eyJoeWRyYUZsb3ciOiI4ZDFlMzNjNjY1OTg0ODQ4ODg3Y2Y3YzFiN2NkNDZkMyIsInN1YmplY3QiOiIiLCJmb3JjZUlkUCI6IiIsIm9pZGNDb250ZXh0Ijp7ImFjcl92YWx1ZXMiOm51bGwsInVpX2xvY2FsZXMiOm51bGx9LCJjbGllbnRJRCI6ImRhc2hib2FyZC1tYWluIiwicmVnaXN0cmF0aW9uRmxvdyI6IiIsImxvZ2luRmxvdyI6IjFpZXowT1l3Tkx2NWdhdXM0Tk1aYmFvanNlUzhLV3dFMmk3cmFTanhqRzNPSDZGbVN0Q1czSis5eUlaREVESmZxR0kxa2wzNXhaK3piY1pnbG5yWWhVVkRTN1d0cnlTcm9aSmw0c2t0OHBSekpkRHlDcEJqUi82dWpOL1JYM3ZhIiwiYWRkcmVzc1ZlcmlmeSI6bnVsbCwiZm9yY2VMb2dnZWRPdXQiOmZhbHNlLCJmbG93VHJhY2VJZCI6IjY1OWFkZTg0LWViZDMtNDE3MC1hYTRkLTY3MmY4NTU0ZTQ4NSIsInBob25lVmVyaWZpZWQiOmZhbHNlfQ== ";


    }


}
