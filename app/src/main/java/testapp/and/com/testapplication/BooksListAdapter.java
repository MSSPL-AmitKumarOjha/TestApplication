package testapp.and.com.testapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 4/4/16.
 */
public class BooksListAdapter extends BaseAdapter {

    ArrayList <Book> books;
    LayoutInflater lInflater;
    Activity activity;

    public BooksListAdapter(Activity activity,ArrayList<Book> books)
    {
        this.activity = activity;
        this.books = books;
        lInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder
    {
        TextView bookName;
    }
    ViewHolder viewHolder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;

        if(vi == null)
        {
            viewHolder = new ViewHolder();
            vi = lInflater.inflate(R.layout.list_cell, parent, false);
            viewHolder.bookName = (TextView) vi.findViewById(R.id.bookName);
            vi.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) vi.getTag();
        }

        viewHolder.bookName.setText(books.get(position).getName());

        return vi;
    }
}
