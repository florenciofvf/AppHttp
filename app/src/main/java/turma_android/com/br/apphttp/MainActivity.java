package turma_android.com.br.apphttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtValor1;
    private EditText txtValor2;
    private TextView lblTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        txtValor1 = (EditText) findViewById(R.id.txtValor1);
        txtValor2 = (EditText) findViewById(R.id.txtValor2);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
    }

    public void somar(View v) {

    }

    public int somar(int valor1, int valor2) {
        return valor1 + valor2;
    }
}
