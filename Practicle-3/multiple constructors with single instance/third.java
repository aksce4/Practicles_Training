public class third
{  
    static third obj = null;
    private third(){
        System.out.println("Default Constructor");
    }

    public static third getdata(){
        if(obj == null){
            obj = new third();
        }
        return obj;
    }

    /*
    public third()
    {
        System.out.println("In default constructor");
    }
    public third(int a)
    {
        this();
        System.out.println("In single parameter constructor");
    }
    public third(int b, int c)
    {
        this(b);
        System.out.println("In multiple parameter constructor");
    }
    */

    public static void main(String s[])
    {
        third obj = new third(10, 20);
    }
}   