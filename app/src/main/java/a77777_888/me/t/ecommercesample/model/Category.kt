package a77777_888.me.t.ecommercesample.model

import a77777_888.me.t.ecommercesample.R
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Category(
    @StringRes val tile: Int,
    @DrawableRes val icon: Int
)

val listCategory = listOf(
    Category(R.string.phones_category_title, R.drawable.ic_phone),
    Category(R.string.tablets_category_title, R.drawable.ic_tablet),
    Category(R.string.books_category_title, R.drawable.ic_book),
    Category(R.string.tvsets_category_title, R.drawable.ic_tv),
    Category(R.string.computers_category_title, R.drawable.ic_computer),
    Category(R.string.tools_category_title, R.drawable.ic_tools),
    Category(R.string.auto_accessories_category_title, R.drawable.ic_auto_accessories),
)




