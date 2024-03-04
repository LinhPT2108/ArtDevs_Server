package com.artdevs.dto.CustomDTO;

import java.util.Date;
import java.util.List;

import com.artdevs.domain.entities.user.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGetRelationDTO {
	 private String userId;

	    private String username;

	    private String profilePicUrl;
	    
	    private String fullname;
}
