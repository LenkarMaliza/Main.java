import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRim;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("т.к. строка не является математической операцией");
        //римские числа
        if (Rim.isRim(operands[0]) && Rim.isRim(operands[1])) {
            num1 = Rim.convertToArab(operands[0]);
            num2 = Rim.convertToArab(operands[1]);
            isRim = true;
        }
        //арабские числа
        else if (!Rim.isRim(operands[0]) && !Rim.isRim(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRim = false;
        }
        //т.к. используются одновременно разные системы счисления
        else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        int arab = calc(num1, num2, oper);
        if (isRim) {
            //т.к. в римской системе нет отрицательных чисел
            if (arab <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            //конвертируем результат операции из арабского в римское
            result = Rim.convertToRim(arab);
        }
        else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arab);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Rim {
    static String[] rimArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRim(String val) {
        for (int i = 0; i < rimArray.length; i++) {
            if (val.equals(rimArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArab(String rim) {
        for (int i = 0; i < rimArray.length; i++) {
            if (rim.equals(rimArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRim(int arab) {
        return rimArray[arab];
    }

}