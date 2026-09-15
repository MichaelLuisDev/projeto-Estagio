package com.estagioprojeto.service;

import com.estagioprojeto.dto.TutorialDto;
import com.estagioprojeto.model.Tutorial;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    List<TutorialDto> findPorPublicacao (boolean opcao);
    void deletarPorId(long id);
    List<TutorialDto> chamarTodosPorDescricao(String description);
    TutorialDto atualizarPorId(long id, TutorialDto tutorialDto);
    TutorialDto criarTutorial(TutorialDto tutorial);
    TutorialDto localizarPorId(long id);
    List<TutorialDto> localizarTodos(String title);

}
