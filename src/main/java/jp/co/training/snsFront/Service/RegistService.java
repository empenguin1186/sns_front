package jp.co.training.snsFront.Service;

import jp.co.training.snsFront.Bean.PassReq;
import jp.co.training.snsFront.Bean.RegistForm;
import jp.co.training.snsFront.Bean.RegistResponse;
import jp.co.training.snsFront.Bean.UpdatePassForm;
import jp.co.training.snsFront.Bean.UserProfile;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Data
@Service
@ConfigurationProperties(prefix = "endpoint")
public class RegistService {

    private String uri;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PasswordEncoder encoder;

    public RegistResponse registUser(RegistForm form) throws URISyntaxException {

        /* convert (year, month, day) into LocalDate */
        LocalDate birthDay = LocalDate.of(form.getYear(), form.getMonth(), form.getDay());

        /* build UserProfile for registration */
        UserProfile user = UserProfile.builder()
                .userId(form.getUserId())
                .userName(form.getUserName())
                .encodedPassword(new BCryptPasswordEncoder().encode(form.getEncodedPassword()))
                .comment("まだ設定されていません。")
                .email(form.getEmail())
                .place(form.getPlace())
                .birthDay(birthDay)
                .imgUrl("まだ設定されていません。")
                .build();

        URI path = new URI(uri + "user/post");

        RequestEntity<UserProfile> entity = RequestEntity
                .post(path)
                .body(user);

        ResponseEntity<RegistResponse> res = restTemplate.exchange(entity, RegistResponse.class);
        return res.getBody();
    }

    public RegistResponse updatePass(UpdatePassForm form) throws URISyntaxException{
        PassReq req = new PassReq();
        req.setUserId(form.getUserId());
        req.setEncodedPassword(encoder.encode(form.getPassword()));
        URI path = new URI(uri + "user/password/put");

        RequestEntity<PassReq> entity = RequestEntity
                .post(path)
                .body(req);

        ResponseEntity<RegistResponse> res = restTemplate.exchange(entity, RegistResponse.class);
        return res.getBody();
    }

}
