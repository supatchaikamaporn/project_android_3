package com.example.project_year3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager


class recycle_view : Fragment() {

    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): recycle_view {
        val recycle_view = recycle_view()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        recycle_view.setArguments(bundle)
        return recycle_view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycle_view, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val login_button2 = view.findViewById(R.id.login_button2) as Button

        this.lode_view()

        val button: Button = view.findViewById(R.id.button_open_dialog)
        button.setOnClickListener{
            val builder: AlertDialog.Builder =  AlertDialog.Builder(this.context)
            builder.setMessage("คุณชอบคัวละครนี้ใช่ไหม?")

            builder.setNegativeButton("รับ",
                DialogInterface.OnClickListener{ dialog, id ->
                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })

            builder.setPositiveButton("ไม่ใช่",
                DialogInterface.OnClickListener{ dialog, id ->
                    Toast.makeText(this.context,"ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })
            builder.show();

        }
        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)
        tvName.setText(Name)

        login_button2.setOnClickListener{
            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()
        }
        return view
    }

    fun lode_view(){
        val detail = detail()
        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        transaction.replace(R.id.Show_layout, detail,"detail")
        transaction.addToBackStack("detail")
        transaction.commit()
    }



}
