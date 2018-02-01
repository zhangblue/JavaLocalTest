package cn.com.guava.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.io.BaseEncoding;
import java.util.List;

/**
 * Created by zhangdi on 2017/06/02.
 */
public class GuavaStringTools {


    public static void main(String[] args) {
        String[] a = {"a","b","","c"};
        System.out.println(new GuavaStringTools().mkString(a,"/"));
    }

    //--------字符串连接器--------

    /**
     * 将列表转化为String
     *
     * @param listContent
     * @param separator
     * @return
     */
    public String mkListToString(List<String> listContent, String separator) {
        return Joiner.on(separator).skipNulls().join(listContent);
    }

    /**
     * 将数组转化为String
     *
     * @param listContent
     * @param separator
     * @return
     */
    public String mkString(String[] listContent, String separator) {
        return Joiner.on(separator).skipNulls().join(listContent);
    }


    //---------字符串匹配器---------

    /**
     * 把连续的多个空格替换为一个空格
     *
     * @param strLine
     * @return
     */
    public String trimManySpace(String strLine) {
        return CharMatcher.WHITESPACE.collapseFrom(strLine, ' ');
    }

    /**
     * 将字符串中的旧内容替换为新内容
     *
     * @param strLine
     * @param oldKeywords
     * @param newKeywords
     * @return
     */
    public String replaceString(String strLine, String oldKeywords, String newKeywords) {
        return CharMatcher.anyOf(oldKeywords).replaceFrom(strLine, newKeywords);
    }

    /**
     * 删除字符串开头与结尾的内容
     *
     * @param strLine
     * @param keywords
     * @return
     */
    public String trimBeginEndKey(String strLine, String keywords) {
        return CharMatcher.anyOf(keywords).trimFrom(strLine);
    }

    /**
     * 删除字符串开头的内容
     *
     * @param strLine
     * @param keywords
     * @return
     */
    public String trimBeginKey(String strLine, String keywords) {
        return CharMatcher.anyOf(keywords).trimLeadingFrom(strLine);
    }

    /**
     * 删除字符串中的制定内容
     *
     * @param strLine
     * @param keywords
     * @return
     */
    public String removeKey(String strLine, String keywords) {
        return CharMatcher.anyOf(keywords).removeFrom(strLine);
    }


    //--------字符串拆分器---------

    /**
     * 拆分字符串
     *
     * @param strLine   拆分字符串
     * @param key       分隔符
     * @param omitEmpty 是否忽略空内容
     * @return
     */
    public List<String> splitString(String strLine, String key, boolean omitEmpty) {
        if (omitEmpty) {
            return Splitter.on(key).omitEmptyStrings().trimResults().splitToList(strLine);
        } else {
            return Splitter.on(key).trimResults().splitToList(strLine);
        }
    }

    /**
     * 按照指定长度拆分字符串
     *
     * @param strLine
     * @param nLength
     * @return
     */
    public List<String> splitStringByLength(String strLine, int nLength) {
        return Splitter.fixedLength(nLength).splitToList(strLine);
    }

    public String base64Encoding(String base64){
        return new String(BaseEncoding.base64().decode(base64));
    }
}
