package integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.openclassrooms.starterjwt.SpringBootSecurityJwtApplication;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;

@SpringBootTest(classes = SpringBootSecurityJwtApplication.class)
@AutoConfigureMockMvc
public class TeacherControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private String authToken;

	private String stringUserId;

	private String registerJsonRequest;

	@BeforeEach
	public void Register_Log_Delete_User() throws Exception {
		authToken = null;
		stringUserId = null;
		registerJsonRequest = null;

		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setEmail("user@test.com");
		signupRequest.setFirstName("UserTest");
		signupRequest.setLastName("UserTest");
		signupRequest.setPassword("testpwd");

		registerJsonRequest = objectMapper.writeValueAsString(signupRequest);

		mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(registerJsonRequest))
				.andExpect(status().isOk());

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("user@test.com");
		loginRequest.setPassword("testpwd");
		String loginJsonRequest = objectMapper.writeValueAsString(loginRequest);

		MvcResult result = mockMvc
				.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(loginJsonRequest))
				.andExpect(status().isOk()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		authToken = objectMapper.readTree(responseBody).get("token").textValue();
		Integer userId = objectMapper.readTree(responseBody).get("id").intValue();
		stringUserId = String.valueOf(userId);
	}

	@AfterEach
	public void detele_User() throws Exception {
		mockMvc.perform(get("/api/user/{id}", stringUserId).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + authToken)) 
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("UserTest"))); 

		mockMvc.perform(delete("/api/user/{id}", stringUserId).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + authToken));
	}

	@Test
	public void getTeacherByIdTest() throws Exception {
		Long teacherId = 1L;

		mockMvc.perform(get("/api/teacher/{id}", teacherId).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + authToken)) 
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Margot"))); 
	}

	@Test
	public void getTeacherByIdTest_TeacherNotFound() throws Exception {
		Long teacherId = 3L;

		mockMvc.perform(get("/api/teacher/{id}", teacherId).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + authToken)) 
				.andExpect(status().isNotFound()); 
	}
	
	@Test
	public void getAllTeachersTest() throws Exception {
		mockMvc.perform(get("/api/teacher").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + authToken)) 
				.andExpect(status().isOk()).andExpect(jsonPath("[1].firstName", is("Hélène"))); 
	}
	
	@Test
	public void findById_InvalidIdFormat() throws Exception {
	    String invalidId = "invalid_id";

	    mockMvc.perform(get("/api/teacher/{id}", invalidId)
	            .contentType(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Bearer " + authToken))
	            .andExpect(status().isBadRequest());
	}

}
