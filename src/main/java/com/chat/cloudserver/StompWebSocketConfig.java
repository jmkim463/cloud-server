package com.chat.cloudserver;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 소켓 연결 설정
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
            .addEndpoint("/ws")
//                .addInterceptors() Exception handler
             .setAllowedOrigins("*");
    }

    // Message Broker 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 send할 때 경로를 설정하는 함수
        // 경로 앞에 pub이 붙어 있을 시 broker로 보내진다.
        registry.setApplicationDestinationPrefixes("/pub");

        // 메시지를 받을 떄 경로를 설정
        // /queue -> 1:1, /topic -> 1:N
        registry.enableSimpleBroker("/queue", "/topic");
    }

    // tcp handshake 시 jwt 인증용
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);

//        registration.interceptors();
    }
}
