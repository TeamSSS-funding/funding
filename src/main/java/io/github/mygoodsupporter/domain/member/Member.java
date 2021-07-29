package io.github.mygoodsupporter.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter @Setter

public class Member {
	
	private String id;
	private String password;
	private String email;
	private String name;
	private String phone;



	private List<Authority> authorities = new ArrayList<>();
	private int enabled;

	@Override
	public String toString() {
		return "Member{" +
				"id='" + id + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
//				", address=" + address +
				'}';
	}
}