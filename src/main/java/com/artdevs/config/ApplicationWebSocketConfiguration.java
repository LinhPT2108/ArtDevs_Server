package com.artdevs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class ApplicationWebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//    private final UserService userService;
//    private final UserValidationService userValidation;
//
//    @Autowired
//    public ApplicationWebSocketConfiguration(UserService userService, UserValidationService userValidation) {
//        this.userService = userService;
//        this.userValidation = userValidation;
//    }

    /**
     * Register Stomp endpoints: the url to open the WebSocket connection.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8080","http://localhost:3000")
                .withSockJS();
    }
//
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	 registry.setApplicationDestinationPrefixes("/app")
         			.setUserDestinationPrefix("/user")
         			.enableSimpleBroker("/chat", "/topic", "/queue");
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor =
//                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    Optional.ofNullable(accessor.getNativeHeader("Authorization")).ifPresent(ah -> {
//                        String bearerToken = ah.get(0).replace("Bearer ", "");
//                        JWTAuthenticationToken token = getJWTAuthenticationToken(bearerToken);
//                        accessor.setUser(token);
//                    });
//                }
//                return message;
//            }
//        });
//    }

//    private JWTAuthenticationToken getJWTAuthenticationToken(String token) {
//        if (token != null) {
//            String username = Jwts.parser()
//                    .setSigningKey("Secret".getBytes())
//                    .parseClaimsJws(token.replace("Bearer ", ""))
//                    .getBody()
//                    .getSubject();
//
//            if (username != null) {
//                UserDetails userData = this.userService
//                        .loadUserByUsername(username);
//
//                if (!userValidation.isValid(userData)) {
//                    throw new CustomException(UNAUTHORIZED_SERVER_ERROR_MESSAGE);
//                }
//
//                JWTAuthenticationToken jwtAuthenticationToken =
//                        new JWTAuthenticationToken(userData.getAuthorities(), token, (User) userData);
//
//                jwtAuthenticationToken.setAuthenticated(true);
//
//                return jwtAuthenticationToken;
//            }
//        }
//
//        return null;
//    }
}
