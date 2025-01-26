public class Test{
    public static void main(String [] args){
        String dictionary_path = "slownik.txt";
        System.out.println(dictionary_path);


        dictionary_path = dictionary_path.substring(0, 7)+"123";

        System.out.println(dictionary_path);

        int [][] a = {{1},{1}};

        a[1][0] =2;

        for (int i = 0; i <a.length;i++){
            int [] temp = a[i];
            for (int j =0; j < temp.length;j++){
                System.out.println(temp[j]);
            }
        }
    }}