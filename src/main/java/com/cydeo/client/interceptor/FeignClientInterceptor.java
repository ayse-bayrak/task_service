package com.cydeo.client.interceptor;

import com.cydeo.service.KeycloakService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/*
We need to add authorization token as an authorization header to all of our requests that we are gonna send from our clients. The reason is we have security implementations added and enabled in all of our microservices which means none of the microservices is gonna responsed to any kind of request that doesn’t have any authorization token inside of it.
How can we add authorization token as an authorization header in our request?
First we need to extract the original authorizatiob token from the original request that is coming from the end users,
We have getAccesToken method in the KeyclockServiceImpl
We create interceptor package and FeignClientInterceptor class in project service
And copy from same class in user service and paste it.
This is how we add the token in all of our requests that’s goint to be send out from our clients.
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private final KeycloakService keycloakService;

    public FeignClientInterceptor(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", keycloakService.getAccessToken());
    }
}
