package a77777_888.me.t.data.remote.testtaskrepository.entities

import a77777_888.me.t.domain.model.IHomeStoreItem
import com.google.gson.annotations.SerializedName

data class HomeStore(
    override val id: Int,
    @SerializedName("is_buy")
    override val isBuy: Boolean,
    @SerializedName("is_new")
    override val isNew: Boolean?,
    override val picture: String,
    override val subtitle: String,
    override val title: String
) : IHomeStoreItem