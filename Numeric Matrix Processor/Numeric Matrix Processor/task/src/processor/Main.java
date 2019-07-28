package processor;
import java.util.Scanner;

public class Main {

    enum OPERATION{
        NOSEL,
        ADD,
        MULCONST,
        MULMATRIX,
        TRANSPOSE,
        DETERMINANT,
        INVERSE,
        EXIT,
    }

    private static OPERATION op;
    private static Scanner scanner;

    public static void main(String[] args) {
        op = OPERATION.NOSEL;
        scanner = new Scanner(System.in);

        while (op != OPERATION.EXIT) {
            printMainMenu();
            setChoice(scanner.nextInt());
            switch (op) {
                case ADD:
                    Matrix addend_1 = new Matrix(readArray());
                    Matrix addend_2 = new Matrix(readArray());
                    Matrix sum = add(addend_1, addend_2);
                    sum.print();
                    //scanner.nextLine();
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
                case TRANSPOSE:
                    printTransposeMenu();
                    int choice = scanner.nextInt();
                    Matrix m = new Matrix(readArray());
                    transpose(m, choice).print();
                    break;
                case DETERMINANT:
                    Matrix matrix1 = new Matrix(readArray());
                    double det = CalculateDeterminant(matrix1);
                    System.out.println("The result is: ");
                    System.out.println(det);
                    break;
                case INVERSE:
                    Matrix matrix2 = new Matrix(readArray());
                    Matrix inverse_m = Inverse(matrix2);
                    System.out.println("The result is: ");
                    inverse_m.print();
                    break;
                default:
                    System.out.println("Unknown command");
            }

        }
    }


    private static void printMainMenu(){
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void printTransposeMenu(){
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
    }

    private static Matrix transpose(Matrix m, int type) {
        switch (type) {
            case 1:
                return mainDiagonalTranspose(m);
            case 2:
                return sideDiagonalTranspose(m);
            case 3:
                return verticalLineTranspose(m);
            case 4:
                return horizontalLineTranspose(m);
            default:
        }
        return m;
    }

    private static Matrix mainDiagonalTranspose(Matrix m){
        double[][] result = new double[m.getColumns()][m.getRows()];
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                result[i][j] = m.getElem(j, i);
            }
        }
        return new Matrix(result);
    }

    private static Matrix sideDiagonalTranspose(Matrix m){
        double[][] result = new double[m.getColumns()][m.getRows()];
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                result[i][j] = m.getElem(m.getRows()-j-1, m.getColumns()-i-1);
            }
        }
        return new Matrix(result);
    }

    private static Matrix verticalLineTranspose(Matrix m){
        double[][] result = new double[m.getColumns()][m.getRows()];
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                result[i][m.getColumns()-1-j] = m.getElem(i, j);
            }
        }
        return new Matrix(result);
    }

    private static Matrix horizontalLineTranspose(Matrix m){
        double[][] result = new double[m.getRows()][m.getColumns()];
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                result[m.getRows()-1-i][j] = m.getElem(i, j);
            }
        }
        return new Matrix(result);
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
            case 4:
                op = OPERATION.TRANSPOSE;
                break;
            case 5:
                op = OPERATION.DETERMINANT;
                break;
            case 6:
                op = OPERATION.INVERSE;
                break;
            case 0:
                op = OPERATION.EXIT;
                break;
            default:
                op = OPERATION.NOSEL;
        }
    }

    private static double[][] readArray(){
        System.out.print("Enter size of matrix: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        double[][] data = new double[rows][columns];
        System.out.println("Enter matrix:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                data[i][j] = scanner.nextDouble();
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

    private static Matrix mul(Matrix m, double c){
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

    private static double CalculateDeterminant(Matrix m){
        if(m.getColumns() == 1 && m.getRows() == 1){
            return m.getElem(0, 0);
        }
        if(m.getColumns() == 2 && m.getRows() == 2){
            return CalculateDeterminant2OrderMatrix(m);
        }
        double determinant = 0;
        for(int j = 0; j < m.getColumns(); j++){
            int coFactor = CalculateCoFactor(0, j);
            determinant += m.getElem(0, j) * coFactor * CalculateDeterminant(DecomposeMatrix(m,0,j));
        }

        return determinant;
    }

    private static double CalculateDeterminant2OrderMatrix(Matrix m){
        return m.getElem(0,0) * m.getElem(1, 1) -
                m.getElem(0,1) * m.getElem(1,0);
    }

    private static int CalculateCoFactor(int i, int j){
        var coFactor = 1;
        if((i + j + 2) % 2 != 0){
            coFactor = -1;
        }
        return coFactor;
    }

    private static Matrix DecomposeMatrix(Matrix m, int row, int column){
        double[][] data = new double[m.getRows() - 1][m.getColumns() - 1];
        int new_i = 0;
        int new_j = 0;
        for(int r = 0; r < m.getRows(); r++){
            if(r != row){
                for(int col = 0; col < m.getColumns(); col++){
                    if(column != col){
                        data[new_i][new_j] = m.getElem(r, col);
                        new_j++;
                    }
                }
                new_i++;
                new_j = 0;
            }
        }
        return new Matrix(data);
    }

    private static Matrix coFactorMatrix(Matrix m){
        double[][] result = new double[m.getRows()][m.getColumns()];
        for(int i = 0; i < m.getRows(); i++){
            for(int j = 0; j < m.getColumns(); j++){
                result[i][j] = CalculateDeterminant(DecomposeMatrix(m, i, j))
                        * CalculateCoFactor(i, j);
            }
        }
        return new Matrix(result);
    }

    private static Matrix Inverse(Matrix m){
        double determinant = CalculateDeterminant(m);
        Matrix mTranspose = mainDiagonalTranspose(m);
        Matrix cTranspose = coFactorMatrix(mTranspose);

        return mul(cTranspose, 1.0 / determinant);
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
        System.out.println();
    }
}