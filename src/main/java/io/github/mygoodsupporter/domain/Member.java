package io.github.mygoodsupporter.domain;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;


@Data
public class Member {
	
	private String m_k_number;
	private String m_id;
	private String m_password;
	private String m_name;
	private String m_phone;
	private String m_email;
	private String m_type;
	private List<String> authorities = new ArrayList<>();
	private int enabled;

}