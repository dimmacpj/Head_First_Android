package com.example.pizza;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaMaterialFragment extends Fragment {


    public PizzaMaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRcycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza_material, container, false);
        String [] pizzaNames = new String[Pizza.pizzas.length];
        for(int i = 0; i < pizzaNames.length; i ++){
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        int[] pizzaImageIds = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImageIds.length; i ++){
            pizzaImageIds[i] = Pizza.pizzas[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImageIds);
        pizzaRcycler.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        pizzaRcycler.setLayoutManager(llm);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, position);
                startActivity(intent);
            }
        });
        return pizzaRcycler;
    }

}
