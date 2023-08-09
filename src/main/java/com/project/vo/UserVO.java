package com.project.vo;

import lombok.Data;
/*CREATE TABLE `user` (
  `user_idx` int PRIMARY KEY AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
);
*/
@Data
public class UserVO {
	private int user_idx;
	private String id;
	private String email;
	private String password;
	private String phone;
	private String name;
	
		
}
