package com.samruddhi.resourceallocationsystem.controller;
import com.samruddhi.resourceallocationsystem.entity.Employee;
import com.samruddhi.resourceallocationsystem.entity.Project;
import com.samruddhi.resourceallocationsystem.service.AIRecommendationService;
import com.samruddhi.resourceallocationsystem.service.EmployeeService;
import com.samruddhi.resourceallocationsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }
    @Autowired
    private AIRecommendationService aiRecommendationService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }
    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }
    @PostMapping("/auto-assign")
    public Project createProjectWithAutoAssign(@RequestBody Project project) {
        Map recommendation = aiRecommendationService.getRecommendation(project.getRequiredSkill());
        Map recommendedEmployee = (Map) recommendation.get("recommended");
        if (recommendedEmployee != null) {
            Long empId = Long.valueOf(recommendedEmployee.get("id").toString());
            Employee emp = employeeService.getEmployeeById(empId);
            project.setAssignedEmployee(emp);
        }
        return projectService.saveProject(project);
    }
}
