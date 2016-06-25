package autoscout.it.autoscout2.rest;

import autoscout.it.autoscout2.model.Post;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RestAPI {
    
    String API_ENDPOINT = "http://jsonplaceholder.typicode.com/";

    @GET("posts/{id}")
    Observable<Post> getPost(@Path("id") int id);

}