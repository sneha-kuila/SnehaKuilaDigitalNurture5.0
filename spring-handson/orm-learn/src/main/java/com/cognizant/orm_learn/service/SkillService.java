package com.cognizant.orm_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.orm_learn.model.Skill;
import com.cognizant.orm_learn.repository.SkillRepository;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Transactional
    public Skill getSkill(int id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Transactional
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }
}