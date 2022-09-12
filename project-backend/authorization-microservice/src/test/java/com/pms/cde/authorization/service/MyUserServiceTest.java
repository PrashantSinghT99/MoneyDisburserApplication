package com.pms.cde.authorization.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pms.cde.authorization.dao.UserDao;
import com.pms.cde.authorization.model.MyUserDetail;
import com.pms.cde.authorization.model.User;
import com.pms.cde.authorization.service.MyUserService;

@SpringBootTest(classes = MyUserServiceTest.class)
//@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
class MyUserServiceTest {

	@InjectMocks
	private MyUserService service;

	@Mock
	UserDao dao;

	@Mock
	PasswordEncoder encoder;

//	@Before
//	public void setup(){
//	    MockitoAnnotations.initMocks(this); //without this you will get NPE
//	}

//	@Test
//	void contextLoads() {
//		assertNotNull(service);
//	}

	@Test
	void UsernameNotFoundExceptionTest() {
		when(dao.getUserByUsername("TestUser")).thenReturn(null);
		Throwable thrown = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("abc"));
		assertEquals("No Users Available With abc !!!....", thrown.getMessage());

	}

	@Test
	void CorrectDetailsTest() {
		User user = new User(3L, "Tester", "Tester@1234", "Admin");
		when(dao.getUserByUsername("Tester")).thenReturn(user);
		UserDetails userDetails = service.loadUserByUsername("Tester");

		User user1 = new User(3L, "Tester", userDetails.getPassword(), "Admin");
		UserDetails details = new MyUserDetail(user1);
		assertThat(userDetails).usingRecursiveComparison().isEqualTo(details);
	}

}
