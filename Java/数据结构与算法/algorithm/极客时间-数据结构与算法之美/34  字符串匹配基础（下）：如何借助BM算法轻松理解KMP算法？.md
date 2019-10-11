[TOC]

# 34 | 字符串匹配基础（下）：如何借助BM算法轻松理解KMP算法？

### 参考

* [字符串匹配基础（下）：如何借助BM算法轻松理解KMP算法？](https://time.geekbang.org/column/article/71845)

## 一、KMP算法

* 当模式串和主串的某个字符不匹配的时候，跳过一些肯定不匹配的情况。将模式串往后多滑动几位
* 坏字符规则和好前缀规则
* 从前往后进行匹配

### 1.1 坏字符

### 1.2 好前缀

* 拿模式串的好前缀的候选，用好前缀的后缀字串和模式串的前缀字串进行匹配，记录到数组next中
* ![](https://github.com/nullWolf007/images/raw/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E7%AE%97%E6%B3%95/string_match_5.jpg)

* 例如aba的好前缀，存在后缀a与模式串的前缀a对应，则next[2]=0;

### 1.3 代码实现

```java
public class JikeStrKMP {

	public static void main(String[] args) {
		char[] master = { 'a', 'b', 'a', 'b', 'a', 'c', 'd' };
		char[] pattern = { 'b', 'a', 'c' };

		System.out.println(kmp(master, pattern));
	}

	public static int kmp(char[] master, char[] pattern) {
		int masterLength = master.length;
		int patternLength = pattern.length;

		int[] next = getNexts(pattern);
		printInts(next);
		int j = 0;
		//好前缀规则
		for (int i = 0; i < masterLength; i++) {
			//
			while (j > 0 && master[i] != pattern[j]) {
				j = next[j - 1] + 1;
			}
			//如果相等的话就+1
			if (master[i] == pattern[j]) {
				j++;
			}
			if (j == patternLength) {
				return i - patternLength + 1;
			}
		}

		return -1;
	}

	/**
	 * 获取next数组
	 */
	public static int[] getNexts(char[] pattern) {
		int patternLength = pattern.length;

		int[] next = new int[patternLength];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < patternLength; i++) {
			// 递归求 pattern[0,i-1]的最长可匹配前缀的pattern[0,k]，且pattern[k + 1] == pattern[i]
			while (k != -1 && pattern[k + 1] != pattern[i]) {
				// 此時pattern[0,i-1]的最长可匹配前缀的pattern[0,k]，但是pattern[k + 1] != pattern[i]

				// 需求pattern[0,i-1]次长可匹配后缀，次长可匹配后缀子串肯定包含在最长可匹配后缀子串中
				// 而pattern[0,i-1]的最长可匹配前缀的pattern[0,k]
				// 于是查找pattern[0,i-1]的次长可匹配后缀就可以变成查找pattern[0,k]的最长匹配后缀的问题
				// 直到找到pattern[k + 1] == pattern[i]或者k == -1结束循环
				k = next[k];
			}
			// pattern[0,i-1]的最长可匹配前缀的pattern[0,k-1],如果k=-1表示不存在，只用比较pattern[i]和pattern[0]
			if (pattern[i] == pattern[k + 1]) {
				k++;// 如果相等的话 +1
			}
			next[i] = k;
		}

		return next;
	}

	public static void printInts(int[] next) {
		for (int i = 0; i < next.length; i++) {
			System.out.println(i + ":" + next[i]);
		}
	}
}
```



