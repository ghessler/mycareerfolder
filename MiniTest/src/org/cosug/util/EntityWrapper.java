package org.cosug.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Entity;

/**
 * @author SpringsTeam
 *
 */
public abstract class EntityWrapper< T >
{
   public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException
   {
//      System.out.println(new EntityWrapper<String>().createEntity().getClass().getName());
//      System.out.println(new EntityWrapper<Double>().createEntity().getClass().getName());
      
   }
   public T createEntity() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException
   {
      Method method = getClass().getMethod("createEntity");
      Type returnType = method.getGenericReturnType();
      if( returnType instanceof ParameterizedType )
      {
         Type[] types = ((ParameterizedType)returnType).getActualTypeArguments();
         if( types.length > 0 )
         {
            System.err.println("Problem, only expecting one Parameterized Type");
         }
         
         return (T) types[0].getClass().newInstance();
      }
      else if( true )
      {
         
      }
      
      return null;
   }
   
   public abstract T create();
}

