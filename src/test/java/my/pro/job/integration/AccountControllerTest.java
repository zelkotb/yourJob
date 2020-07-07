package my.pro.job.integration;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import my.pro.job.YourJobApplication;
import my.pro.job.dto.AccountDTO;
import my.pro.job.entity.Account;

/**
 *
 * @author zakaria Elkotb
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = YourJobApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class AccountControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	public void registerTest() throws Exception {

		AccountDTO dto = new AccountDTO();
		dto.setUsername("zakaria");
		dto.setPassword("Password1");
		dto.setEmail("zak@gmail.com");
		List<String> roles = new ArrayList<>();
		roles.add("CANDIDATE");
		dto.setRoles(roles);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();
		String requestBody = writer.writeValueAsString(dto);
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/my/pro/job/account/register")
				.contentType("application/json").content(requestBody)).andReturn();
		Account resultDto = mapper.readValue(result.getResponse().getContentAsString(), Account.class);
		Assert.assertEquals("zakaria", resultDto.getUsername());
	}
}
