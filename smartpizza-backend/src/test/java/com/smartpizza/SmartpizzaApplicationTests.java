package com.smartpizza;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartpizzaApplicationTests {

	@Test
	void contextLoads() {
	}
	
//	@Mock
//    private User userRepo;
//    @InjectMocks
//    private UserServiceImpl userService;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        
//        @Test
//        void testGetUserById() {
//            User user = new User();
//            user.setUserId(1L);
//            user.setName("Adarsh");
//            when(userRepo.findById(1L))
//                    .thenReturn(Optional.of(user));
//            User result = userService.getUserById(1L);
//            assertEquals("Adarsh", result.getName());
//        }
//        @Test
//        void testRegisterUser() {
//            User user = new User();
//            user.setName("John");
//            when(userRepo.save(user))
//                    .thenReturn(user);
//            User savedUser = userRepo.save(user);
//            assertEquals("John", savedUser.getName());
//        }
//    
//
//}
}
