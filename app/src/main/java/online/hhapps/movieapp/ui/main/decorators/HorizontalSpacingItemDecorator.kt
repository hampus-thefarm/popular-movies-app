package online.hhapps.movieapp.ui.main.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) > 0) {
                top = spaceSize
            }
            if (parent.getChildAdapterPosition(view) < parent.childCount - 1) {
                bottom = spaceSize
            }
        }
    }
}