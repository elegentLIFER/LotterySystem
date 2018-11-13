package com.sxmh.wt.lotterysystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Wang Tao on 2018/4/18 0018.
 */

public class RandomUtil {

    /**
     * 生成几个在start和end之间（包含）的不重复的随机数
     *
     * @param start
     * @param end
     * @param amount
     * @return
     */
    public static int[] getRandomInt(int start, int end, int amount) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        int[] randomArr = new int[amount];
        for (int i = 0; i < amount; i++) {
            boolean noChongfu = true;
            int r = 0;

            loop:
            while (noChongfu) {
                r = random.nextInt(end - start + 1) + start;

                for (int j = 0; j < randomArr.length; j++) {
                    if (randomArr[j] == r) {
                        noChongfu = true;
                        continue loop;
                    }
                }
                noChongfu = false;
            }

            randomArr[i] = r;
        }

        return randomArr;
    }
}
