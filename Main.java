public class Main {
    public static void main(String[] args) {
        int C=3,L=3;
        int[][] temperturas=new int[L][C];
        lermt(temperturas,C,L);
    }

    public static void lermt(int[][] temperaturas, int C,int L){
        for(int i=0;i<L;i++){
            for(int j=0;j<C;j++){
                if(temperaturas[i][j]<10){
                    System.out.print(" "+temperaturas[i][j]+" ");
                }
                else{
                    System.out.print(temperaturas[i][j]+" ");
                }
            }
            System.out.println();
        }
    }


}