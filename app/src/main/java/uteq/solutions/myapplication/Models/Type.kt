package uteq.solutions.myapplication.Models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Type(val strgJsonObjType: String?) {

    var name: String? = null
    var id: String? = null
    var options: MutableList<String>? = null


    companion object {
        @Throws(JSONException::class)
        fun jsonObjectsBuild(myJsonType: JSONArray): ArrayList<Type> {
            val userArray: ArrayList<Type> = ArrayList<Type>()
            for (i in 0 until myJsonType.length()) {
                userArray.add(Type(myJsonType.getJSONObject(i).toString()))
            }
            return userArray
        }
    }

    init {
        val jsonObjType: JSONObject = JSONObject(strgJsonObjType.toString())
        name = jsonObjType.getString("name")
        id = jsonObjType.getString("id")
        options = jsArrayToArrayList(jsonObjType.getJSONArray("options"))

    }

    fun jsArrayToArrayList(jsArray: JSONArray): MutableList<String> {
        var mutList: MutableList<String> = mutableListOf()
        for (i in 0 until jsArray.length()) {
            mutList.add(jsArray.get(i).toString())
        }
        return mutList
    }
}