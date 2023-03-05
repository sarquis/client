package com.sqs.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ClientController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/eurekaClient")
    public String exec() {
	InstanceInfo instance = eurekaClient.getNextServerFromEureka("alfa-service", false);
	String eurekaUrl = instance.getHomePageUrl();
	WebClient.Builder builder = WebClient.builder();
	String resultado = builder.build().get().uri(eurekaUrl).retrieve().bodyToMono(String.class).block();
	return resultado += "(" + eurekaUrl + ")";
    }

}
