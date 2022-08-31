package ru.udemy.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.udemy.shoppinglist.data.ShopListRepositoryImpl
import ru.udemy.shoppinglist.domain.DeleteShopItemUseCase
import ru.udemy.shoppinglist.domain.EditShopItemUseCase
import ru.udemy.shoppinglist.domain.GetShopListUseCase
import ru.udemy.shoppinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changedEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}