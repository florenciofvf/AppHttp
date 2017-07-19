package turma_android.com.br.apphttp;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtValor1;
    private EditText txtValor2;
    private TextView lblTotal;

    private MeuHandler handler = new MeuHandler();
    private Tarefa tarefa = new Tarefa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        txtValor1 = (EditText) findViewById(R.id.txtValor1);
        txtValor2 = (EditText) findViewById(R.id.txtValor2);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
    }

    //Handler
    public void somar(View v) {

        Thread t = new Thread() {
            @Override
            public void run() {
                final int v1 = Integer.parseInt( txtValor1.getText().toString() );
                final int v2 = Integer.parseInt( txtValor2.getText().toString() );

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lblTotal.setText(String.valueOf( somar(v1, v2) ));
                    }
                });
                */

                /*
                int total = somar(v1, v2);

                Message m = new Message();
                m.what = EXIBIR_TOTAL;

                Bundle b = new Bundle();
                b.putInt("TOTAL", total);
                m.setData(b);

                handler.sendMessageDelayed(m, 3000L);
                */

                tarefa.execute(v1, v2);
            }
        };

        t.start();
    }

    public static final int EXIBIR_TOTAL = 1;

    class MeuHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == EXIBIR_TOTAL) {
                int total = msg.getData().getInt("TOTAL");
                lblTotal.setText(String.valueOf( total ));
            }
        }
    }

    class Tarefa extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int valor1 = integers[0];
            int valor2 = integers[1];
            return valor1 + valor2;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            lblTotal.setText(String.valueOf( integer ));
        }
    }


    public int somar(int valor1, int valor2) {
        return valor1 + valor2;
    }
}
