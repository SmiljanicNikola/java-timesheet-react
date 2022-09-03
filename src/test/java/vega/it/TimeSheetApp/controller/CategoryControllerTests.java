package vega.it.TimeSheetApp.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.conditional.ThenAction;
import vega.it.TimeSheetApp.DTO.CategoryDTO;
import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Client;
import vega.it.TimeSheetApp.repository.CategoryRepository;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.service.CategoryService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;



@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	
	@MockBean
	private CategoryService categoryService;

	
	@MockBean
	private JWTRequestFilter jwtRequestFilter;
	
	
	@BeforeEach
    public void Init() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }   
	
    Category category1 = new Category(1, "categoryType1");
    Category category2 = new Category(2, "categoryType2");

	@Test
	public void getCategoriesPageable_success() throws Exception{
		
        ArrayList<Category> categories = new ArrayList<>(Arrays.asList(category1,category2));
        PageImpl<Category> categoriesPage = new PageImpl<Category>(categories);
        
        Mockito.when(categoryService.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(categoriesPage);
   

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$", notNullValue()));

	}
	
	@Test
	public void filterCategoryByLetterOfType_success() throws Exception{
		
	   ArrayList<Category> categories = new ArrayList<>(Arrays.asList(category1,category2));

       Mockito.when(categoryService.filterAllCategoriesByFirstLetter("c")).thenReturn(categories);
		
       mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/filterBy/c").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", notNullValue()))
		   	   .andExpect(jsonPath("$[0].type").value("categoryType1"))
		   	   .andExpect(jsonPath("$[1].type").value("categoryType2"));

	}
	
	@Test
	public void testSaveCategory_success() throws JsonProcessingException, Exception{
		
		Category newCategory = new Category("novaKategorija");
		
		Category savedCategory = new Category(3, "novaKategorija");
		
		Mockito.when(categoryService.save(Mockito.any(Category.class))).thenReturn(savedCategory);
		
		  mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
				  .content(asJsonString(newCategory))
				  .contentType(MediaType.APPLICATION_JSON))
          		  .andExpect(status().isCreated())
          		  .andExpect(jsonPath("$.type").value("novaKategorija"));
	}
	
	
	@Test
    public void testUpdateCategory_success() throws Exception {

    	Mockito.when(categoryService.findById(category1.getId())).thenReturn(category1);
    	    	
    	category1.setType("updatedCategory1");
    	
    	Category category2 = new Category(1, category1.getType());
    	
        Mockito.when(categoryService.save(category1)).thenReturn(category2);

		  mockMvc.perform(MockMvcRequestBuilders.put("/api/categories/1")
	               	.content(asJsonString(category1))
	               	.contentType(MediaType.APPLICATION_JSON))
		  			.andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.type").value("updatedCategory1"));
	
    }
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	        	throw new RuntimeException(e);
     }
 }

	
	
}
