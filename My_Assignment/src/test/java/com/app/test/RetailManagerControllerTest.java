package com.app.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.app.repository.Application;
import com.app.repository.RetailManagerRepository;
import com.app.repository.Shop;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RetailManagerControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private Shop shop;
	private List<Shop> shopList = new ArrayList<Shop>();
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	@Autowired
	private MockMvc mvc;
	@Autowired
	private RetailManagerRepository repository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	/*@Before
	public void setup() throws Exception {
		this.shop = repository.save(new Shop("ShopOne", "Pimpri", "411018"));
		shopList.add(shop);
	}*/
	
	@Test
	public void createShop() throws Exception {
		String bookmarkJson = json(new Shop("ShopTwo", "Kolkata", "700084"));

		this.mvc.perform(post("/shops").contentType(contentType).content(bookmarkJson)).andExpect(status().isOk());
	}
	
	@Test
	public void getShops() throws Exception {
			this.shop = repository.save(new Shop("ShopOne", "Pimpri", "411018"));
			shopList.add(shop);
		  this.mvc.perform(get("/shops"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(contentType))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[1].shopName",is(this.shopList.get(0).getShopName())))
	                .andExpect(jsonPath("$[1].shopAddress", is(this.shopList.get(0).getShopAddress())))
	                .andExpect(jsonPath("$[1].shopZipCode", is(this.shopList.get(0).getShopZipCode())))
	                ;
	    }


	
	
	
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
