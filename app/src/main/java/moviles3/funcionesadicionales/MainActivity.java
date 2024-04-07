package moviles3.funcionesadicionales;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    ProgressBar BarraDeCarga;
    private ArrayList<String> listaItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.idLista);
        BarraDeCarga = (ProgressBar) findViewById(R.id.idProgress);

        llenarlista2();
    }

    public void llenarlista2(){
        new Sincronicatask().execute();
    }

    private class Sincronicatask extends AsyncTask<Void,Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            int tiempo = 600;
            ArrayList<String> listaDeCarga = new ArrayList<>();
            for (int i=0;i<10;i++){
                listaDeCarga.add("Objeto " + i);
                publishProgress();
                try {
                    Thread.sleep(tiempo);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return listaDeCarga;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            BarraDeCarga.incrementProgressBy(10);
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            listaItem = strings;
            ArrayAdapter<String> adaptadorList = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_checked,
                    listaItem
            );
            lista.setAdapter(adaptadorList);
        }
    }

}


