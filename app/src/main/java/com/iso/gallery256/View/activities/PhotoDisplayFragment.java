package com.iso.gallery256.View.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iso.gallery256.R;
import com.iso.gallery256.View.adapters.PhotoAdapter;
import com.iso.gallery256.View.presenters.FragmentPresenter;

public class PhotoDisplayFragment extends Fragment
{
    private RecyclerView recycler;
    private FragmentPresenter presenter;
    private GridLayoutManager gridLayout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        presenter = new FragmentPresenter(this);

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
        this.recycler.setAdapter(new PhotoAdapter(this,
                presenter.getPhotos(getActivity(),
                        getActivity()
                                    .getIntent()
                                    .getExtras()
                                    .getString("name"))
                )
        );
    }

//    @Override
//    public void onResume()
//    {
//        super.onResume();
//
//    }



    @Override
    public void onStart()
    {
        super.onStart();
    }

}
