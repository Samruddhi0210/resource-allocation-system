package com.samruddhi.resourceallocationsystem.service;
import com.samruddhi.resourceallocationsystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class AIRecommendationService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmployeeService employeeService;
    public Map getRecommendation(String requiredSkill){
        List<Employee> employees=employeeService.getAllEmployees();
        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("requiredSkill",requiredSkill);
        requestBody.put("employees",employees);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,Object>> request= new HttpEntity<>(requestBody,headers);
        String pythonUrl="http://localhost:5000/recommend";
        Map response = restTemplate.postForObject(pythonUrl,request, Map.class);
        return response;
    }
}
