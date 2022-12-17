package a77777_888.me.t.ecommercesample.presentation.model

import a77777_888.me.t.data.repositories.mockphonerepository.EmptyListsMockRepository
import a77777_888.me.t.data.repositories.mockphonerepository.MockPhonesRepository
import a77777_888.me.t.domain.repositories.IProductsRepository
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
    val productsRepository: IProductsRepository
) {
    class Phones(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class Tablets(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class Books(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class Tv(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class Computers(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class Tools(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
    class AutoAccessories(@StringRes title: Int, @DrawableRes icon: Int, productsRepository: IProductsRepository)
        : Category(title, icon, productsRepository)
}

val listCategory = listOf(
    Category.Phones(R.string.phones_category_title, R.drawable.ic_phone, MockPhonesRepository()),
    Category.Tablets(R.string.tablets_category_title, R.drawable.ic_tablet, EmptyListsMockRepository()),
    Category.Books(R.string.books_category_title, R.drawable.ic_book, EmptyListsMockRepository()),
    Category.Tv(R.string.tvsets_category_title, R.drawable.ic_tv, EmptyListsMockRepository()),
    Category.Computers(R.string.computers_category_title, R.drawable.ic_computer, EmptyListsMockRepository()),
    Category.Tools(R.string.tools_category_title, R.drawable.ic_tools, EmptyListsMockRepository()),
    Category.AutoAccessories(
        R.string.auto_accessories_category_title,
        R.drawable.ic_auto_accessories,
        EmptyListsMockRepository()
    )
)






