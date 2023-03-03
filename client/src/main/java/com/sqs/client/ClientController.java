package com.sqs.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ClientController {

    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping("/")
    public String serviceUrl() {
	InstanceInfo instance = discoveryClient.getNextServerFromEureka("alfa-service", false);
	return instance.getHomePageUrl();
    }
}
