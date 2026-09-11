package com.estagioprojeto.service;

import com.estagioprojeto.model.Tutorial;

import java.util.List;

public interface TutorialService {
    List<Tutorial> findPorPublicacao (boolean opcao);
    void deletarPorId(long id);
    List<Tutorial> chamarTodosPorDescricao(String description);
    //void deletarTudo();
}
