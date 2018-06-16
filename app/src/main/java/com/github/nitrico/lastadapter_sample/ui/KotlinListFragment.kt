package com.github.nitrico.lastadapter_sample.ui

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.aadira.library.Interfaces.ListCallback
import com.aadira.library.Main.WooCommerce
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.github.nitrico.lastadapter_sample.BR
import com.github.nitrico.lastadapter_sample.R
import com.github.nitrico.lastadapter_sample.data.*
import com.github.nitrico.lastadapter_sample.databinding.*
import com.squareup.picasso.Picasso
import retrofit.RetrofitError

class KotlinListFragment : ListFragment() {


    private val typeCategory = Type<ItemCategoryBinding>(R.layout.item_category)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }


    private val typeHeader = Type<ItemHeaderBinding>(R.layout.item_header)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }

    private val typeHeaderFirst = Type<ItemHeaderFirstBinding>(R.layout.item_header_first)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }

    private val typePoint = Type<ItemPointBinding>(R.layout.item_point)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }

    private val typeCar = Type<ItemCarBinding>(R.layout.item_car)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }

    private val typePerson = Type<ItemPersonBinding>(R.layout.item_person)
            .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
            .onBind { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
            .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
            .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val items: ObservableArrayList<Any> = Data.items
        populateAllCategories()
        // initialize data
        init()
    }

    fun populateAllCategories(){
        val wooCommerce = WooCommerce.getInstance()
        // get all categories
        var items=ObservableArrayList<Any>().apply {
            add(Header("Categories"))
            }
        wooCommerce.getAllCategories { categories, error ->
            if (error == null) {
                System.out.println("categories");
                System.out.println(categories.toString())
                 items = ObservableArrayList<Any>().apply {
                    categories.forEachIndexed { index, category ->
                        add(Categories(category as com.aadira.library.Models.Categories))
                    }
                }
                val stableIds = items == StableData.items

                //setMapAdapter(items, stableIds)
                //setMapAdapterWithListeners(items, stableIds)
                //setLayoutHandlerAdapter(items, stableIds)
                setTypeHandlerAdapter(items, stableIds)

            } else {
                println(error)

            }
        }

    }

    fun init() {

    }

    private fun setMapAdapter(items: List<Any>, stableIds: Boolean) {
        LastAdapter(items, BR.item, stableIds)
                .map<Person>(R.layout.item_person)
                .map<Car>(R.layout.item_car)
                .map<Header>(R.layout.item_header)
                .map<Point>(R.layout.item_point)
                .map<Categories>(R.layout.item_category)
                .into(list)
    }

    // don't think this function is ever used.
    private fun setMapAdapterWithListeners(items: List<Any>, stableIds: Boolean) {
        list.adapter = LastAdapter(items, BR.item, stableIds)
                .map<Header, ItemHeaderBinding>(R.layout.item_header)
                .map<Categories, ItemCategoryBinding>(R.layout.item_category)
                .map<Point>(typePoint)
                .map<Car>(Type<ItemCarBinding>(R.layout.item_car)
                        .onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
                        .onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
                        .onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
                        .onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
                        .onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }
                )
                .map<Person, ItemPersonBinding>(R.layout.item_person) {
                    onCreate { println("Created ${it.binding.item} at #${it.adapterPosition}") }
                    onBind { println("Bound ${it.binding.item} at #${it.adapterPosition}") }
                    onRecycle { println("Recycled ${it.binding.item} at #${it.adapterPosition}") }
                    onClick { activity.toast("Clicked #${it.adapterPosition}: ${it.binding.item}") }
                    onLongClick { activity.toast("Long-clicked #${it.adapterPosition}: ${it.binding.item}") }
                }
                .into(list)
    }

    private fun setLayoutHandlerAdapter(items: List<Any>, stableIds: Boolean) {
        LastAdapter(items, BR.item, stableIds).layout { item, position ->
            when (item) {
                is Header -> if (position == 0) R.layout.item_header_first else R.layout.item_header
                is Categories -> R.layout.item_category
                is Person -> R.layout.item_person
                is Point -> R.layout.item_point
                is Car -> R.layout.item_car
                else -> -1
            }
        }.into(list)
    }

    private fun setTypeHandlerAdapter(items: List<Any>, stableIds: Boolean) {
        LastAdapter(items, BR.item, stableIds).type { item, position ->
            when (item) {
                is Header -> if (position == 0) typeHeaderFirst else typeHeader
                is Categories -> typeCategory
                is Point -> typePoint
                is Person -> typePerson
                is Car -> typeCar

                else -> null
            }
        }.into(list)
    }

    private fun Context?.toast(text: String) = this?.let { Toast.makeText(it, text, Toast.LENGTH_SHORT).show() }

}
