package ly.generalassemb.drewmahrt.shoppinglistdetailview;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistdetailview.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.OnItemSelectedListener{

    private final String SELECTED = "SELECTED";
    private boolean mTwoChainz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //TWO CHAINZ
        if(findViewById(R.id.detail_fragment_container) != null){
            mTwoChainz = true;
        } else{ mTwoChainz = false; }

        //Setup the RecyclerView
        RecyclerView shoppingListRecyclerView = (RecyclerView) findViewById(R.id.shopping_list_recyclerview);

        ShoppingSQLiteOpenHelper db = ShoppingSQLiteOpenHelper.getInstance(this);
        List<ShoppingItem> shoppingList = db.getShoppingList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        shoppingListRecyclerView.setLayoutManager(linearLayoutManager);
        shoppingListRecyclerView.setAdapter(new ShoppingListAdapter(shoppingList,this));

    }

    @Override
    public void onItemSelected(int itemId){
        if (mTwoChainz) {
            DetailFragment detailFragment = DetailFragment.newInstance(itemId);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.detail_fragment_container,detailFragment);
            fragmentTransaction.commit();

        } else {
            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra(DetailFragment.ITEM_ID_KEY, itemId);
            startActivity(intent);
        }
    }


}
