package a77777_888.me.t.ecommercesample.presentation.model

import a77777_888.me.t.ecommercesample.R
import a77777_888.me.t.ecommercesample.databinding.CategoriesItemBinding
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//sealed class Category {
//    class Phones(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class Tablets(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class Books(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class Tv(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class Computers(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class Tools(@StringRes val tile: Int, @DrawableRes val icon: Int)
//    class AutoAccessories(@StringRes val tile: Int, @DrawableRes val icon: Int)
//}
//
//val listCategory = listOf(
//    Category.Phones(R.string.phones_category_title, R.drawable.ic_phone),
//    Category.Tablets(R.string.tablets_category_title, R.drawable.ic_tablet),
//    Category.Books(R.string.books_category_title, R.drawable.ic_book),
//    Category.Tv(R.string.tvsets_category_title, R.drawable.ic_tv),
//    Category.Computers(R.string.computers_category_title, R.drawable.ic_computer),
//    Category.Tools(R.string.tools_category_title, R.drawable.ic_tools),
//    Category.AutoAccessories(R.string.auto_accessories_category_title, R.drawable.ic_auto_accessories),
//)

sealed class Category(
    @StringRes var tile: Int,
    @DrawableRes var icon: Int,
) {
    class Phones(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class Tablets(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class Books(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class Tv(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class Computers(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class Tools(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
    class AutoAccessories(@StringRes title: Int, @DrawableRes icon: Int)
        : Category(title, icon)
}

internal val listCategory = listOf(
    Category.Phones(R.string.phones_category_title, R.drawable.ic_phone),
    Category.Tablets(R.string.tablets_category_title, R.drawable.ic_tablet),
    Category.Books(R.string.books_category_title, R.drawable.ic_book),
    Category.Tv(R.string.tvsets_category_title, R.drawable.ic_tv),
    Category.Computers(R.string.computers_category_title, R.drawable.ic_computer),
    Category.Tools(R.string.tools_category_title, R.drawable.ic_tools),
    Category.AutoAccessories(
        R.string.auto_accessories_category_title,
        R.drawable.ic_auto_accessories
    )
)






