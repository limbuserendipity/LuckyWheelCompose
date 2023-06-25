package net.limbuserendipity.luckywheelcompose.empty

data class Item(
    val emoji : String
) : Enumerable{
    override var count: Int = 1
}