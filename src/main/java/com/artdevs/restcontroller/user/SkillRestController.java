package com.artdevs.restcontroller.user;

import java.util.ArrayList;
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
import com.artdevs.services.SkillService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class SkillRestController {
    @Autowired
    SkillService skillService;

    @Autowired
    SkillRepository skillRepository;

    @PostMapping("/skill")
    public ResponseEntity<Skill> postSkill(@RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillService.saveSkill(SkillMapper.convertToSkill(skillDTO)));
    }

    @GetMapping("/skill")
    public ResponseEntity<List<SkillDTO>> getSkill() {
        List<SkillDTO> listSkillDTO = new ArrayList<>();
        List<Skill> listSkill = skillRepository.findAll();
        for (Skill skill : listSkill) {
            listSkillDTO.add(SkillMapper.convertToSkillDTO(skill));
        }

        return ResponseEntity.ok(listSkillDTO);
    }
}
