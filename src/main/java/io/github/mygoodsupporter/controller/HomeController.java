package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.service.CategoryService;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String home(Model model) {
		List<Project> projectList = projectService.getProjectsByAll();
		model.addAttribute("projectList",projectList);

		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);

		return "home";
	}

	@GetMapping("/authentication")
	public String authenticationExample() {
		return "authentication";
	}
}
