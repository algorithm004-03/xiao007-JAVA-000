/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.jupiter.api.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author liuwei
 */
public class ProxyTest {

    @Test
    public void test() throws IOException {
        final IExample example = new ExampleImpl();
        final IExample exampleProxy = (IExample) Proxy.
                newProxyInstance(example.getClass().getClassLoader(),
                        example.getClass().getInterfaces(),
                        new ProxyInvocationHandler(example));
        System.out.println(exampleProxy.hello());

        byte[] bts = ProxyGenerator.generateProxyClass("$ExampleProxy", example.getClass().getInterfaces());
        FileOutputStream f = new FileOutputStream(new File("D:\\Code\\Java\\self\\JAVA-000\\homework\\mybatis\\jdkProxy\\src\\main\\resources\\$GameProxy.class"));
        f.write(bts);
        f.flush();
        f.close();
    }
}
