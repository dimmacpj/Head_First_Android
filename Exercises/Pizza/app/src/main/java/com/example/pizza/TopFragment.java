package com.example.pizza;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    private View view;
    private RecyclerView pizzaRecycler, pastaRecycler;
    private GridLayoutManager pizzaGLM, pastaGLM;
    private CaptionedImagesAdapter pizzaAdapter, pastaAdapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);
        String[] pizzaNames = new String[2];
        for (int i = 0; i < 2; i ++){
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }
        String[] pastaNames = new String[2];
        for (int i = 0; i < 2; i ++){
            pastaNames[i] = Pasta.pastas[i].getName();
        }
        int[] pizzaImages = new int[2];
        for (int i = 0; i < 2; i ++){
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }
        int[] pastaImages = new int[2];
        for (int i = 0; i < 2; i ++){
            pastaImages[i] = Pasta.pastas[i].getImageId();
        }

        pizzaRecycler = (RecyclerView) view.findViewById(R.id.top_pizza_recycler);
        pastaRecycler = (RecyclerView) view.findViewById(R.id.top_pasta_recycler);

        pizzaAdapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pastaAdapter = new CaptionedImagesAdapter(pastaNames, pastaImages);

        pizzaRecycler.setAdapter(pizzaAdapter);
        pastaRecycler.setAdapter(pastaAdapter);

        pizzaGLM = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(pizzaGLM);

        pastaGLM = new GridLayoutManager(getActivity(), 2);
        pastaRecycler.setLayoutManager(pastaGLM);

        pizzaAdapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, position);
                startActivity(intent);
            }
        });

        pastaAdapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PastaDetailActivity.class);
                intent.putExtra(PastaDetailActivity.EXTRA_PASTANO, position);
                startActivity(intent);
            }
        });

        return view;
    }

}
