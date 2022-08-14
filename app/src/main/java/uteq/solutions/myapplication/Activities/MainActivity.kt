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
import uteq.solutions.myapplication.Fragments.*
import uteq.solutions.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle)

        user = intent.getParcelableExtra<User>("user") as User
        Picasso.get().load(user.avatar).into(
            binding.navView.getHeaderView(0).findViewById<CircleImageView>(R.id.profile_image)
        )
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.lblNombreUser).text = user.name
        addOptions()
    }

    private fun addOptions() {
        for (i in 0 until user.options!!.size) {
            var option = user.options!![i].toString()

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

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, fragment)
            commit()
        }
    }

}

