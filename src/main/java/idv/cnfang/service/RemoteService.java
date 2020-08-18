package idv.cnfang.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService {
    
    private final RestTemplate restTemplate;
  
    /**
     * Customize your restTemplate by RestTemplateBuilder
     * @param restTemplateBuilder
     */
    @Autowired
    RemoteService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    
    public String getWikiPage(String topic) {
        String url = "https://en.wikipedia.org/wiki/{topic}";
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, topic);
        return entity.getBody();
    }
}
