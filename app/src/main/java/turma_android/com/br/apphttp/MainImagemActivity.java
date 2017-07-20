package turma_android.com.br.apphttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainImagemActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_imagem_layout);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void baixarImagem(View v) {
        new TarefaBaixarImagem(imageView)
                .execute("https://raw.githubusercontent.com/florenciofvf/AppHttp/master/app/src/main/res/mipmap-hdpi/ic_launcher.png");
    }

    private byte[] lerBytes(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] dados = new byte[1024];
        int lidos = is.read(dados);

        while(lidos > 0) {
            baos.write(dados, 0, lidos);
            lidos = is.read(dados);
        }

        is.close();
        return baos.toByteArray();
    }

    private class TarefaBaixarImagem extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        TarefaBaixarImagem(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String s = strings[0];
            Bitmap b = null;

            try {
                URL url = new URL(s);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(false);

                conn.connect();

                int resp = conn.getResponseCode();

                if(resp == HttpURLConnection.HTTP_OK) {
                    byte[] bytes = lerBytes(conn.getInputStream());
                    b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }

            } catch(Exception e) {
                Log.i("ERRO", e.getMessage());
            }

            return b;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
