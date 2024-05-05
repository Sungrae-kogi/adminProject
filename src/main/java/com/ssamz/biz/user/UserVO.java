package com.ssamz.biz.user;

//메소드 호출시 호출되는 메소드의 매개변수 개수와 타입만 일치하면 실행이되어버리기때문에 VO 클래스를 작성하여 여러개의 매개변수를 사용하는 메소드에 대해서 매개변수를 하나로 통합하는 역할.
public class UserVO {
	//private 멤버 변수
	//외부에서 직접 멤버 변수에 접근할 수 없도록. 
	//그리고 멤버 변수에 접근하도록 getter, setter메소드 작성
	private String id;
	private String password;
	private String name;
	private String role;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
}
