import java.util.*;

enum months{
    Jan(1), Feb(2), Mar(3), Apr(4), May(5);

    private int price;

    months(int m){
        price = m;
    }

    int getprice(){
        return price;
    }
}

class fourth{
    public static void main(String...args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Months Value");
        
        for(months m: months.values()){
            System.out.println(m + " "+ m.getprice());
        }

        System.out.println("Enter name of object");
        String a = sc.nextLine();
        try{
            System.out.println(months.valueOf(a).getprice());
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println(months.Jan.getprice());
    }
}