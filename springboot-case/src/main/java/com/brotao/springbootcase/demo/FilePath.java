package com.brotao.springbootcase.demo;

import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

public class FilePath {

    static Set<String> suffixs = new HashSet<>();
    static {
        suffixs.add(".java");
        suffixs.add(".xml");
    }

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        File root = new File("d:\\github-repo\\service-base\\service-commons\\");
        iterateDir(root);
    }

    public static void iterateDir(File rootDir) {
        if (rootDir.isDirectory()) {
            File[] files = rootDir.listFiles();
            for (File file : files) {
                iterateDir(file);
            }
        }
        if (rootDir.isFile()) {
            for (String suffix : suffixs) {
                if (rootDir.getName().endsWith(suffix)) {
                    System.out.println(rootDir.getPath());
                    break;
                }
            }
        }
    }

    static String PROJECT = "Sonar_DataService-commons";

    public static void createFile() {
        File root = new File("C:\\Users\\luotao\\Desktop\\sonar\\Sonar_DataService-base");
        String[] files = {
                "pom.xml",
                "src/main/java/com/brotao/service/commons/assembly/BaseClassLoader.java",
                "src/main/java/com/brotao/service/commons/assembly/CachingClassLoader.java",
                "src/main/java/com/brotao/service/commons/assembly/ClassUtils.java",
                "src/main/java/com/brotao/service/commons/db/BaseConfigConnection.java",
                "src/main/java/com/brotao/service/commons/db/DBCon.java",
                "src/main/java/com/brotao/service/commons/db/DBConfigBuilder.java",
                "src/main/java/com/brotao/service/commons/db/DBConParamReader.java",
                "src/main/java/com/brotao/service/commons/db/ExtDataSource.java",
                "src/main/java/com/brotao/service/commons/db/IDBConnectionConfig.java",
                "src/main/java/com/brotao/service/commons/db/JEDB.java",
                "src/main/java/com/brotao/service/commons/db/TypeDefine.java",
                "src/main/java/com/brotao/service/commons/http/AuthSSLInitializationError.java",
                "src/main/java/com/brotao/service/commons/http/AuthSSLProtocolSocketFactory.java",
                "src/main/java/com/brotao/service/commons/http/AuthSSLX509TrustManager.java",
                "src/main/java/com/brotao/service/commons/http/CustomSecureProtocolSocketFactory.java",
                "src/main/java/com/brotao/service/commons/http/CustomX509TrustManager.java",
                "src/main/java/com/brotao/service/commons/http/HttpClientFactory.java",
                "src/main/java/com/brotao/service/commons/http/HttpProtocalHelp.java",
                "src/main/java/com/brotao/service/commons/mq/IMQConsumer.java",
                "src/main/java/com/brotao/service/commons/mq/IMQPublisher.java",
                "src/main/java/com/brotao/service/commons/mq/MQFactory.java",
                "src/main/java/com/brotao/service/commons/mq/MultMqFactory.java",
                "src/main/java/com/brotao/service/commons/mq/TopicInfo.java",
                "src/main/java/com/brotao/service/commons/thread/DefaultObjectPoolConfig.java",
                "src/main/java/com/brotao/service/commons/thread/ExecutorsExtend.java",
                "src/main/java/com/brotao/service/commons/thread/ThreadPoolConfig.java",
                "src/main/java/com/brotao/service/commons/thread/ThreadPoolExecutorExtend.java",
                "src/main/java/com/brotao/service/commons/util/buffer/AbstractArrayBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayByteBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayCharBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayDoubleBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayFloatBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayIntBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayLongBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayObjectBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayObjectPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ArrayShortBufferPool.java",
                "src/main/java/com/brotao/service/commons/util/buffer/BufferUtil.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastBooleanBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastByteBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastCharBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastDoubleBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastFloatBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastIntBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastLongBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/FastShortBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/HeapObjectBuffer.java",
                "src/main/java/com/brotao/service/commons/util/buffer/ObjectBuffer.java",
                "src/main/java/com/brotao/service/commons/util/collection/BooleanArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/ByteArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/CollectionUtils.java",
                "src/main/java/com/brotao/service/commons/util/collection/ConcurrentHashSet.java",
                "src/main/java/com/brotao/service/commons/util/collection/DoubleArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/FloatArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashDoubleMultiMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashDoubleMultiSetMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashIntMultiMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashIntMultiSetMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashLongMultiMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/HashLongMultiSetMap.java",
                "src/main/java/com/brotao/service/commons/util/collection/IntArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/LongArrayList.java",
                "src/main/java/com/brotao/service/commons/util/collection/ShortArrayList.java",
                "src/main/java/com/brotao/service/commons/util/DateJsonValueProcessor.java",
                "src/main/java/com/brotao/service/commons/util/EMailHelper.java",
                "src/main/java/com/brotao/service/commons/util/EndianBinaryReader.java",
                "src/main/java/com/brotao/service/commons/util/EndianBinaryWriter.java",
                "src/main/java/com/brotao/service/commons/util/EndianSupport.java",
                "src/main/java/com/brotao/service/commons/util/GzipHelp.java",
                "src/main/java/com/brotao/service/commons/util/IDGenerator.java",
                "src/main/java/com/brotao/service/commons/util/IWordItem.java",
                "src/main/java/com/brotao/service/commons/util/JaxbUtils.java",
                "src/main/java/com/brotao/service/commons/util/JsonUtil.java",
                "src/main/java/com/brotao/service/commons/util/LDAPClient.java",
                "src/main/java/com/brotao/service/commons/util/MD5.java",
                "src/main/java/com/brotao/service/commons/util/PinYinUtil.java",
                "src/main/java/com/brotao/service/commons/util/Position.java",
                "src/main/java/com/brotao/service/commons/util/Props.java",
                "src/main/java/com/brotao/service/commons/util/SimpleSlideWindowProtocol.java",
                "src/main/java/com/brotao/service/commons/util/SMSHelper.java",
                "src/main/java/com/brotao/service/commons/util/StockPinYinUtil.java",
                "src/main/java/com/brotao/service/commons/util/StopBidCodec.java",
                "src/main/java/com/brotao/service/commons/util/SubnetHelp.java",
                "src/main/java/com/brotao/service/commons/util/TimerTaskUtil.java",
                "src/main/java/com/brotao/service/commons/util/ToolUtils.java",
                "src/main/java/com/brotao/service/commons/util/ValueNode.java",
                "src/main/java/com/brotao/service/commons/util/VerifyUtils.java",
                "src/main/java/com/brotao/service/commons/util/WordDic.java",
                "src/main/java/com/brotao/service/commons/util/WordDicManager.java",
                "src/main/java/com/brotao/service/commons/util/WordDicValues.java",
                "src/main/java/com/brotao/service/commons/util/WordItem.java",
                "src/main/java/com/brotao/service/commons/util/XmlHelp.java",
        };


    }
}
