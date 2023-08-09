package com.project.vo;
import java.util.Date;

import lombok.Data;
/*CREATE TABLE `board` (
  `board_idx` int primary key AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `title` varchar(100) NOT NULL,
  `contents` text NOT NULL,
  `insert_date`  Date NOT NULL DEFAULT current_timestamp,
  `view_cnt` int NOT NULL DEFAULT 0
);
select * from  board;*/

@Data
public class BoardVO {
	private int board_idx;//글번호(pk)
	private String id;//아이디
	private String title;//제목
	private String contents;//내용
	private Date   insert_date;//등록일
	private int view_cnt;	//조회수
}
