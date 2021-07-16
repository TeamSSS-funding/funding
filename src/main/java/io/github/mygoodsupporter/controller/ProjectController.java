package io.github.mygoodsupporter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {
    // 프로젝트 신청 화면 요청
    @RequestMapping(value="/projectRequestPage")
    public String projectRequestPage() {
        return "projectRequest";
    }
}
