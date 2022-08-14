package uteq.solutions.myapplication.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class User(val strgJsonObjUser: String?): Parcelable {
    var name: String ? = null
    var user: String ? = null
    var pass: String ? = null
    var avatar: String? = null
    var type: String? = null

    companion object {
        @Throws(JSONException::class)
        fun jsonObjectsBuild(myJsonUser: JSONArray): ArrayList<User> {
            val userArray: ArrayList<User> = ArrayList<User>()
            for (i in 0 until myJsonUser.length()) {
                userArray.add(User(myJsonUser.getJSONObject(i).toString()))
            }
            return userArray
        }
    }

    init {
        val jsonObjUser:JSONObject = JSONObject(strgJsonObjUser.toString())
        user = jsonObjUser.getString("nickname")
        name = jsonObjUser.getString("name")
        pass = jsonObjUser.getString("pass")
        avatar = jsonObjUser.getString("avatar")
        type = jsonObjUser.getString("type")

    }

}