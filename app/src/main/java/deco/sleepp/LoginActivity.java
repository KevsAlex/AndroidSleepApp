package deco.sleepp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


import deco.sleepp.Fragments.LoginDoctorFrag;
import deco.sleepp.Fragments.LoginPacienteFragment;
import deco.sleepp.Utils.Utils;

public class LoginActivity extends AppCompatActivity {


    private LoginPacienteFragment mLoginPacienteFragment;
    private LoginDoctorFrag mDoctorFragment;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (!Utils.getIDPACIENTE(this).equals("")){
            Intent intent = new Intent(this,MenuPaciente.class);
            startActivity(intent);
        }
        mViewPager = findViewById(R.id.viewPager);
        mLoginPacienteFragment = new LoginPacienteFragment();
        mDoctorFragment = new LoginDoctorFrag();
        mFragments = new ArrayList<>();
        mFragments.add(mLoginPacienteFragment);
        mFragments.add(mDoctorFragment);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(adapter);


        //FragmentManager manager = getSupportFragmentManager();
        //FragmentTransaction transaction= manager.beginTransaction();
        //transaction.replace(R.id.fragmentCOntainerLogin,mLoginPacienteFragment);
        //transaction.commit();
    }

    public class MyAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> mFragments;

        public MyAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
