package Lesson2;

class MyArraySizeException extends Throwable {
    public String errorMessage;

    MyArraySizeException(String m) {
        errorMessage = m;
    }
}

class MyArrayDataException extends Throwable {
}

public class ParseArrays {
    public static int parseArray(String[][] a) throws MyArraySizeException, MyArrayDataException {
        if(a.length != 4) {
            throw new MyArraySizeException("1st level array count not equal 4");
        }
        for (int i = 0; i < 3; i++) {
            if(a[i].length != 4)
                throw new MyArraySizeException("2nd level array count not equal 4");
        }

        int sum = 0;
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 4; j++) {
                try{
                    sum += Integer.parseInt(a[i][j]);
                }
                catch (NumberFormatException ex){
                    throw new MyArrayDataException();
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        try {
            int sum = ParseArrays.parseArray(new String[][]{ {"1", "2", "3", "4"}, {"8","5", "6", "7"}, {"1",
                    "2", "3", "4"}, {"8","5", "6", "7" } });
            System.out.println("Sum: " + sum);

        } catch (MyArraySizeException | MyArrayDataException myArraySizeException) {
            System.out.println(myArraySizeException.getClass().getName());
        }
    }
}
