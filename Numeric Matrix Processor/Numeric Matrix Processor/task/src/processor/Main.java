package processor;
import java.util.Scanner;

public class Main {

    enum OPERATION{
        NOSEL,
        ADD,
        MULCONST,
        MULMATRIX,
        EXIT,
    }

    private static OPERATION op;
    private static Scanner scanner;

    public static void main(String[] args) {
        op = OPERATION.NOSEL;
        scanner = new Scanner(System.in);

        while (op != OPERATION.EXIT) {
            printMenu();
            setChoice(scanner.nextInt());
            switch (op) {
                case ADD:
                    Matrix addend_1 = new Matrix(readArray());
                    Matrix addend_2 = new Matrix(readArray());


                    Matrix sum = add(addend_1, addend_2);
                    sum.print();
                    break;
                case MULCONST:
                    Matrix matrix = new Matrix(readArray());
                    int c = scanner.nextInt();
                    Matrix mulOnC = mul(matrix, c);
                    mulOnC.print();
                    break;
                case MULMATRIX:
                    Matrix mul_1 = new Matrix(readArray());
                    Matrix mul_2 = new Matrix(readArray());
                    Matrix result = mulMatrices(mul_1, mul_2);
                    result.print();
                    break;
                default:
            }

        }
    }


    private static void printMenu(){
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void setChoice(int choice){
        switch (choice){
            case 1:
                op = OPERATION.ADD;
                break;
            case 2:
                op = OPERATION.MULCONST;
                break;
            case 3:
                op = OPERATION.MULMATRIX;
                break;
            case 0:
                op = OPERATION.EXIT;
                break;
            default:
                op = OPERATION.NOSEL;
        }
    }

    private static int readInt(){
        return scanner.nextInt();
    }

    private static double[][] readArray(){
        System.out.print("Enter size of matrix: ");
        int rows = readInt();
        int columns = readInt();

        double[][] data = new double[rows][columns];
        System.out.println("Enter matrix:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                data[i][j] = readInt();
            }
        }
        return data;
    }

    private static Matrix add(Matrix a, Matrix b){
        double[][] sum = new double[a.getRows()][a.getColumns()];
        for(int i = 0; i < sum.length; i++){
            for(int j = 0; j < sum[0].length; j++){
                sum[i][j] = a.getElem(i, j) + b.getElem(i, j);
            }
        }
        return new Matrix(sum);
    }

    private static Matrix mul(Matrix m, int c){
        double[][] result = new double[m.getRows()][m.getColumns()];
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                result[i][j] = m.getElem(i, j) * c;
            }
        }
        return new Matrix(result);
    }

    private static Matrix mulMatrices(Matrix a, Matrix b) {
        double[][] result = new double[a.getRows()][b.getColumns()];
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < b.getColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < b.getRows(); k++) {
                    sum += a.getElem(i, k) * b.getElem(k, j);
                }
                result[i][j] = sum;
            }
        }
        return new Matrix(result);
    }
}


class Matrix{
    private int rows;
    private int columns;

    private double[][] data;

    int getRows(){
        return rows;
    }

    int getColumns(){
        return columns;
    }

    double getElem(int i, int j){
        return data[i][j];
    }

    Matrix(double[][] array){
        rows = array.length;
        columns = array[0].length;
        data = array;
    }

    void print(){
        for (double[] ints : data) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}