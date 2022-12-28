package dgtic.unam.modulosiete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dgtic.unam.modulosiete.databinding.ActivityLoginBinding
import android.app.AlertDialog
import android.content.Context

class LogAct : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validate()
        sesiones()
    }

    private fun sesiones() {
        var preferences =
            getSharedPreferences(getString(R.string.file_preferencia), Context.MODE_PRIVATE)
        var email: String? = preferences.getString("email", null)
        var provedor: String? = preferences.getString("provedor", null)

        if (email != null && provedor != null) {
            opciones(email, TipoProvedor.valueOf(provedor))
        }
    }

    private fun validate() {
        binding.updateUser.setOnClickListener {

            if (!binding.username.text.toString().isEmpty() && !binding.password.text.toString()
                    .isEmpty()
            ) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                ).addOnCompleteListener {
                    if (it.isComplete) {
                        Toast.makeText(
                            binding.signin.context,
                            "Enlace con exito",
                            Toast.LENGTH_SHORT
                        ).show()
                        opciones(it.result?.user?.email ?: "", TipoProvedor.CORREO)
                    } else {
                        alert()
                    }
                }
            }
        }

        binding.loginbtn.setOnClickListener {
            if (binding.username.text.toString().isNotEmpty() && binding.password.text.toString().isNotEmpty()
            ) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        opciones(it.result?.user?.email ?: "", TipoProvedor.CORREO)
                    } else {
                        alert()
                    }
                }
            }
            else if(binding.username.text.toString().isEmpty() || binding.password.text.toString().isEmpty()){
                alertEmpty()
            }
        }
    }

    private fun alert() {
        val bulder = AlertDialog.Builder(this)
        bulder.setTitle("Mensaje")
        bulder.setMessage("Error los Datos son Incorrectos")
        bulder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = bulder.create()
        dialog.show()
    }

    private fun alertEmpty(){
        val bulder = AlertDialog.Builder(this)
        bulder.setTitle("Mensaje")
        bulder.setMessage("Llene los campos vacios!")
        bulder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = bulder.create()
        dialog.show()
    }

    private fun opciones(email: String, provedor: TipoProvedor) {
        var pasos = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provedor", provedor.name)
        }

        startActivity(pasos)
    }
}