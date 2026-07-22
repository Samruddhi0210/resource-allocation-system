package com.samruddhi.resourceallocationsystem.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String requiredSkill;
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedEmployee;
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitle(){return title;}
    public void  setTitle(String title){this.title=title;}
    public String getRequiredSkill(){return requiredSkill;}
    public void setRequiredSkill(String requiredSkill){this.requiredSkill=requiredSkill;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public Employee getAssignedEmployee(){return assignedEmployee;}
    public void setAssignedEmployee(Employee assignedEmployee){this.assignedEmployee=assignedEmployee;}

}
