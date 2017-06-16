package com.quepexinxa.carlos.quepexinxa;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private PromocaoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final ArrayList<ItemPromocao> arrayList = new ArrayList<>();
        adapter = new PromocaoAdapter(getContext(), arrayList);
        final ListView listView = (ListView) view.findViewById(R.id.lista_promocao);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemPromocao itemPromocao = arrayList.get(position);
                Intent intent = new Intent(getContext(), PromocaoDetalheActivity.class);
                intent.putExtra(MainActivity.PROMOCAO, itemPromocao);
                startActivity(intent);
            }
        });

        new PromocaoAsyncTask().execute();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_atualizar:
                new PromocaoAsyncTask().execute();
                Toast.makeText(getContext(), "Atualizando Promoção...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class PromocaoAsyncTask extends AsyncTask<Void, String, List<ItemPromocao>>{
        @Override
        protected List<ItemPromocao> doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://www.devnet2.com.br/apiquepexinxa/api/promocao");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream == null){
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String linha;
                StringBuffer buffer = new StringBuffer();
                while((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }
                return JsonUtil.fromJsonToList(buffer.toString());
            }catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ItemPromocao> itemPromocao) {
            adapter.clear();
            adapter.addAll(itemPromocao);
            adapter.notifyDataSetChanged();
        }
    }
}
