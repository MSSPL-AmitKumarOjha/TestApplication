package testapp.and.com.testapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {


    public static final String ROOT_URL = "http://ocean-dev.com/secondapi/members/fetchOrganization.json";

    ListView lv ;

    BooksListAdapter listAdapter;
    ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listViewBooks);

        listAdapter = new BooksListAdapter(MainActivity.this,books);
        getBooks();
    }

    private void getBooks(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        BooksAPI api = adapter.create(BooksAPI.class);

        //Defining the method
        api.getBooks(new Callback<List<Book>>() {
            @Override
            public void success(List<Book> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();
                Log.e("response", response.getReason());
                //Storing the data in our list
                books = (ArrayList<Book>) list;
                lv.setAdapter(listAdapter);
                //Calling a method to show the list
                //showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
                Log.e("error",error.getResponse().getReason());
            }
        });
    }


}
