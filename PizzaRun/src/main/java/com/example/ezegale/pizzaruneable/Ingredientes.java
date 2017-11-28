package com.example.ezegale.pizzaruneable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**6646784885
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Ingredientes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Ingredientes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ingredientes extends Fragment  implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int VISIBLE = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    ImageButton BtnChile;
    ImageButton btnaceituna;
    ImageButton btncebolla;
    ImageButton btnchampinon;
    ImageButton btnjamon;
    ImageButton btnpina;
    ImageButton btnpeperoni;
    ImageButton btnqueso;
    ImageButton btnsalchicha;
    ImageButton btntocino;
    ImageView aceituna;
    ImageView cebolla;
    ImageView champinon;
    ImageView chile;
    ImageView jamon;
    ImageView pina;
    ImageView peperoni;
    ImageView queso;
    ImageView salchicha;
    ImageView tocino;

    private OnFragmentInteractionListener mListener;



    public Ingredientes() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ingredientes.
     */
    // TODO: Rename and change types and number of parameters
    public static Ingredientes newInstance(String param1, String param2) {
        Ingredientes fragment = new Ingredientes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredientes, container, false);


    }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ingredientes, container, false);
        BtnChile = (ImageButton) view.findViewById(R.id.BtnChile);
        BtnChile.setOnClickListener(this);

        btnaceituna = (ImageButton) view.findViewById(R.id.BtnAceituna);
        btnaceituna.setOnClickListener(this);

        btncebolla = (ImageButton) view.findViewById(R.id.BtnCebolla);
        btncebolla.setOnClickListener(this);

        btnchampinon = (ImageButton) view.findViewById(R.id.BtnChampiñon);
        btnchampinon.setOnClickListener(this);

        btnjamon = (ImageButton) view.findViewById(R.id.BtnJamon);
        btnjamon.setOnClickListener(this);

        btnpina = (ImageButton) view.findViewById(R.id.BtnPina);
        btnpina.setOnClickListener(this);

        btnpeperoni = (ImageButton) view.findViewById(R.id.BtnPeperoni);
        btnpeperoni.setOnClickListener(this);

        btnqueso = (ImageButton) view.findViewById(R.id.BtnQueso);
        btnqueso.setOnClickListener(this);

        btnsalchicha = (ImageButton) view.findViewById(R.id.BtnSalchicha);
        btnsalchicha.setOnClickListener(this);

        btntocino = (ImageButton) view.findViewById(R.id.BtnTocino);
        btntocino.setOnClickListener(this);

        chile = view.findViewById(R.id.imageView7);
        aceituna = view.findViewById(R.id.imageView);
        cebolla = view.findViewById(R.id.imageView2);
        champinon = view.findViewById(R.id.imageView5);
        jamon = view.findViewById(R.id.imageView6);
        pina = view.findViewById(R.id.imageView8);
        peperoni = view.findViewById(R.id.imageView4);
        queso = view.findViewById(R.id.imageView9);
        salchicha = view.findViewById(R.id.imageView10);
        tocino = view.findViewById(R.id.imageView11);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
            switch(v.getId()){
                case R.id.BtnChile:
                    cambiarVisibilidad(chile);
                break;
                case R.id.BtnAceituna:
                    cambiarVisibilidad(aceituna);
                break;
                case R.id.BtnCebolla:
                    cambiarVisibilidad(cebolla);
                break;
                case R.id.BtnChampiñon:
                    cambiarVisibilidad(champinon);
                break;
                case R.id.BtnJamon:
                    cambiarVisibilidad(jamon);
                break;
                case R.id.BtnPina:
                    cambiarVisibilidad(pina);
                break;
                case R.id.BtnPeperoni:
                    cambiarVisibilidad(peperoni);
                break;
                case R.id.BtnQueso:
                    cambiarVisibilidad(queso);
                break;
                case R.id.BtnSalchicha:
                    cambiarVisibilidad(salchicha);
                break;
                case R.id.BtnTocino:
                    this.cambiarVisibilidad(tocino);
                break;

            }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void cambiarVisibilidad(ImageView image){
        if(image.getVisibility()==View.INVISIBLE){
            image.setVisibility(View.VISIBLE);
        }else{
            image.setVisibility(View.INVISIBLE);
        }
    }

}

