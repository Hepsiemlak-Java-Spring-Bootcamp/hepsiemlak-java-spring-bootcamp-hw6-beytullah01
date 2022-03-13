package emlakburada.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.UserResponse;
import static org.mockito.ArgumentMatchers.any;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.model.enums.UserType;
import emlakburada.repository.AdvertRepository;

@SpringBootTest
public class AdvertServiceTest {

	@InjectMocks
	private AdvertService advertService;
	
	@Mock
	private AdvertRepository advertRepository;
	
	@BeforeEach
	void init() {
		
		Mockito.when(advertRepository.findAll()).thenReturn(prepare());
		Mockito.when(advertRepository.save(any())).thenReturn(advertPrepare());
		Mockito.when(advertRepository.getById(1)).thenReturn(advertPrepare());
		
	}

	private List<Advert> prepare() {
		List<Advert> advertList=new ArrayList<Advert>();
		advertList.add(new Advert(1, 122, "adsa", new User(), new BigDecimal(12), 1, false, false, null, false));
		advertList.add(new Advert(1, 122, "adsa", new User(), new BigDecimal(12), 1, false, false, null, false));
	
		return advertList;
	}
	
	@Test
	void getAllAdverts() {
		
		List<AdvertResponse> allAdvert=advertService.getAllAdverts();
		
		assertNotNull(allAdvert);
		assertThat(allAdvert.size()).isNotZero();
	}
	
	
	@Test
	void saveAdvert() {
		
		AdvertResponse response =advertService.saveAdvert(AdvertRequestPrepare());
		assertEquals("baslik1", response.getBaslik());
		
	}

	private AdvertRequest AdvertRequestPrepare() {
		AdvertRequest request=new AdvertRequest();
		request.setAdvertId(0);
		request.setBaslik("baslik");
		request.setSuresi(0);
		request.setUser(new User(1, UserType.INDIVIDUAL, "beytullah", "email"));
		return  request;
	}
	
	private Advert advertPrepare() {
	
		Advert advert =new Advert();
		advert.setAdvertId(1);
		advert.setAdvertNo(1234);
		advert.setBaslik("baslik1");
		advert.setSuresi(0);
		advert.setUser(new User(1, UserType.INDIVIDUAL, "beytullah", "email"));
		return  advert;
	}
	
	@Test
	void findAdvert() {
		
		AdvertResponse response=advertService.getAdvertByAdvertId(1);
		
		assertEquals("baslik1", response.getBaslik());
		
		
	}

	
}
