package io.rajivsingh.moviecatalogservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate template;
	
	
	@GetMapping("/{userId}")
	public List<Catalog> getCatalog(@PathVariable("userId")String userId)
	{
		
		//Rest template, using which we call the url and get data
		
		//RestTemplate template = new RestTemplate(); commented because it's autowired above
		
		//Creating the list for catalogs
		ArrayList<Catalog> catalogList= new ArrayList<Catalog>();
		
		
		//should be later replaced by api call to rating microservice
//		List<Rating>  ratings = Arrays.asList(
//				new Rating("Ra1",7),
//				new Rating("HWG90",9)
//				);
		
		UserRating userRating=template.getForObject("http://localhost:8095/rating/users/"+userId, UserRating.class);
		
		
		//For each movie id, call movie-info service and get details
		
		for(Rating tempR : userRating.getUserRating())
		{
			Movie movie=template.getForObject("http://localhost:8085/movie/" + tempR.getMovieId(), Movie.class);
			catalogList.add(new Catalog(movie.getName(),"This is description",tempR.getRating()));
		}
		
		
		return catalogList;

	}
}
