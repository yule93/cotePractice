public class bunhaljungbokex {
    public static void main(String[] args) {
        
    }

    static int callCnt;

    static long exp2(long x, long y) {
        callCnt++;
        if(y == 1) return x;
        
        long r = exp2(x, y/2);
        long res = r*r;
        if(y%2 == 1) res *= x;
        return res;
    }

    static long exp1(long x, long y) {
        callCnt++;
        if(y == 1) return x;
        return x * exp1(x, y-1);
    }
}
