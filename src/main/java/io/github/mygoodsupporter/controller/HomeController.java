package io.github.mygoodsupporter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.service.CategoryService;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/redirection")
	public String redirectionExample() {
		return "redirection";
	}

	@PostMapping("/redirection")
	public ResponseEntity<String> responseExample(@RequestParam String coffee, @RequestParam String cake) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Coffee c = new Coffee(coffee, cake);
		String json = objectMapper.writeValueAsString(c);
		log.debug(json);
		ResponseEntity<String> response = new ResponseEntity<>(json, HttpStatus.OK);
		return response;
	}

	@NoArgsConstructor
	@Getter @Setter
	class Coffee {
		private String coffee;
		private String cake;

		public Coffee(String coffee, String cake) {
			this.coffee = coffee;
			this.cake = cake;
		}
	}
}
