package com.demo.gwt.client.reflection;

public interface IClassUtil
{
    public <V> Class<V> forName(String className);

    public <V> V newInstance(String className);

    public <V> V newInstance(Class<V> clazz);

    public void init();
}
