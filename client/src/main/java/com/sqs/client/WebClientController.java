package com.sqs.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class WebClientController {

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Bean
    WebClient.Builder loadBalancedWebClientBuilder() {
	return WebClient.builder();
    }

    @GetMapping("/")
    public String exec() {
	String url = "http://ALFA-SERVICE/";
	WebClient.Builder builder = WebClient.builder();
	String resultado = builder.filter(lbFunction).build().get().uri(url).retrieve().bodyToMono(String.class)
		.block();
	return resultado;
    }
}
