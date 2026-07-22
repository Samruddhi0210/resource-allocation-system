package com.samruddhi.resourceallocationsystem.controller;
import com.samruddhi.resourceallocationsystem.service.AIRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/ai")
public class AIController {
    @Autowired
    private AIRecommendationService aiRecommendationService;
    @GetMapping("/recommend")
    public Map recommend(@RequestParam String skill){
        return aiRecommendationService.getRecommendation(skill);
    }



}
