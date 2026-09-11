package com.estagioprojeto.repository;

import com.estagioprojeto.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);

    @Query("SELECT p FROM Tutorial p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Tutorial> findAllByDescription(@Param("description") String description);


}
