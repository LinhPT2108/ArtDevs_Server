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
import com.artdevs.dto.SkillDTO;
import com.artdevs.mapper.SkillMapper;
import com.artdevs.repositories.user.SkillRepository;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class SkillRestController {
    @Autowired
    SkillRepository skillRepository;

    @PostMapping("/skill")
    public ResponseEntity<Skill> postSkill(@RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillRepository.save(SkillMapper.convertToSkill(skillDTO)));
    }

    @GetMapping("/skill")
    public ResponseEntity<List<Skill>> getSkill() {
        return ResponseEntity.ok(skillRepository.findAll());
    }
}
