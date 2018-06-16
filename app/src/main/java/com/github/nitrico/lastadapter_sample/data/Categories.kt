package com.github.nitrico.lastadapter_sample.data;

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso



class Categories(val jsonCategory:com.aadira.library.Models.Categories) {
    val imageUrl=jsonCategory.image
    val name=jsonCategory.name
    val id=jsonCategory.id
    val description=jsonCategory.description

}
// this is binding view
// in java is static method here it should be outside class directly in a file.
// also remember no bind: prefix required in the annotation

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String) {
    if(imageUrl.length>0) {
        Picasso.get()
                .load(imageUrl)
                .resize(400, 400) // handle the size of the image here.
                .into(view);
    }
}