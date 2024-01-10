package com.artdevs.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Skill;
import com.artdevs.dto.user.SkillDTO;
import com.artdevs.mapper.SkillMapper;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.services.impl.user.SkillServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class SkillRestController {
    @Autowired
    SkillServiceImpl skillServiceImpl;

    @PostMapping("/skill")
    public ResponseEntity<Skill> postSkill(@RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillServiceImpl.saveSkill(SkillMapper.convertToSkill(skillDTO)));
    }

    @GetMapping("/skill")
    public ResponseEntity<List<Skill>> getSkill() {
        return ResponseEntity.ok(skillServiceImpl.findAll());
    }
}
