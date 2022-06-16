package com.example.demo.util;

import com.example.demo.config.SpringContext;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 项目基础配置信息工具类
 *
 * @author:x
 * @date:2018/11/7 10:35
 */
public class ProjectInfoUtils {
    public static final String BASE_PACKAGE;
    public static final String BASE_PACKAGE_PREFIX;
    /*public static final String PROJECT_CONTEXT;
    public static final String PROJECT_APOLLO_DB_KEY;
    public static final int PROJECT_FEIGN_CONNECT_TIMEOUT_MILLIS;
    public static final int PROJECT_FEIGN_READ_TIMEOUT_MILLIS;
    public static final String PROJECT_AES_KEY;
    public static final String PROJECT_AES_OFFSET;
    public static final String PROJECT_JWT_SECRET;
    public static final String PROJECT_NACOS_URL;*/
    public static final String ENVIRONMENT;
    private static final String CLASS_LOADER_NAME = "java.lang.ClassLoader";
    public static PropertySource applicationProperty;

    /**
     * 系统编码（权限验证中使用）
     */
//    public static final String SYSTEM_CODE;

    static {
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        try {
            List<PropertySource<?>> propertySources = yamlPropertySourceLoader.load("application.yml", new
                    PathMatchingResourcePatternResolver().getResource("classpath:application.yml"));

            if (propertySources != null && !propertySources.isEmpty()) {
                applicationProperty = propertySources.get(0);
            }

//            Object profile = applicationProperty.getProperty("spring.profiles.active");

            String profile = SpringContext.getActiveProfile();
            if (!StringUtils.isEmpty(profile)) {
                String fileName = "application-" + profile + ".yml";
                if ("local".equals(profile)){
                    fileName="application.yml";
                }

                List<PropertySource<?>> propertySourceList = yamlPropertySourceLoader.load(fileName, new
                        PathMatchingResourcePatternResolver().getResource("classpath:" + fileName));

                if (propertySourceList != null && !propertySourceList.isEmpty()) {
                    applicationProperty = propertySourceList.get(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BASE_PACKAGE = getBasePackage();
        BASE_PACKAGE_PREFIX = getBasePackagePrefix();
       /* PROJECT_CONTEXT = getProjectContext();
        PROJECT_APOLLO_DB_KEY = getProjectDBKey();
        PROJECT_FEIGN_CONNECT_TIMEOUT_MILLIS = getProjectFeignConnectTimeoutMillis();
        PROJECT_FEIGN_READ_TIMEOUT_MILLIS = getProjectFeignReadTimeoutMillis();
        PROJECT_AES_KEY = getAESKey();
        PROJECT_AES_OFFSET = getAESOffset();
        PROJECT_JWT_SECRET = getJWTSecret();
        PROJECT_NACOS_URL = getNacos();*/
        ENVIRONMENT = getEnvironment();

//        SYSTEM_CODE = getSystemCode();
    }

    private static String getEnvironment() {
        String profile = SpringContext.getActiveProfile();
        return StringUtils.isEmpty(profile) ? "unknown" : profile;
    }

    private static String getNacos() {
        String serverArr = (String) applicationProperty.getProperty("nacos.config.server-addr");
        if (StringUtils.isEmpty(serverArr)) {
            throw new RuntimeException("application.yml中缺少nacos配置项：nacos.config.server-addr");
        }
        return serverArr;
    }

    /**
     * 获取包名前缀
     *
     * @param
     * @return java.lang.String
     * @author x
     * @date 2019/4/22 15:58
     */
    private static String getBasePackagePrefix() {
        String basePackagePrefix = (String) applicationProperty.getProperty("project.base-package-prefix");
        if (StringUtils.isEmpty(basePackagePrefix)) {
            throw new RuntimeException("application.yml中缺少包名前缀配置项：project.base-package-prefix");
        }
        return basePackagePrefix;
    }

    /**
     * 获取项目basePackage
     *
     * @return java.lang.String
     * @exception:
     * @author: x
     * @date:2018/11/9 16:45
     */
    private static String getBasePackage() {
        String basePackage = (String) applicationProperty.getProperty("project.base-package");
        if (!StringUtils.isEmpty(basePackage)) {
            return basePackage;
        }
        ClassLoader classLoader = ProjectInfoUtils.class.getClassLoader();
        Class loadClass = classLoader.getClass();
        while (!CLASS_LOADER_NAME.equals(loadClass.getName())) {
            loadClass = loadClass.getSuperclass();
        }
        Package[] packages;
        try {
            Method getPackages = loadClass.getDeclaredMethod("getPackages");
            getPackages.setAccessible(true);
            packages = (Package[]) getPackages.invoke(classLoader);
        } catch (Exception e) {
            throw new RuntimeException("获取项目包名失败");
        }
        for (Package p : packages) {
            if (p.getName().startsWith(getBasePackagePrefix())) {
                basePackage = p.getName();
                int lastChar = basePackage.indexOf(".", 8);
                if (lastChar < 0) {
                    return basePackage;
                }
                return basePackage.substring(0, basePackage.indexOf(".", 8));
            }
        }
        throw new RuntimeException("获取项目包名失败");
    }

    /**
     * 获取项目名称
     *
     * @param
     * @return java.lang.String
     * @author x
     * @date 2019/4/22 15:59
     */
    private static String getProjectContext() {
        try {
            String path = ProjectInfoUtils.class.getResource("/").getPath();
            String[] projectURL = path.split("/");
            return projectURL[projectURL.length - 3];
        } catch (Exception e) {
            throw new RuntimeException("获取项目名称失败");
        }
    }


    /**
     * 获取项目数据库key
     *
     * @return java.lang.String
     * @author x
     * @date 2019/4/29 17:20
     */
    private static String getProjectDBKey() {
        return (String) ProjectInfoUtils.applicationProperty.getProperty("project.db-key");
    }

    /**
     * 获取是否启动归档数据库
     *
     * @return boolean
     * @author x
     * @date 2019/4/29 17:20
     */
    private static boolean getProjectArchiveEnabled() {
        Object obj = ProjectInfoUtils.applicationProperty.getProperty("project.apollo.archive.enabled");
        if (StringUtils.isEmpty(obj)) {
            return false;
        }
        return (boolean) obj;
    }

    /**
     * feign连接超时
     *
     * @return int
     * @author x
     * @date 2019/4/30 10:16
     */
    private static int getProjectFeignConnectTimeoutMillis() {
        Object connectTimeoutMillis = ProjectInfoUtils.applicationProperty.getProperty("project.feign" +
                ".connect-timeout-millis");
        if (StringUtils.isEmpty(connectTimeoutMillis)) {
            return 20000;
        }
        return (int) connectTimeoutMillis;
    }

    /**
     * feign读取超时
     *
     * @return int
     * @author x
     * @date 2019/4/30 10:17
     */
    private static int getProjectFeignReadTimeoutMillis() {
        Object readTimeoutMillis = ProjectInfoUtils.applicationProperty.getProperty("project.feign" +
                ".read-timeout-millis");
        if (StringUtils.isEmpty(readTimeoutMillis)) {
            return 20000;
        }
        return (int) readTimeoutMillis;
    }

    /**
     * AESKey
     *
     * @return String
     * @author x
     * @date 2019/4/30 10:17
     */
    private static String getAESKey() {
        Object aesKey = ProjectInfoUtils.applicationProperty.getProperty("project.aes.key");
        if (StringUtils.isEmpty(aesKey)) {
            return "7hf^$Hd*g3@#!fd4";
        }
        return (String) aesKey;
    }

    /**
     * AESOffSet
     *
     * @return String
     * @author x
     * @date 2019/4/30 10:17
     */
    private static String getAESOffset() {
        Object aesOffset = ProjectInfoUtils.applicationProperty.getProperty("project.aes.offset");
        if (StringUtils.isEmpty(aesOffset)) {
            return "a1rg35Dew47f4ffk";
        }
        return (String) aesOffset;
    }

    /**
     * 获取JWT密匙
     *
     * @return java.lang.String
     * @author x
     * @date 2019/7/1 17:51
     */
    private static String getJWTSecret() {
        Object secret = ProjectInfoUtils.applicationProperty.getProperty("project.jwt.secret");
        if (StringUtils.isEmpty(secret)) {
            return "AgQGCAoMDfASFAIEBggKDA4QETAdBAYICgwOE52UAgQ=";
        }
        return (String) secret;
    }

    /**
     * 判断是否有druid配置
     *
     * @param
     * @return boolean
     * @author x
     * @date 2019/7/19 11:27
     */
    private static boolean hasDruidConfig() {
        String[] configs = ((MapPropertySource) applicationProperty).getPropertyNames();
        for (String config : configs) {
            if (config.contains("spring.datasource.druid")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取系统编码
     */
    private static String getSystemCode() {
        Object systemCode = ProjectInfoUtils.applicationProperty.getProperty("project.system-code");
        if (StringUtils.isEmpty(systemCode)) {
            //LoggerUtils.info(ProjectInfoUtils.class, "application.yml没有默认的系统编码配置项：systemCode");
            return "";
        }
        return (String) systemCode;
    }
}
