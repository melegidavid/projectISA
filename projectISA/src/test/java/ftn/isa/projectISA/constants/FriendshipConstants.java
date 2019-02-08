package ftn.isa.projectISA.constants;

import ftn.isa.model.User;

public class FriendshipConstants {
	
	public static User getUser1() {
		return new User("User1","Pass1","mail@mail.com","Name 1", "Last name 1","City 1","06333333",true);
	}
	
	public static User getUser2() {
		return new User("User2","Pass2","mail@mail.com","Name 2", "Last name 2","City 2","06333333",true);
	}

}
