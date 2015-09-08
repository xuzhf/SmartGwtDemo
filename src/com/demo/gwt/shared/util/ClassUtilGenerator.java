package com.demo.gwt.shared.util;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.demo.gwt.client.pub.reflection.ClassForNameAble;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class ClassUtilGenerator extends Generator {
	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		ArrayList<JClassType> forNameAbleClassList = new ArrayList<JClassType>();

		for (JClassType type : context.getTypeOracle().getTypes()) {
			if (type.isAnnotationPresent(ClassForNameAble.class)) {
				forNameAbleClassList.add(type);
			}
		}

		JClassType targetType;
		try {
			targetType = context.getTypeOracle().getType(typeName);
		} catch (NotFoundException e) {
			logger.log(TreeLogger.ERROR, "No such type " + typeName, e);
			throw new UnableToCompleteException();
		}

		String targetPackageName = targetType.getPackage().getName();
		String targetClassName = targetType.getName() + "Impl";

		PrintWriter printWriter = context.tryCreate(logger, targetPackageName,
				targetClassName);

		if (printWriter == null)
			return targetPackageName + "." + targetClassName;

		ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
				targetPackageName, targetClassName);
		

		factory.addImplementedInterface(typeName);

		factory.addImport("java.util.HashMap");

		for (JClassType type : forNameAbleClassList) {
			String name = type.getPackage().getName() + "." + type.getName();
			factory.addImport(name);
		}

		SourceWriter writer = factory.createSourceWriter(context, printWriter);

		writer.println("private HashMap<String, Class> classHashMap = new HashMap<String, Class>();");
		writer.println();

		writer.println("@Override");
		writer.println("public void init()");
		writer.println("{");
		for (JClassType type : forNameAbleClassList) {
			String name = type.getPackage().getName() + "." + type.getName();
			writer.println("classHashMap.put(\"" + name + "\", "
					+ type.getName() + ".class);");
		}
		writer.println("}");
		writer.println();

		writer.println("@Override");
		writer.println("public <V> V newInstance(String className)");
		writer.println("{");
		writer.println("    if (classHashMap.containsKey(className))");
		writer.println("        {");

		for (JClassType type : forNameAbleClassList) {
			String className = type.getPackage().getName() + "."
					+ type.getName();

			writer.println("        if (className.equals(\"" + className
					+ "\"))");
			writer.println("            {");
			writer.println("                return (V) new " + type.getName()
					+ "();");
			writer.println("            }");
		}

		writer.println("        }");
		writer.println("        return null;");
		writer.println("}");
		writer.println();

		writer.println("@Override");
		writer.println("public <V> Class<V> forName(String name)");
		writer.println("{");
		writer.println("    return classHashMap.get(name);");
		writer.println("}");
		writer.println();

		writer.println("@Override");
		writer.println("public <V> V newInstance(Class<V> clazz)");
		writer.println("{");
		writer.println("    return newInstance(clazz.getName());");
		writer.println("}");
		writer.println();

		writer.commit(logger);
		return targetPackageName + "." + targetClassName;
	}
}
