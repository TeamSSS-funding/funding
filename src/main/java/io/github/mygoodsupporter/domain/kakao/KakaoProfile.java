package io.github.mygoodsupporter.domain.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoProfile {

    public Integer id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;
}
