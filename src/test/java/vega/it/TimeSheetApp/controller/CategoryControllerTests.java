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

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.repository.TeamMemberRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.security.TokenUtils;
import vega.it.TimeSheetApp.service.CategoryService;
import vega.it.TimeSheetApp.service.ClientService;
import vega.it.TimeSheetApp.service.CountryService;
import vega.it.TimeSheetApp.service.DayService;
import vega.it.TimeSheetApp.service.TeamMemberService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
	public void getCategoriesPageable() throws Exception{
        ArrayList<Category> categories = new ArrayList<>(Arrays.asList(category1,category2));
        PageImpl<Category> categoriesPage = new PageImpl<Category>(categories);
        
        Mockito.when(categoryService.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(categoriesPage);
        //Mockito.when(teamMemberService.findAllTeamMembersPaginate(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(teamMembersPage);

        

        mockMvc.perform(MockMvcRequestBuilders.get("/api/categories/paginate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
}
