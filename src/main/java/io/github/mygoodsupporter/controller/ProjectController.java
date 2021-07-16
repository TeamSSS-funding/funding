package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService ps;

    private ModelAndView mav;

    // 프로젝트 신청 화면 요청
    @RequestMapping(value="/projectRequestPage")
    public String projectRequestPage() {
        return "projectRequest";
    }

    //프로젝트 신청페이지
    @RequestMapping(value="projectRequest")
    public ModelAndView projectRequest(@ModelAttribute Project pdto) throws IllegalStateException, IOException {
        mav = ps.projectRequest(pdto);
        return mav;
    }

    //프로젝트 심사 페이지
    @RequestMapping(value="projectSimsa")
    public ModelAndView projectSimsa(@ModelAttribute Project pdto) {
        mav = ps.projectSimsa(pdto);
        return mav;
    }
}
