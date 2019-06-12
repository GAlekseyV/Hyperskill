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
        int C = Processor.readInt();

        int[][] result = Processor.mul(matrixA, C);

        Processor.printMatrix(result);
    }

    private  int readInt(){
        return scanner.nextInt();
    }

    private  int[][] readMatrix(){
        int rows = readInt();
        int columns = readInt();

        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = readInt();
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

    private int[][] mul(int[][] matrix, int c){
        int[][] result = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                result[i][j] = matrix[i][j] * c;
            }
        }
        return result;
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