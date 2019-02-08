package ftn.isa.projectISA.service;

import static org.mockito.Mockito.when;

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

import ftn.isa.model.AvioCompany;
import ftn.isa.projectISA.constants.AvioCompanyConstants;
import ftn.isa.repository.AvioCompanyRepository;
import ftn.isa.service.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvioCompanyServiceTest {
	
	@Mock
	private AvioCompanyRepository avioCompanyRepositoryMock;
	
	@Mock
	private AvioCompany avioCompanyMock;
	
	@InjectMocks
	private AvioCompanyService avioCompanyService;

	
	
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

	@SuppressWarnings("deprecation")
	@Test
	public void testGetAvgRating() {
		
		when(avioCompanyRepositoryMock.getOne(1L)).thenReturn(AvioCompanyConstants.getAvioCompany());
		when(avioCompanyRepositoryMock.getOne(2L)).thenReturn(AvioCompanyConstants.getAvioCompany2());
		
		
		double result = avioCompanyService.getAvgRating(1L);
		double resultNew = avioCompanyService.getAvgRating(2L);
		
		org.junit.Assert.assertEquals(3.5,result);
		
		org.junit.Assert.assertEquals(0,resultNew);
		
	}

}
