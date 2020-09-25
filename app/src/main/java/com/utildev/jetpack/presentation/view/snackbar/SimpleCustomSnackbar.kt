package com.utildev.jetpack.presentation.view.snackbar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.utildev.jetpack.R
import kotlinx.android.synthetic.main.view_simple_snackbar.view.*
import java.lang.Exception
import java.lang.IllegalArgumentException

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SimpleCustomSnackbar(
    parent: ViewGroup,
    content: SimpleCustomSnackbarView
) : BaseTransientBottomBar<SimpleCustomSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        fun make(
            view: View,
            message: String,
            duration: Int,
            icon: Int,
            action: String?,
            backgroundColor: Int,
            listener: View.OnClickListener?,
        ): SimpleCustomSnackbar? {

            // find a suitable parent for custom view
            val parent = view.findSuitableParent()
                ?: throw IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.")

            // inflate custom view
            try {
                val customView = LayoutInflater.from(view.context).inflate(
                    R.layout.inflation_simple_snackbar,
                    parent,
                    false
                ) as SimpleCustomSnackbarView

                // create and return Snackbar
                customView.vSimpleSnackbar_tvMessage.text = message
                action?.let {
                    customView.vSimpleSnackbar_tvAction.text = action
                    customView.vSimpleSnackbar_tvAction.setOnClickListener {
                        listener?.onClick(customView.vSimpleSnackbar_tvAction)
                    }
                }
                customView.vSimpleSnackbar_ivLabel.setImageResource(icon)
                customView.vSimpleSnackbar_container.setBackgroundColor(backgroundColor)

                return SimpleCustomSnackbar(parent, customView).setDuration(duration)
            } catch (e: Exception) {
                Log.v("exception ", e.message)
            }
            return null
        }
    }
}