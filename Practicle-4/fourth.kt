fun main(args: Array<String>){
   val m1: monthname = monthname(months.Jan)

   println("The first Month is:"+m1.name)

   println(m1.name.toString()+ " value is : " +m1.name.value)
}

data class monthname(val name: months)

enum class months(val value: Int){
    Jan(1), Feb(2), Mar(3), Apr(4), May(5)
}
