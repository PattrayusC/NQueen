import java.util.*;

class QueenMatrix {
    private List<List<Character>> board;
    private int Size;
    private int rinput, cinput;
    private boolean manual;
    
    private reader read = new reader();
  
    public QueenMatrix() {
        System.out.println("\nEnter board size N (Integer)");
        Size = read.readInput(Size);
        board = new ArrayList<>();
        for (int i = 0; i < Size; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < Size; j++)
                board.get(i).add('=');
        }
    }
  
    private void print() {
        int jx = 1;
        System.out.println();
        System.out.print("   ");
        for (int ix = 1; ix < Size+1; ix++)
            System.out.print(" " + ix + " ");
        System.out.println();
        for (int i = 0; i < Size; i++) {
            System.out.print(" " + jx + " ");
            jx++;
            for (int j = 0; j < Size; j++) {
              System.out.print(" " + board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void AskifManual() {
        System.out.println("\nPlace first Queen manually ? (yes or no))");
        String choice = null;
        choice = read.readInput(choice);
        if (choice.equals("yes")) {
            System.out.println("\nEnter row of first queen =");
            rinput = read.readInput(rinput, Size)-1;
            System.out.println("\nEnter col of first queen =");
            cinput = read.readInput(cinput, Size)-1;
            manual = true;
            board.get(rinput).set(cinput, 'Q');
            print();
        }
        if (choice.equals("no")) {
            System.out.println("\nAuto placement mode");
        }
    }

    private boolean isSafe(int row, int col) {
        int i, j;
        for (i = 0; i < col; i++)
            if (board.get(row).get(i) == 'Q')//left
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)//upper left
            if (board.get(i).get(j) == 'Q')
                return false;

        for (i = row, j = col; j >= 0 && i < Size; i++, j--)//lower left
            if (board.get(i).get(j) == 'Q')
                return false;

        return true;
    }

    private boolean solveNQUtil(int col) {
        if (col >= Size)
            return true;
        for (int i = 0; i < Size; i++) {
            if (i == rinput && manual) {
                continue;
            }
            if (col == cinput && manual) {
                board.get(rinput).set(cinput, '=');
                if (isSafe(rinput, cinput)) {
                    board.get(rinput).set(cinput, 'Q');
                    if (solveNQUtil(col + 1) == true) {
                        return true;
                    }
                }
                board.get(rinput).set(cinput, 'Q');
                
                return false;
            }
            else if (isSafe(i, col)) {
                board.get(i).set(col, 'Q');
                if (solveNQUtil(col + 1) == true)
                    return true;
                board.get(i).set(col, '=');
            }
        }
    
        return false;
    }

    public boolean solveNQ() {
        if (solveNQUtil(0) == false) {
            System.out.println("\nSolution does not exist");
            return false;
        }
        print();

        return true;
    }
}

class reader {
    public class minSize extends Exception {
        public minSize(String errorMessage) {
            super(errorMessage);
        }
    }
    
    public class exceedSize extends Exception {
        public exceedSize(String errorMessage) {
            super(errorMessage);
        }
    }
    
    public class invalidSize extends Exception {
        public invalidSize(String errorMessage) {
            super(errorMessage);
        }
    }
    
    public class OnlyYesNo extends Exception {
        public OnlyYesNo(String errorMessage) {
            super(errorMessage);
        }
    }
    
    public int readInput(int number) {
        boolean loop = true;
        while (loop) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextInt();
                if (number < 4)
                    throw new minSize("\nN must be at least 4");
                loop = false;
            }
            catch (minSize e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("\nEnter a new Integer");
            }
        }
        return number;
    }
    
    public int readInput(int number, int N) {
        boolean loop = true;
        while (loop) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextInt();
                if (number > N)
                    throw new exceedSize("\nExceed board size (board size = " + N + ")");
                if (number < 1)
                    throw new invalidSize("\nNumber must be at least 1");
                loop = false;
            }
            catch (exceedSize e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("\nEnter a new Integer");
            }
        }
        return number;
    }
    
    public String readInput(String string) {
        boolean loop = true;
        while (loop) {
            try {
                Scanner input = new Scanner(System.in);
                string = input.next().toLowerCase();
                switch (string) {
                  case "yes" :
                  case "no"  :
                    loop = false;
                    break;
                  default :
                    throw new OnlyYesNo("\nEnter a \"yes\" or \"no\"");
                }
            }
            catch (OnlyYesNo e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("\nEnter a new String");
            }
        }
        return string;
    }
}

public class NQueen {
    public static void main(String[] args) {
        boolean loop = true;
        reader read = new reader();
        while (loop) {
            QueenMatrix board = new QueenMatrix();
            board.AskifManual();
            board.solveNQ();
            System.out.println("\nContinue ? (yes or no)");
            String choice = null;
            choice = read.readInput(choice);
            if (choice.equals("yes"))
                loop = true;
            if (choice.equals("no"))
                loop = false;
        }
    }
}