package ftn.isa.projectISA.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.isa.model.Friendship;
import ftn.isa.projectISA.constants.FriendshipConstants;
import ftn.isa.repository.FriendshipRepository;
import ftn.isa.service.FriendshipService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipServiceTest {
	
	@Mock
	private FriendshipRepository friendshipRepositoryMock;
	
	@Mock
	private Friendship friendshipMock;
	
	@InjectMocks
	private FriendshipService friendshipService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllFriendships() {
		when(friendshipRepositoryMock.findAll()).thenReturn(Arrays.asList(new Friendship(FriendshipConstants.getUser1(),FriendshipConstants.getUser2(),true)));
		List<Friendship> prijateljstva = friendshipService.getAllFriendships();
		assertThat(prijateljstva).hasSize(1);
	}


}
