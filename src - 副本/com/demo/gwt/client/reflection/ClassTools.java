package com.demo.gwt.client.reflection;

import com.google.gwt.core.client.GWT;

public class ClassTools
{
    private static IClassUtil classUtil;

    static
    {
        classUtil = GWT.create(IClassUtil.class);
        classUtil.init();
    }

    public static <V> Class<V> forName(String className)
    {
        return classUtil.forName(className);
    }

    public static <V> V newInstance(String className)
    {
        return classUtil.newInstance(className);
    }

    public static <V> V newInstance(Class<V> clazz)
    {
        return classUtil.newInstance(clazz);
    }
}
