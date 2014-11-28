package kaitou.exercise.spring.util;

/**
 * String工具类.
 * User: 赵立伟
 * Date: 2014/5/23
 * Time: 10:11
 */
public abstract class StringUtils {

    /**
     * 判空
     *
     * @param str 校验字符串
     * @return null或空串即为真
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equalsIgnoreCase(str.trim());
    }
}
