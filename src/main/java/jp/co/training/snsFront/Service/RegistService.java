package jp.co.training.snsFront.Service;

import jp.co.training.snsFront.Bean.RegistForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix = "endpoint")
public class RegistService {

    private String uri;

    @Autowired
    RestTemplate restTemplate;

//    public regist(RegistForm form)

}
