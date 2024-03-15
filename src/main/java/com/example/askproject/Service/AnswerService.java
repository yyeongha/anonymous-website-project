package com.example.askproject.Service;

import java.util.*;

import com.example.askproject.Model.DTO.AnswerDTO;

public interface AnswerService {
    public List<AnswerDTO> findAllByAnswerFrom(String answerTo);
    public AnswerDTO findByAnswerQuestionId(Long answerQuestionId);
    public void deleteByAnswerId(Long AnswerId);
    public void insertAnswer(AnswerDTO answerDTO);
    public List<Map<String, Object>> countAnswerByUserId();
}
