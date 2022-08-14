package uteq.solutions.myapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import uteq.solutions.myapplication.Models.User
import uteq.solutions.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            val nickname: String = binding.txtUser.text.toString()
            val pass: String = binding.txtPass.text.toString()

            val user: User? = consAPI(nickname, pass)

            if (user != null) {
                startActivity(
                    Intent(this, MainActivity::class.java).putExtra(
                        "user", user as Parcelable
                    )
                )
                finish()
            } else {
                Snackbar.make(
                    it, "Usuario o contraseña incorrecta",
                    Snackbar.LENGTH_LONG
                ).setAction("Actción", null).show()
            }
        }
    }

    fun consAPI(user1: String, pass1: String): User? {
        //Aquí se debería hacer el pedido a la api, pero como no tengo uso un json en un string :v
        val jsonUsers =
            "[{\"nickname\":\"kelvin\",\"pass\":\"12345\",\"name\":\"Kelvin Estrada\",\"avatar\":\"https://i.postimg.cc/FHq4t78B/Kelvin.png\",\"type\":\"1\"},{\"nickname\":\"Nigma\",\"pass\":\"nigmamen\",\"name\":\"Veas\",\"avatar\":\"https://i.postimg.cc/j2dChSXg/Veas.jpg\",\"type\":\"2\"},{\"nickname\":\"galves\",\"pass\":\"123456789\",\"name\":\"Eduardo Galves\",\"avatar\":\"https://i.postimg.cc/HnJBjdyK/Galves.jpg\",\"type\":\"3\"}]"

        val userArray = User.jsonObjectsBuild(JSONArray(jsonUsers))

        for (i in 0 until userArray.size) {
            if (user1 == userArray[i].user && pass1 == userArray[i].pass) {
                return userArray[i]
            }
        }
        return null
    }
}