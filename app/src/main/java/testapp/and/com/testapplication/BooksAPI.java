package testapp.and.com.testapplication;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by developer on 4/4/16.
 */
public interface BooksAPI
{

        /*Retrofit get annotation with our URL
           And our method that will return us the list ob Book
        */
        @GET("/RetrofitExample/books.json")
        public void getBooks(Callback<List<Book>> response);
}
