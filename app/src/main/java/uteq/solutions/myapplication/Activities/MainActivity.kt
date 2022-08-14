package uteq.solutions.myapplication.Activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import uteq.solutions.myapplication.R
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import uteq.solutions.myapplication.Models.User
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONArray
import uteq.solutions.myapplication.Fragments.*
import uteq.solutions.myapplication.Models.Type
import uteq.solutions.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Activar el botón del menú
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_drawer, R.string.close_drawer)
        binding.drawerLayout.addDrawerListener(toggle)

        //Tomar el objeto usuario que me pasa el login
        user = intent.getParcelableExtra<User>("user") as User

        //Cargo los datos del usuario en el menú
        Picasso.get().load(user.avatar).into(
            binding.navView.getHeaderView(0).findViewById<CircleImageView>(R.id.profile_image)
        )
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.lblNombreUser).text = user.name
        addOptions()

    }

    private fun addOptions() {

        lateinit var type: Type

        val idTypeUser:String = user.type.toString()


        val jsonTypes =
            "[{\"name\":\"student tipe 1\",\"id\":\"1\",\"options\":[\"PENDIENTES\",\"AULA VIRTUAL\",\"FICHA MÉDICA\",\"HOJA DE VIDA\"]},{\"name\":\"teacher\",\"id\":\"1\",\"options\":[\"AULA VIRTUAL\",\"FICHA MÉDICA\",\"MALLA\",\"HOJA DE VIDA\"]},{\"name\":\"coordinator\",\"id\":\"1\",\"options\":[\"ALUMNOS\",\"PROFESORES\",\"AULA VIRTUAL\",\"FICHA MÉDICA\",\"MALLA\",\"HOJA DE VIDA\"]}]"

        val typeArray = Type.jsonObjectsBuild(JSONArray(jsonTypes))

        for (i in 0 until typeArray.size) {
            if (idTypeUser == typeArray[i].id) {
                type =  typeArray[i]
            }
        }




        for (i in 0 until type!!.options!!.size) {
            var option = type!!.options!![i].toString()

            var menu: Menu = binding.navView.menu

            menu.add(option).setIcon(R.drawable.iconmenu)
                .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener { //do what u want
                    when (it.title) {
                        "PENDIENTES" -> {
                            setFragment(Pendientes())
                        }
                        "AULA VIRTUAL" -> {
                            setFragment(FichaMedica())
                        }
                        "FICHA MÉDICA" -> {
                            setFragment(FichaMedica())
                        }
                        "MALLA" -> {
                            setFragment(Malla())
                        }
                        "HOJA DE VIDA" -> {
                            setFragment(HojaDeVida())
                        }
                    }
                    binding.toolbar.title = it.title
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                })
        }
    }


    fun getTypeUser(type: String): Type? {
        //Aquí se debería hacer el pedido a la api; pero, como no tengo, uso un json en un string :v
        val jsonTypes =
            "[{\"1\":[\"PENDIENTES\",\"AULA VIRTUAL\",\"FICHA MÉDICA\",\"MALLA\",\"HOJA DE VIDA\"]},{\"2\":[\"FICHA MÉDICA\",\"HOJA DE VIDA\"]},{\"2\":[\"FICHA MÉDICA\",\"MALLA\",\"HOJA DE VIDA\"]}]"

        val typeArray = Type.jsonObjectsBuild(JSONArray(jsonTypes))

        for (i in 0 until typeArray.size) {
            if (type == typeArray[i].name) {
                return typeArray[i]
            }
        }
        return null
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, fragment)
            commit()
        }
    }

}

