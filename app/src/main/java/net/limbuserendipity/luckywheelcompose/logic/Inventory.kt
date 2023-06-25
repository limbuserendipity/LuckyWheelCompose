package net.limbuserendipity.luckywheelcompose.logic

import net.limbuserendipity.luckywheelcompose.empty.Enumerable
import net.limbuserendipity.luckywheelcompose.empty.Item

class Inventory<T : Enumerable>{

    private val mutableItems : MutableList<T> = mutableListOf()

    val items : List<T>
        get() = mutableItems

    fun add(item : T){
        val index = mutableItems.indexOf(item)

        if(index != -1){
            mutableItems[index].count += item.count
        } else {
            mutableItems.add(item)
        }
    }

    fun remove(item : T){
        val index = mutableItems.indexOf(item)

        if(index != -1){
            with(mutableItems.get(index)){
                count -= item.count
                if(count < 1){
                    mutableItems.removeAt(index)
                }
            }
        }
    }

}

fun inventory(list : List<Item>) : Inventory<Item>{
    val inv = Inventory<Item>()
    list.forEach {
        inv.add(it)
    }
    return inv
}