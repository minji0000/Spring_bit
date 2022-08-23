package com.bitcamp.Board.model;

import java.sql.Timestamp;
import java.util.Calendar;

import lombok.Data;

@Data
public class BoardDTO {
	private int id;
	private int writerId;
	private String title;
	private String content;
	private Calendar writtenDate;
	private Calendar updatedDate;
	
	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = Calendar.getInstance();
		this.writtenDate.setTime(writtenDate);
	}
	
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = Calendar.getInstance();
		this.updatedDate.setTime(updatedDate);
	}
	
	public boolean equals(Object o) {
		if(o instanceof BoardDTO) {
			BoardDTO b = (BoardDTO) o;
			return id == b.id;
		}
		return false;
	}

}
