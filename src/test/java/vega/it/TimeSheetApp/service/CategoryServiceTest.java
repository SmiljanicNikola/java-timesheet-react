package vega.it.TimeSheetApp.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import vega.it.TimeSheetApp.model.Category;
import vega.it.TimeSheetApp.model.Roles;
import vega.it.TimeSheetApp.model.TeamMember;
import vega.it.TimeSheetApp.repository.CategoryRepository;
import vega.it.TimeSheetApp.security.JWTRequestFilter;
import vega.it.TimeSheetApp.service.implementation.CategoryServiceImpl;

@WebMvcTest(CategoryServiceImpl.class)
public class CategoryServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
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
	public void testGetAllCategoriesPaginate_success() {
		
		 ArrayList<Category> categories = new ArrayList<>(Arrays.asList(category1,category2));
	     PageImpl<Category> categoryPage = new PageImpl<Category>(categories);
		
		 Mockito.when(categoryRepository.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(categoryPage);

	     assertThat(categoryPage.getNumberOfElements(), is(2));
	     assertThat(categoryPage.getTotalPages(), is(1));


	}
	
	@Test
	public void testCategoryById() {
		 Mockito.when(categoryRepository.existsById(1)).thenReturn(true);

	     assertThat(category1.getId(), is(1));
	}
	
	@Test
	public void testSaveTeamMember() {
		
		Category newCategory = new Category("newTypeCategory");	
		
	    Category savedCategory = new Category(1,"newTypeCategory");
		
		Mockito.when(categoryRepository.save(newCategory)).thenReturn(savedCategory);

	    assertThat(savedCategory.getId(), is(1));
	    assertThat(savedCategory.getType(), is("newTypeCategory"));
	    

	}
	
	@Test
	public void testFilterCategoriesByType() {
		
		Category category3 = new Category(3, "specificType1");
	    Category category4 = new Category(4, "specificType2");
	    Category category5 = new Category(5, "NonSpeficiType3");


		ArrayList<Category> filteredCategories = new ArrayList<>(Arrays.asList(category3, category4));
		
		Mockito.when(categoryRepository.filterAllCategoriesByFirstLetter("spe")).thenReturn(filteredCategories);
		 
	    assertThat(filteredCategories.size(), is(2));

	}
	

}
