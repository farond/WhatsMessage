package br.com.lino.whatsmessage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;


public class MainActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText editTextTelefone;
    EditText editTextMensagem;
    public Button enviarButton;
    String messagestr, phonestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTelefone = findViewById(R.id.telefoneEditText);
        editTextMensagem = findViewById(R.id.mensagemEditText);
        enviarButton = findViewById(R.id.enviarBtn);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagestr = editTextMensagem.getText().toString();
                phonestr = editTextTelefone.getText().toString();

                if (!messagestr.isEmpty() && !phonestr.isEmpty()) {

                    if (appInstalledOrNot("com.whatsapp")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://api.whatsapp.com/send?phone=" + "+55" + phonestr + "&text=" + messagestr));
                        startActivity(intent);
                        editTextMensagem.setText("");
                        editTextTelefone.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Whatsapp não está intalado neste aparelho", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "O telefone e a mensagem não podem ser vazias", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}