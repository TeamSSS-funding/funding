package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
public class ProjectController {

    @Autowired
    private ProjectService ps;
    @Autowired
    private MemberDAO memberDAO;
    private ModelAndView mav;

    //로그인 아이디 불러오기
    @GetMapping("/principal")
    @ResponseBody
    public String currentUserNameByPrincipal(Principal principal, @AuthenticationPrincipal MemberDetails memberDetails) {
        log.debug("MemberDetails:" + memberDetails.getId() + " " + memberDetails.getUsername() + " " + memberDetails.getEmail());
        log.debug(principal.getName());

        return principal.getName() + "MemberDetails:" + memberDetails.getId() + " " + memberDetails.getUsername() + " " + memberDetails.getEmail();
    }

    // 프로젝트 신청 화면 요청
    @RequestMapping(value="/projectRequestPage")
    public String projectRequestPage(Principal principal) {
        MemberDetails memberDetails = (MemberDetails) principal;
        log.debug("Member[id=" + memberDetails.getId() + ", " + memberDetails.getUsername() + "]");
        return "projectRequest";
    }

    //프로젝트 신청페이지
    @RequestMapping(value="/projectRequest")
    public ModelAndView projectRequest(@ModelAttribute Project pdto) throws IllegalStateException, IOException {
        mav = ps.projectRequest(pdto);
        return mav;
    }

    //프로젝트 심사 페이지
    @RequestMapping(value="/projectSimsa")
    public ModelAndView projectSimsa(@ModelAttribute Project pdto) {
        mav = ps.projectSimsa(pdto);
        return mav;
    }
}
