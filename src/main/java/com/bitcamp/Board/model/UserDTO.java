package com.bitcamp.Board.model;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String username;
	private String password;
	private String nickname;
	
	public boolean equals(Object o) {
		if(o instanceof UserDTO) {
			UserDTO u = (UserDTO) o;
			return id == u.id;
		}
		return false;
	}
	
}
