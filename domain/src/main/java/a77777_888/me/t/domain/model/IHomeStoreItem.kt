package a77777_888.me.t.domain.model

interface IHomeStoreItem {
    val id: Int
    val isBuy: Boolean
    val isNew: Boolean?
    val picture: String
    val subtitle: String
    val title: String
}