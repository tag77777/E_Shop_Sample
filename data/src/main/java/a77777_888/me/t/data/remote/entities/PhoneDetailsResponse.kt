package a77777_888.me.t.data.remote.entities

import a77777_888.me.t.domain.model.IPhoneDetails
import com.google.gson.annotations.SerializedName

data class PhoneDetailsResponse(
    @SerializedName("CPU")
    override val cpu: String,
    override val camera: String,
    override val capacity: List<String>,
    override val color: List<String>,
    override val id: String,
    override val images: List<String>,
    override val isFavorites: Boolean,
    override val price: Int,
    override val rating: Double,
    override val sd: String,
    override val ssd: String,
    override val title: String
) : IPhoneDetails