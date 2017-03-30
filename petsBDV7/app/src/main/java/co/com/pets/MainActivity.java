package co.com.pets;

import android.support.design.widget.TabLayout;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.pets.adapter.PageAdapter;
import co.com.pets.fragment.MascotasFavoritas;
import co.com.pets.fragment.PerfilFragment;
import co.com.pets.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();


        if(toolbar != null){
            setSupportActionBar(toolbar);
        }



    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    //ir a activityMascotasFavoritas
    public void mascotasFavoritas(){
        Intent intent = new Intent(this, MascotasFavoritas.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.mAbout:
                Intent intent = new Intent(this, ActivityAbout.class);
                startActivity(intent);
                break;

            case R.id.mContacto:
                Intent i = new Intent(this, ActivityContacto.class);
                startActivity(i);
                break;

            case R.id.mFavoritos:
                Toast.makeText(this, "Mascotas Favoritas", Toast.LENGTH_SHORT).show();
                mascotasFavoritas();
                break;

            case R.id.mCuentaInstagram:
                intent = new Intent(this, ConfigurarCuenta.class);
                startActivity(intent);
                break;

            case R.id.mRecibirNotificaciones:
                intent = new Intent(this, ActivityRecibirNotificacion.class);
                startActivity(intent);
                break;

        }


        return super.onOptionsItemSelected(item);
    }



}
