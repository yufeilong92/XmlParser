package com.lawyee.cardstck.xml;

import java.io.InputStream;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CardStck
 * @Package com.lawyee.cardstck.xml
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/23 11:58
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface BookParser {
    /**
     * 解析输入流 得到book对象集合
     *
     * @param is
     * @return
     * @throws Exception
     */
    public List<Book> parse(InputStream is) throws Exception;

    /**
     * 序列化book对象，的到xml形式字符串
     * @param Books
     * @return
     * @throws Exception
     */
    public String serizalize(List<Book> Books) throws Exception;
}
