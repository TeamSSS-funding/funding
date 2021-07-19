package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.ProjectDAO;
import io.github.mygoodsupporter.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Service
public class ProjectService {
    @Autowired
    private ProjectDAO pdao;

    private ModelAndView mav;

    //프로젝트 정보 입력
    public ModelAndView projectRequest(Project pdto) throws IllegalStateException, IOException {
        mav = new ModelAndView();

        MultipartFile proimage = pdto.getP_image();

        String proimagename = proimage.getOriginalFilename();

        proimagename = System.currentTimeMillis()+"-"+proimagename;

        String savePath = "E:\\soure_hsh\\mygoodsupporter\\src\\main\\resources\\upload\\"+proimagename;

        if(!proimage.isEmpty()) {
                proimage.transferTo(new File(savePath));
            }

        pdto.setP_imagename(proimagename);

        pdao.projectRequest(pdto);

        mav.addObject("pdto", pdto);
        mav.setViewName("redirect:/projectList");

        return mav;
    }

    //프로젝트 심사하는 과정
    public ModelAndView projectSimsa(Project pdto) {
        mav = new ModelAndView();


        return mav;
    }
}
