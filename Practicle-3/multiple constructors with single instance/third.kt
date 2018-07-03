class third(){
    var a1= ""
    var a2 = ""

    init {
        println("Default called")
    }

    constructor(a1: Int, a2: Int) : this(){
        println("Secondary Called")
    }

}



fun main(args: Array<String>){
    var v1 = third(10,20)
}
