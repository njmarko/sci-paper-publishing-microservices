package rs.njmarko.scipaperservice.service.resttemplates;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {


    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate;

    @Autowired
    public UserService(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    private String getServiceURL(String serviceName){
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }


    @HystrixCommand(fallbackMethod = "getFallbackIsLoggedIn",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public Boolean isLoggedIn(String username){
        return restTemplate.getForObject(getServiceURL("UserService") + "/is-logged-in/{username}", Boolean.class, username);
    }

    public Boolean getFallbackIsLoggedIn(String username){
        return Boolean.FALSE;
    }

    @HystrixCommand(fallbackMethod = "getFallbackGetFullName",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public String getFullName(String username){
        return restTemplate.getForObject(getServiceURL("UserService") + "/get-full-name/{username}", String.class, username);
    }

    public String getFallbackGetFullName(String username){
        return "";
    }

}
