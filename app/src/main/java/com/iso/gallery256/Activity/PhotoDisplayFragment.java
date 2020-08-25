package com.iso.gallery256.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iso.gallery256.Model.Photo;
import com.iso.gallery256.R;
import com.iso.gallery256.adapters.PhotoAdapter;
import com.iso.gallery256.Presenter.FragmentPresenter;

import java.util.ArrayList;

public class PhotoDisplayFragment extends Fragment
{
    private RecyclerView recycler;
    private FragmentPresenter presenter;
    private GridLayoutManager gridLayout;
    private ArrayList<Photo> photos = null;
    private PhotoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.photos = new ArrayList<Photo>();
        this.adapter = new PhotoAdapter(this, this.photos);
        presenter = new FragmentPresenter(this, this.adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "Fragment created");
        return inflater.inflate(R.layout.fragment_photo_layout, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        this.recycler = getView().findViewById(R.id.photo_layout);
        this.gridLayout = new GridLayoutManager(getActivity(), 3); //set this dynamically
        this.recycler.setLayoutManager(gridLayout);
        presenter.getPhotos(getContext(), getActivity().getIntent().getStringExtra("name"), this.photos);
        this.recycler.setAdapter(this.adapter);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("Fragment", this.photos.size() + " " + adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }


}
