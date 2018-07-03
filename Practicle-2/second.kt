package Modules

fun main(args: Array<String>){
    println("Enter the Number of Elements in an array")

    var data: Int? = readLine()!!.toInt()
    var arr: ArrayList = ArrayList()
    var search: Int? = null
    for(i in 0 until data!!) {
        arr.add(readLine()!!.toInt())
    }

    println("Enter Number to Search: ")
    search = readLine()!!.toInt()
    println(arr.indexOf(search))

    println("Enter the position to Insert the element: ")
    var pos: Int = readLine()!!.toInt()

    println("Enter the element for Insert: ")
    var ele: Int = readLine()!!.toInt()
    arr.add(pos, ele)

    println("Array is $arr")
    arr.sort()
    println("Sorted Array is $arr")


}
