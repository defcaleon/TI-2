import java.util.ArrayList;
import java.util.Random;

public class Logic {
    private static final short[] arrOfPrimeNum = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
            179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251};

    public static int fastPower(int num, int degree) {

        if (degree == 0)
            return 1;
        if (degree % 2 == 1)
            return fastPower(num, degree - 1) * num;
        else {
            int b = fastPower(num, degree / 2);
            return b * b;
        }
    }

    public static int generatePrimeNum(int numOfBits) {

        Random rand = new Random();
        int maxNum = fastPower(2, numOfBits);
        int minNum = fastPower(2, numOfBits - 1);



        while (true) {
            int newNumber = rand.nextInt(maxNum - minNum) + minNum;
            if (isPrime(newNumber)) {
                return newNumber;
            }

        }
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }

        int index = 0;
        while (number > arrOfPrimeNum[index] && index < arrOfPrimeNum.length) {
            if (number % arrOfPrimeNum[index] == 0) {
                return false;
            }
            index++;
        }

        int[] stArr = _2st(number - 1);
        int s = stArr[0];
        int t = stArr[1];
        int numOfRounds = (int) Math.round(Math.log(number) / Math.log(2));
        Random rand = new Random();

        for (int i = 0; i < numOfRounds; i++) {
            int newNumber = rand.nextInt(number - 1) + 2;

            int x = fastPower(newNumber, t) % s;
            if (x == 1 || x == newNumber - 1) {
                continue;
            }

            boolean flag = true;

            for (int j = 0; j < s - 1; j++) {

                x = fastPower(x, 2) % newNumber;
                if (x == 1) {
                    return false;
                }
                if (x == newNumber - 1) {
                    flag = false;
                    break;
                }

            }
            if (!flag) {
                return false;
            }


        }
        return true;
    }

    private static int[] _2st(int number) {
        int[] arr = new int[2];
        if (number % 2 == 1) {
            return null;
        }
        int degree = 0;
        do {
            number /= 2;
            degree++;
        } while (number % 2 == 0 && number > 0);
        if (number % 2 == 0) {
            return null;
        } else {
            arr[0] = degree;
            arr[1] = number;
            return arr;
        }
    }

    public  static int primitiveRootNumber(int p){
        Random rand= new Random();
        while (true){
            int num = Math.abs(rand.nextInt(10000)+255);
            int funcNum = eulerFunc(num);
            if(fastPower(num,funcNum)%p==1){
                return funcNum;
            }
        }
    }
    private static int eulerFunc (int n) {
        int result = n;
        for (int i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
    }
    public static int NOD(int a, int b) {

        if (a == 0 || b == 0) {
            return 0;
        }

        int biggest = a > b ? a : b;
        int smallest = a > b ? b : a;

        int remainder = smallest;
        do {

            remainder = biggest % smallest;

            if (remainder == 0) {
                return smallest;
            }

            biggest = smallest;
            smallest = remainder;

        } while (true);

    }

    public  static PublicKey encrypt(int numOfBits,String message){
        Random rand = new Random();
        int primeNum = generatePrimeNum(numOfBits);
        System.out.println(primeNum);
        int rootNum =primitiveRootNumber(primeNum);
        System.out.println(rootNum);

        int x;
        while(true){
            x=rand.nextInt(primeNum);
            if(NOD(x,primeNum-1)==1){
                break;
            }
        }

        System.out.println(x);

        int y=fastPower(rootNum,x)%primeNum;
        System.out.println(y);

        int secretNumber;
        while(true){
            secretNumber=rand.nextInt(primeNum);
            if(NOD(x,primeNum-1)==1){
                break;
            }
        }
        int firstNum =fastPower(rootNum,secretNumber)%primeNum;
        ArrayList<Integer> builder = new ArrayList<>();
        for (char c: message.toCharArray()) {
            int secret=(int)c *fastPower(y,secretNumber)%primeNum;
            builder.add(secret);
        }
        for(int n:builder){
            System.out.print(n+" ");
        }
        System.out.println();
        PublicKey key =new PublicKey(rootNum,primeNum,y);

        for(int n:builder){
            char c=(char)((fastPower(firstNum,-x))*n%primeNum);
            System.out.print(c);
        }
        return key;
    }
    public void decrypt(int[]message,int secretKey,PublicKey key){

    }
}
