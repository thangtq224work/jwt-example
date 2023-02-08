package com.application.model.response;

import java.util.Date;

import com.application.domain.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
	private Long id;
	private String value;
	private Account createBy;
	private Account updateBy;
	private Date updateTime;
	private Date createTime;

}
