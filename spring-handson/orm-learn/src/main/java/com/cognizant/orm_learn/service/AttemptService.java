package com.cognizant.orm_learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.repository.AttemptRepository;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    public List<Attempt> getAttemptDetail() {
        return attemptRepository.getAttemptDetail();
    }

    public List<Attempt> getAllAttemptsNative() {
        return attemptRepository.getAllAttemptsNative();
    }
}