package uteq.solutions.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnIngresar.setOnClickListener {
            consAPI(txtUser.text.toString(), txtPass.text.toString())
        }
    }


    fun consAPI(user1: String, pass1: String) {

        var jsonUser =
            "[{\"user\":\"kelvin\",\"password\":\"12345\",\"name\":\"Kelvin Estrada\",\"options\":[\"0\",\"1\",\"2\"],\"moreOption\":[\"Ficha Médica\",\"Malla\",\"Hoja de vida\"]},{\"user\":\"Nigma\",\"password\":\"nigmamen\",\"name\":\"Veas\",\"options\":[\"0\",\"1\"],\"moreOption\":[\"Hoja de vida\"]},{\"user\":\"galves\",\"password\":\"123456789\",\"name\":\"Eduardo Galves\",\"options\":[\"0\",\"2\"],\"moreOption\":[\"Ficha Médica\",\"Hoja de vida\"]}]"

        var jsonArray = JSONArray(jsonUser.toString())

        for (i in 0 until jsonArray.length()) {
            var user2 = jsonArray.getJSONObject(i).getString("user").toString()
            var pass2 = jsonArray.getJSONObject(i).getString("password").toString()

            if (user1 == user2 && pass1 == pass2) {

                var name = jsonArray.getJSONObject(i).getString("name").toString()
                var opciones = jsonArray.getJSONObject(i).getJSONArray("options").toString()
                var moreOption = jsonArray.getJSONObject(i).getJSONArray("moreOption").toString()

//                println(println("${powers::class.simpleName}"))

                val bundle = Bundle()

                bundle.putString("name", name)
                bundle.putString("options", opciones)
                bundle.putString("moreOption", moreOption)

                startActivity(Intent(this, MainActivity::class.java).putExtras(bundle))
            }
        }


//        Volley.newRequestQueue(this).add(
//            StringRequest(
//                Request.Method.GET, "xxx",
//                { response ->
//                    var dataString: String = JSONObject(response.toString()).optString("data")
//                    parcJson(JSONArray(dataString))
//                },
//                {
//                    Log.e("Error", "Error")
//                })
//        )

    }
}