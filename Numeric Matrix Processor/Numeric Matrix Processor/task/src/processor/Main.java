package processor;
import java.util.Scanner;

public class Main {

    private Scanner scanner;

    private Main(){
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main Processor = new Main();
        int[][] matrixA = Processor.readMatrix();
        int[][] matrixB = Processor.readMatrix();

        if(matrixA.length != matrixB.length
                || matrixA[0].length != matrixB[0].length){
            System.out.println("ERROR");
            return;
        }

        int[][] sum = Processor.add(matrixA, matrixB);

        Processor.printMatrix(sum);
    }

    private  int[][] readMatrix(){
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private int[][] add(int[][] a, int[][] b){
        int[][] sum = new int[a.length][a[0].length];
        for(int i = 0; i < sum.length; i++){
            for(int j = 0; j < sum[0].length; j++){
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        return sum;
    }

    private void printMatrix(int[][] matrix){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
