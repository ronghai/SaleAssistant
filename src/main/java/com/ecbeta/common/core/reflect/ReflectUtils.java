package com.ecbeta.common.core.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ecbeta.common.core.annotation.ParseMethodType;
import com.ecbeta.common.core.annotation.RequestParse;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectUtils {
    private static final Logger logger =   Logger.getLogger(ReflectUtils.class.getName()) ;

    
    public static Class<?> classForName (String className) throws ClassNotFoundException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            try {
                Thread thread = Thread.currentThread();
                ClassLoader threadClassLoader = thread.getContextClassLoader();
                return Class.forName(className, false, threadClassLoader);
            } catch (ClassNotFoundException cnfe2) {
                throw cnfe2;
            }
        }
    }

    
    public static Map<String, Field> getDeclaredFields(Map<String, Field> fields, Class<?> type, boolean allSuper) {
        if (fields == null) {
            fields = new HashMap<>();
        }
        for (Field field : type.getDeclaredFields()) {
            if (!fields.containsKey(field.getName())) {
                fields.put(field.getName(), field);
            }
        }
        if (allSuper && type.getSuperclass() != null) {
            fields = getDeclaredFields(fields, type.getSuperclass() , allSuper);
        }
        return fields;
    }
    
    
    public static List<Field> getDeclaredFields (List<Field> fields, Class<?> type, boolean allSuper) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if ( allSuper && type.getSuperclass() != null) {
            fields = getDeclaredFields(fields, type.getSuperclass() , allSuper);
        }
        return fields;
    }
    
    

    public static final Method findGetter (Object instance, Field field, Map<String, PropertyDescriptor> name2Property, RequestParse annotation) {
        String fieldName = field.getName();
        if (annotation == null || StringUtils.isEmpty(annotation.getter())) {
            PropertyDescriptor pro = name2Property == null ? null : name2Property.get(fieldName);
            if (pro != null && pro.getReadMethod() != null) {
                return pro.getReadMethod();
            }
        } else {
            Method getter = findMethod(instance, annotation.getter(), new Class<?>[] {});
            if (getter != null) {
                return getter;
            }
        }
        {
            Method getter = findMethod(instance, "get" + fieldName.substring(0, 1).toUpperCase() + (fieldName.length() > 1 ? fieldName.substring(1) : "" ), new Class<?>[] {});
            if (getter != null) {
                return getter;
            }
        }

        if (field != null && (Boolean.TYPE.equals(field.getType()) || Boolean.class.equals(field.getClass()) )) {
            Method getter = findMethod(instance, "is" + fieldName.substring(0, 1).toUpperCase() + (fieldName.length() > 1 ? fieldName.substring(1) : "" ), new Class<?>[] {});
            if (getter != null) {
                return getter;
            }
        }
        if (field != null && (Boolean.TYPE.equals(field.getType()) || Boolean.class.equals(field.getClass()) ) && fieldName.startsWith("is") && fieldName.length() > 3
                && fieldName.charAt(3) >= 'A' && fieldName.charAt(3) <= 'Z') {
            Method getter = findMethod(instance, fieldName, new Class<?>[] {});
            if (getter != null) {
                return getter;
            }
        }

        return null;
    }

    protected static boolean _SHOW_EXCEPTION = true;

    public static final Method findMethod (Object instance, String name, Class<?>[] clazz) {
        try {
            Method setter = instance.getClass().getMethod(name, clazz);
            return setter;
        } catch (SecurityException e) {
            logger.log(Level.WARNING, null, e);
        } catch (NoSuchMethodException e) {
            if (_SHOW_EXCEPTION) {
                logger.log(Level.WARNING, null, e);
                System.err.println("BaseServicerParaBean.__findMethod__");
                System.err.println("BaseServicerParaBean.__findMethod__ (" + name +")" + e); 
            }

        }
        return null;
    }
    
    
    public static final Method findSetter (Object instance, Field field, Map<String, PropertyDescriptor> name2Property, RequestParse annotation) {
        String fieldName = field.getName();
        if (annotation == null || StringUtils.isEmpty(annotation.setter())) {
            PropertyDescriptor pro = name2Property == null ? null : name2Property.get(fieldName);
            if (pro != null && pro.getWriteMethod() != null) {
                return pro.getWriteMethod();
            }
        } else {
            Method setter = findMethod(instance, annotation.setter(), new Class[] { field.getType() });
            if (setter != null) {
                return setter;
            }
            setter = findMethod(instance, annotation.setter(), new Class[] { String.class });
            if (setter != null) {
                return setter;
            }
        }
        if (annotation != null && !StringUtils.isEmpty(annotation.parser())) {
            
        } else {
            String setname = "set" + fieldName.substring(0, 1).toUpperCase() + (fieldName.length() > 1 ? fieldName.substring(1) : "" );
            {
                Method setter = findMethod(instance, setname, new Class[] { field.getType() });
                if (setter != null) {
                    return setter;
                }
            }

            {
                Method setter = findMethod(instance, setname, new Class[] { String.class });
                if (setter != null) {
                    return setter;
                }
            }
        }
        return null;
    }

    public static final Method findParser (Object instance, Field field, RequestParse annotation) {
        if (annotation != null && StringUtils.isNotEmpty(annotation.parser())) {
            try {
                Method parser = findMethod(instance, annotation.parser(), annotation.parseType() == ParseMethodType.ParseRequestWithAction ? new Class[] { HttpServletRequest.class,
                        String.class } : new Class[] { HttpServletRequest.class });
                if (parser != null) {
                    return parser;
                }
            } catch (SecurityException e) {
                logger.log(Level.WARNING, null, e);
            }
        }
        return null;
    }
    
    public static void updateFieldValue (Object instance, Field field, Method setterMethod, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        try {
            if (setterMethod != null) {
                setterMethod.invoke(instance, value);
            } else {
                field.setAccessible(true);
                field.set(instance, value);
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            
        }
    }
    
    public static void updateFieldValue (Object instance, Field field, Method setterMethod, boolean value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (setterMethod != null) {
            setterMethod.invoke(instance, value);
        } else {
            field.setAccessible(true);
            field.setBoolean(instance, value);
        }
    }

    
    public static Class<?>  findPropertyType (Field field, Method setterMethod) {

        if (setterMethod != null) {
            Class<?>[] parameterTypes = setterMethod.getParameterTypes();
            if (parameterTypes == null || parameterTypes.length != 1) {} else {
                return parameterTypes[0];
            }
        }
        if (field != null) {
            return field.getType();
        }
        return null;
    }
    
    public static boolean isPrimitive (Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class) || clazz.equals(Byte.class) || clazz.equals(Character.class) || clazz.equals(Double.class)
                || clazz.equals(Float.class) || clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Short.class) || clazz.equals(Date.class)
                || clazz.equals(InternetAddress.class);
    }

    
    public static Field getField (Class<?> ownerClass, String fieldName) {
        Field field = null;
        try {
            field = ownerClass.getDeclaredField(fieldName);
            return field;
        } catch (NoSuchFieldException | SecurityException e) {
            logger.log(Level.WARNING, null, e);
        } 
        if (ownerClass.getSuperclass() != null) {
            return getField(ownerClass.getSuperclass(), fieldName);
        } else {
            try {
                throw new NoSuchFieldException(fieldName);
            } catch (NoSuchFieldException e) {
               logger.log(Level.WARNING, null, e);
            }
            return field;
        }
    }
    
    
    
   

}
