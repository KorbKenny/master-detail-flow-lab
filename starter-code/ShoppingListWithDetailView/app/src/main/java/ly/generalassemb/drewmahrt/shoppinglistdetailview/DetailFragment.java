package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 Instance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SHOP_ITEM_CLICKED = "param1";
    public static final String ITEM_ID_KEY = "itemIdKey";
    private ShoppingItem mSelectedItem;
    private int mSelectedId;

    // TODO: Rename and change types of parameters
    private String mShopItemClicked;
    public DetailFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int itemId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_ID_KEY, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectedId = getArguments().getInt(ITEM_ID_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView name = (TextView) view.findViewById(R.id.detail_name);
        TextView description = (TextView) view.findViewById(R.id.detail_description);
        TextView price = (TextView) view.findViewById(R.id.detail_price);
        TextView category = (TextView) view.findViewById(R.id.detail_category);

        mSelectedItem = ShoppingSQLiteOpenHelper.getInstance(getContext()).getShoppingItemById(mSelectedId);

        // Populate the TextViews
        name.setText(mSelectedItem.getName());
        description.setText(mSelectedItem.getDescription());
        category.setText(mSelectedItem.getType());

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double priceValue = Double.valueOf(mSelectedItem.getPrice());
        price.setText(currencyFormat.format(priceValue));



    }
}











//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}
