package util;

/**
 * description: Kaisapwd  凯撒加密 <br>
 * date: 2021/6/18 22:42 <br>
 * author: s1mple <br>
 * version: 1.0 <br>
 */
public class Kaisapwd {
    public static void main(String[] args) {
        char str[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char out : str) {
            System.out.print(out + " ");
        }
        for (int i = 0; i < 26; i++) {
            if (i < 23) {
                str[i] += 3;
            } else {
                str[i] -= 23;
            }
        }
        System.out.println("\n");
        for (char out : str) {
            System.out.print(out + " ");
        }
    }
}
