package com.samruddhi.resourceallocationsystem.repository;
import com.samruddhi.resourceallocationsystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
