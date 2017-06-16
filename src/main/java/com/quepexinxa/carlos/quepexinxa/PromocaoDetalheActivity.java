package com.quepexinxa.carlos.quepexinxa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class PromocaoDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao_detalhe);

        Intent intent = getIntent();
        ItemPromocao itemPromocao = (ItemPromocao) intent.getSerializableExtra(MainActivity.PROMOCAO);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PromocaoDetalheFragment promocaoDetalheFragment = new PromocaoDetalheFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.PROMOCAO, itemPromocao);
        promocaoDetalheFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_filme_detalhe, promocaoDetalheFragment);
        fragmentTransaction.commit();
    }
}
