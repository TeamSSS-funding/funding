package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    private final MemberDAO memberDAO;

    // 프로젝트 신청 화면 요청 메이커
    @RequestMapping(value="/projectRequestPage")
    public String projectRequestPage(@AuthenticationPrincipal MemberDetails memberDetails) {
        log.debug(memberDetails.getId());
        log.debug(memberDetails.getEmail());
        log.debug(memberDetails.getUsername());
        log.debug(memberDetails.getAuthorities().toString());
        return "projects/projectRequest";
    }

    //프로젝트 신청페이지 메이커
    @RequestMapping(value="/projectRequest")
    public String projectRequest(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute Project pdto, Model model){
        //현재 로그인된 아이디 가져옴
        Project project = new Project();
        project.setMemberId(memberDetails.getId());

        pdto = projectService.projectRequest(pdto);
        model.addAttribute("pdto", pdto);
        return "projects/projectList";
    }

}
