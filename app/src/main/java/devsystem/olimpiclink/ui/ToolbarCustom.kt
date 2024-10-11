package devsystem.olimpiclink.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import devsystem.olimpiclink.R
import devsystem.olimpiclink.databinding.ToolbarCustomBinding
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager

class ToolbarCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding = ToolbarCustomBinding.inflate(LayoutInflater.from(context), this, true)
    init{
        setLayout(attrs)
    }
    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ToolbarCustom
            )
            binding.btnMenu.setOnClickListener{
                Log.d("ToolbarCustom", "teste")
            }
            attributes.recycle()
        }
    }
}