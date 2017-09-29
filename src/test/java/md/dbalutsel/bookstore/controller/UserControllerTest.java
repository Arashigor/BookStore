package md.dbalutsel.bookstore.controller;

import com.google.gson.Gson;
import md.dbalutsel.bookstore.config.TestConfig;
import md.dbalutsel.bookstore.config.TestDataConfig;
import md.dbalutsel.bookstore.config.TestSecurityConfig;
import md.dbalutsel.bookstore.model.Role;
import md.dbalutsel.bookstore.model.User;
import md.dbalutsel.bookstore.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static md.dbalutsel.bookstore.data.Constants.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, TestDataConfig.class, TestSecurityConfig.class})
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    private User user;

    @Autowired
    private Role role;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        role.setId(ALLOWED_ID);
        role.setName(USER_ROLE);

        user.setUsername(ALLOWED_USERNAME+"1");
        user.setPassword(ALLOWED_PASSWORD);
        user.setEmail("a"+ALLOWED_EMAIL);
        user.setRoles(role);
    }

    @Test
    @Ignore
    public void userRegistrationTest() throws Exception {
        mockMvc.perform(post("/users").contentType(APPLICATION_JSON).content(new Gson().toJson(user)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        verify(userService, times(1)).save(user);
        verifyNoMoreInteractions(userService);
    }
}