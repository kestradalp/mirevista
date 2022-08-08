package uteq.solutions.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONArray
import java.util.stream.Stream


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var nombre: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "SÚPER APP MÓVIL"

        nombre = this.intent.extras?.getString("name").toString()

        habilitarOpciones(this.intent.extras?.getString("options").toString())
        crearOtrOpc(this.intent.extras?.getString("moreOption").toString())

        var header: View = nav_view.getHeaderView(0)
        header.findViewById<TextView>(R.id.nombre_user).text = nombre

//        println(println("${powers::class.simpleName}"))

        setSupportActionBar(toolbar);

        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener(this);
    }

    fun habilitarOpciones(opcion: String) {
        var jsArPerm = JSONArray(opcion.toString())
        for (j in 0 until jsArPerm.length()) {
            nav_view.menu.getItem(jsArPerm.get(j).toString().toInt()).setVisible(true)
        }
    }

    fun crearOtrOpc(OtrOpc: String) {
        var jsOtrOpc = JSONArray(OtrOpc.toString())
        var x:String
        for (j in 0 until jsOtrOpc.length()) {
            x=jsOtrOpc.get(j).toString()
            nav_view.menu.add(x).setIcon(R.drawable.iconmenu)
                .setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener { //do what u want
                    Log.d("HOLA", nav_view.menu.getItem(j).setTitle(x).toString())
                    true
                })
//            nav_view.menu.getItem(j).setTitle(x.toString())
//            Log.d("HOLA", nav_view.menu.getItem(j).setTitle(x).toString())

//            var frag:Fragment =resources.getIdentifier("fragment1", "fragment", packageName)



        }
    }
    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(androidx.fragment.R.id.fragment_container_view_tag, fragment)
            commit()
        }
    }

    //    Esto es el botón que abre el menu de la izquierda
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        Log.d("Perrito 1", "perrito 1")

        var fragment: Fragment? = null

        when (item.title) {
            "Ficha Médica" -> {
                fragment = Fragment1()
            }
            "Malla" -> {
                fragment = Fragment2()
            }
            "Hoja de vida" -> {
                fragment = Fragment3()
            }
        }

        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()

            item.setChecked(true)
            getSupportActionBar()?.setTitle(item.getTitle());
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true

    }

}

