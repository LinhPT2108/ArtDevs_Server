package com.artdevs.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StarDTO {
	private int id;
	private String content;
	private double star;
	private String userSendId;
	private String userReceiveId;
}
