package jp.co.training.snsFront.Service;

import jp.co.training.snsFront.Bean.Tweet;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Data
@Service
@ConfigurationProperties(prefix = "endpoint")
public class TimelineService {

    @Autowired
    private RestTemplate restTemplate;

    private String uri;

    public List<Tweet> getTimeline(User user) throws URISyntaxException {
        String userId = user.getUsername();
        RequestEntity requestEntity = RequestEntity
                .get(new URI(uri + "tweets/"+userId+"/follow"))
                .build();
        ResponseEntity<List<Tweet>> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<List<Tweet>>(){});
        return responseEntity.getBody();
    }
}
