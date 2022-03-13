package emlakburada.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;
import emlakburada.repository.BannerRepository;

@SpringBootTest
public class BannerServiceTest {
	
	@InjectMocks
	private BannerService bannerService;
	
	@Mock
	private BannerRepository bannerRepository;
	
	@BeforeEach
	void init() {
		
		Mockito.when(bannerRepository.findAll()).thenReturn(prepare());
		Mockito.when(bannerRepository.getById(1)).thenReturn(bannerPrepare());
		Mockito.doNothing().when(bannerRepository).deleteById(1);
	}
	private List<Banner>  prepare() {
		List<Banner> allBanners=new ArrayList<Banner>();
		
		allBanners.add(new Banner(1, 1231, "+9050505",3 ));
		allBanners.add(new Banner(2, 1231, "+9050505",3 ));
		
		return allBanners;
	}
	
	
	@Test
	void allBanners() {
		
		List<BannerResponse> banners=bannerService.getAllBanner();
		
		assertNotNull(banners);
		assertThat(banners.size()).isNotZero();
	}
	
	@Test	
	void saveBanner() {
		
		Banner banner2=new Banner( 1,1, "0+2221", 5);
		Mockito.when(bannerRepository.save(any())).thenReturn(banner2);
	
		BannerResponse banner=bannerService.saveBanner(prepareRequest());
		
		assertNotNull(banner);
		assertEquals("0+2221",banner.getPhone());
		
	}
	
	private Banner bannerPrepare() {
		
		
		return new Banner(1, 1,"0+2221",5);
	}
	
	private BannerRequest prepareRequest() {
		
		
		return  new BannerRequest(1,"0+2221", 5);
	}
	
	
	@Test
	void findBanner() {
		
		BannerResponse response=bannerService.findBannerById(1);
		
		assertEquals("0+2221", response.getPhone());
		
	}
	
	@Test
	void deleteBanner() {
	
		Boolean response=bannerService.deleteBannerById(1);
		
		assertEquals(true, response);
	}
}
