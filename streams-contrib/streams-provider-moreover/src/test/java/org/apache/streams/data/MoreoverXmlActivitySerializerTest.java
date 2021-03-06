package org.apache.streams.data;


import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.streams.pojo.json.Activity;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

import static org.apache.streams.data.util.MoreoverTestUtil.test;

public class MoreoverXmlActivitySerializerTest {
    ActivitySerializer serializer;
    private String xml;

    @Before
    public void setup() throws IOException {
        serializer = new MoreoverXmlActivitySerializer();
        xml = loadXml();
    }

    @Test
    public void loadData() throws Exception {
        List<Activity> activities = serializer.deserializeAll(Lists.newArrayList(xml));
        for (Activity activity : activities) {
            test(activity);
        }
    }

    private String loadXml() throws IOException {
        StringWriter writer = new StringWriter();
        InputStream resourceAsStream = this.getClass().getResourceAsStream("moreover.xml");
        IOUtils.copy(resourceAsStream, writer, Charset.forName("UTF-8"));
        return writer.toString();
    }

}
