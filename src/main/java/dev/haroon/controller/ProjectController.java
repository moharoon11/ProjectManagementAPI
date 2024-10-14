package dev.haroon.controller;

import dev.haroon.dto.ProjectDTO;
import dev.haroon.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create a new project with file upload
    @PostMapping("/create")
    public ResponseEntity<Integer> createProject(
            @RequestPart("projectDTO") ProjectDTO projectDTO, 
            @RequestPart("file") MultipartFile file) {

        Integer projectId = projectService.createProjectWithFile(projectDTO, file);
        return ResponseEntity.ok(projectId);
    }

    // Get all projects by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@PathVariable Integer userId) {
        List<ProjectDTO> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    // Update a project by ID
    @PutMapping("/update")
    public ResponseEntity<Integer> updateProject(
            @RequestPart("projectDTO") ProjectDTO projectDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Integer updatedProjectId = projectService.updateProject(projectDTO, file);
        return ResponseEntity.ok(updatedProjectId);
    }

    // Delete a project by ID
 // Delete a project by user ID and project ID
    @DeleteMapping("/delete/{userId}/{projectId}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable Integer userId, @PathVariable Integer projectId) {
        boolean isDeleted = projectService.deleteProject(userId, projectId);
        return ResponseEntity.ok(isDeleted);
    }

}
