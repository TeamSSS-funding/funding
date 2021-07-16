package io.github.mygoodsupporter.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Project {

    private int p_number;
    private String p_m_id;
    private String p_name;
    private int p_price;
    private String p_contents;
    private String p_startdate;
    private String p_enddate;

    private MultipartFile p_image;
    private String p_imagename;
}
