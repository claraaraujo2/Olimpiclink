package devsystem.olimpiclink.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import android.widget.EditText
import android.widget.ImageView
import devsystem.olimpiclink.R
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.ui.pages.ComunityActivity
import devsystem.olimpiclink.ui.pages.MainActivity
import devsystem.olimpiclink.ui.pages.MyProfileActivity
import devsystem.olimpiclink.ui.pages.UnpublishedPublicationActivity

class CommonEvents {
    @SuppressLint("ClickableViewAccessibility")
    fun touchListenerGet(button : AppCompatButton) : View.OnTouchListener{
        return View.OnTouchListener{_, motionEvent ->
            when (motionEvent.action) {
                ACTION_DOWN -> {
                    button.setBackgroundResource(R.drawable.edit_text_selected)
                    button.setTextColor(button.context.getColor(R.color.laranja_splash))
                }
                ACTION_UP, ACTION_CANCEL -> {
                    button.setBackgroundResource(R.drawable.buttons_initial)
                    button.setTextColor(button.context.getColor(R.color.white))
                }
            }
            false
        }
    }

    fun focusChangedListenerGet(edit_text : EditText) : View.OnFocusChangeListener{
        return View.OnFocusChangeListener{ _, hasFocus ->
            if(hasFocus){
                edit_text.setBackgroundResource(R.drawable.edit_text_selected)
            }
            else{
                edit_text.setBackgroundResource(R.drawable.buttons_initial)
            }
            false
        }
    }
    fun goPageCreationPublication (user: User, context : Activity, view : ImageView){
        view.setOnClickListener{
            var main_activity = Intent(context, UnpublishedPublicationActivity::class.java)
            view.setBackgroundResource(R.drawable.edit_text_selected)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
            context.finish()
        }
    }
    fun goPageMain(user : User, context: Activity, view : View){
        view.setOnClickListener{
            var main_activity = Intent(context, MainActivity::class.java)
            view.setBackgroundResource(R.drawable.edit_text_selected)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
            context.finish()
        }
    }
    fun goPageMyProfile(user : User, context: Activity, view : View){
        view.setOnClickListener{
            var main_activity = Intent(context, MyProfileActivity::class.java)
            view.setBackgroundResource(R.drawable.edit_text_selected)
            main_activity.putExtra("user", user)
            context.startActivity(main_activity)
            context.finish()
        }
    }

    fun goPageComunity(user : User, context: Activity, view : View){
        view.setOnClickListener{
            var main_activity = Intent(context, ComunityActivity::class.java)
            view.setBackgroundResource(R.drawable.edit_text_selected)
            main_activity.putExtra("user", user)
            main_activity.putExtra("comunity_id", 2)
            context.startActivity(main_activity)
            context.finish()
        }
    }


}