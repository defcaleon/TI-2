import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Logic {
    private static final short[] arrOfPrimeNum = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
            179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251};

    public static long fastPower(long num, long degree) {

        if (degree == 0)
            return 1;
        if (degree % 2 == 1)
            return fastPower(num, degree - 1) * num;
        else {
            long b = fastPower(num, degree / 2);
            return b * b;
        }
    }

    public static int generatePrimeNum(int numOfBits) {

        Random rand = new Random();
        long maxNum = fastPower(2, numOfBits);
        long minNum = fastPower(2, numOfBits - 1);


        while (true) {
            int newNumber = (int) (rand.nextInt((int)maxNum - (int)minNum) + minNum);
            if (isPrime(newNumber)) {
                return newNumber;
            }

        }
    }

    static boolean isPrime(int n) {

        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime2(int number) {
        if (number < 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }

        int index = 0;
        while (index < arrOfPrimeNum.length && number > arrOfPrimeNum[index]) {
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
            int newNumber = rand.nextInt(number - 2) + 1;

           long x = fastPower(newNumber, t) % number;
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

    static void findPrimefactors(HashSet<Integer> s, int n) {
        while (n % 2 == 0) {
            s.add(2);
            n = n / 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                s.add(i);
                n = n / i;
            }
        }

        if (n > 2) {
            s.add(n);
        }
    }

    static int findPrimitive(int p) {

        Random rand = new Random();

        while (true) {

            HashSet<Integer> s = new HashSet<>();

            int phi = p - 1;

            findPrimefactors(s, phi);

            for (int r = 2; r <= phi; r++) {

                boolean flag = false;
                for (Integer a : s) {

                    if (power(r, phi / (a), p) == 1) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    return r;
                }
            }
        }

    }

    static int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % p;
            }
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public static int primitiveRootNumber(int p) {
        Random rand = new Random();
        int num;

        while (true) {
            num = rand.nextInt(p - 1) + 1;

            if (NOD(num, p) == 1) {
                int funcNum = eulerFunc(num);
                if (funcNum == 0) {
                    break;
                }
                if (fastPower(num, funcNum) % p == 1) {
                    boolean flag = true;
                    for (int i = 1; i < funcNum; i++) {
                        if (fastPower(i, funcNum) % p == 1)
                            flag = false;
                    }
                    if (flag) {
                        return funcNum;
                    }
                }


            }
        }
        return -1;
    }

    private static int eulerFunc(int n) {
        int result = n;
        for (int i = 2; i * i <= n; ++i)
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

        int biggest = Math.max(a, b);
        int smallest = Math.min(a, b);

        int remainder;
        do {

            remainder = biggest % smallest;

            if (remainder == 0) {
                return smallest;
            }

            biggest = smallest;
            smallest = remainder;

        } while (true);

    }

    public static Output encrypt(int numOfBits, String message) {
        Random rand = new Random();
        int primeNum = generatePrimeNum(numOfBits);
        System.out.println(primeNum + " : простое число");
        int rootNum = findPrimitive(primeNum);
        System.out.println(rootNum + " : первообразный корень");

        int secretNumber;

        do {
            secretNumber = rand.nextInt(primeNum-2)+1; //cамому вводить
        } while (NOD(secretNumber, primeNum - 1) != 1);
        System.out.println(secretNumber+" : секретный ключ");
        int y= mul(rootNum,secretNumber,primeNum);





        PublicKey key = new PublicKey(y, rootNum, primeNum);
        System.out.println(key.toString());

        int a1 = mul(key.getG(),secretNumber, key.getP());

        ArrayList<Integer> bArr = new ArrayList<>();
        ArrayList<Integer> aArr = new ArrayList<>();

        for (char c : message.toCharArray()) {
            System.out.print((int)c + " ");
            int b=mull(mul(key.getY(),secretNumber, key.getP()),(int)c, key.getP());
            int a=mul(key.getG(),secretNumber, key.getP());
            bArr.add(b);
            aArr.add(a);
        }


        System.out.println();
        for (int i=0;i<bArr.size();i++) {
            System.out.print(bArr.get(i) + " ");
            System.out.print(aArr.get(i) + " ");
        }
        System.out.println();



        return new Output(aArr,bArr,key);
    }

    public static int mul(int a, int b, int n)
    {
        // a^b mod n - возведение a в степень b по модулю n
        int tmp = a;
        int sum = tmp;
        for (int i = 1; i < b; i++)
        {
            for (int j = 1; j < a; j++)
            {
                sum += tmp;
                if (sum >= n)
                {
                    sum -= n;
                }
            }
            tmp = sum;
        }
        return tmp;
    }

    public static int mull(int a, int b, int n)
    {
        // a*b mod n - умножение a на b по модулю n
        int sum = 0;
        for (int i = 0; i < b; i++)
        {
            sum += a;
            if (sum >= n)
            {
                sum -= n;
            }
        }
        return sum;
    }

    public void decrypt(Output output, int secretKey) {
        for (int i=0;i<output.aArr.size();i++) {

            char c = (char) (mull(mul(output.aArr.get(i),output.key.getP()-1-secretKey, output.key.getP()),output.bArr.get(i), output.key.getP()));
            System.out.print(c+" ");
        }
    }
}
