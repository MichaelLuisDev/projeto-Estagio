package com.estagioprojeto.service;

import com.estagioprojeto.model.Tutorial;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    List<Tutorial> findPorPublicacao (boolean opcao);
    void deletarPorId(long id);
    List<Tutorial> chamarTodosPorDescricao(String description);
    Tutorial atualizarPorId(Tutorial tutorial, long id);
    Tutorial criarTutorial(Tutorial tutorial);
    Optional<Tutorial> localizarPorId(long id);
    List<Tutorial> localizarTodos(String title);

}
