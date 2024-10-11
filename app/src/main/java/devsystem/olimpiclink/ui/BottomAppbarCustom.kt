package devsystem.olimpiclink.ui
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.BottomAppbarCustomBinding
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.util.CommonEvents

class BottomAppbarCustom @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding = BottomAppbarCustomBinding.inflate(LayoutInflater.from(context), this, true)
    val commonEvent = CommonEvents()
    init {
        setLayout(attrs)
    }
    fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.BottomAppbar
            )
        }
    }
}
