package idv.cnfang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.cnfang.service.RemoteService;

@RestController
@RequestMapping("/api/remote")
public class CallRemoteController {
    protected static final Logger logger = LoggerFactory.getLogger(CallRemoteController.class);
    
    @Autowired
    private RemoteService remoteService;
    
    /**
     * get Wiki page given the topic
     * @param topic
     * @return
     */
    @RequestMapping("/{topic}")
    public ResponseEntity<?> getWikiPage(@PathVariable String topic) {
        String body = remoteService.getWikiPage(topic);
        
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.TEXT_HTML);
        return ResponseEntity.ok().headers(header).body(body);
    }
    
}
