Given N & M, Find the minimum number of primes required to make N. You can use first M primes to do so.

Example - N = 10, M = 1
=> 5 (2 + 2 + 2 + 2 + 2)

N = 11, M = 3
=> 3


##############################################

import java.util.*;

class getMinPrime
  {
  int[] getFirstMPrimesUsingSeive(int N, int M) {
  List<Int> mPrimes = new ArrayList<>();
  boolean[] isPrime = new boolean[N + 1];
  Arrays.fill(isPrime, true);
  isPrime[0] = isPrime[1] = false;
  for(int p = 2; p * p <= N; p++) {
    if(isPrime[p]) {
      for(int i = p * p; i <= N; i+=p) {
        isPrime[i] = false;
      }
    }
  }

  for(int i = 2; i <= N && mPrimes.size() < M; i++) {
    if(isPrime[i]) {
      mPrimes.add(i);
    }
  
  }
  return mPrimes.stream().mapToInt(i -> i).toArray();
 
}

int minPrimes(int n, int m) { 
  int[] primes = getFirstMPrimesUsingSeive(n,m);
  int[] dp = new int[n + 1];
  Arrays.fill(dp, Integer.MAX_VALUE);
  dp[0] = 0;
  for(int i = 1; i <= n; i++) {
    for(int prime: primes) {
      if(i - prime > 0 && dp[i - prime] != Integer.MAX_VALUE) {
        dp[i] = Math.min(dp[i], dp[i - prime] + 1);
      }
    }
  }
  return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
}

public static void main(String[] args) {
        int n = 11, m = 3;
        System.out.println(minPrimes(n, m)); // Output: 3
    }
}
