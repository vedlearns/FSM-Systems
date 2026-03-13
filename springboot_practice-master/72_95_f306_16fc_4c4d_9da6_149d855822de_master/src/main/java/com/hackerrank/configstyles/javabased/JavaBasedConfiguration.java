package com.hackerrank.configstyles.javabased;

import com.hackerrank.configstyles.service.CallNotificationService;
import com.hackerrank.configstyles.service.SmsNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBasedConfiguration {
    @Bean
    public SmsNotificationService smsNotificationService(){
        return new SmsNotificationService("SMS_SERVICE");
    }

    @Bean
    public CallNotificationService callNotificationService(){
        return new CallNotificationService("CALL_SERVICE");
    }
}
