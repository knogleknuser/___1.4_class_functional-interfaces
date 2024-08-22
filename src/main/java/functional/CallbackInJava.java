/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Purpose: Show how to use functional interfaces and lambda expressions in Java
 *
 * @author Thomas Hartmann
 */
public class CallbackInJava
{
    
    //In javascript we hava callbacks like: array.filter(function(){...})
    //In java an equivilent would be (not using lambda) to use an object
    public static void main( String[] args )
    {
        
        String[] strs = { "Andy", "Beatrice", "Charles", "Dorthea", "Eric", "Beatles" };
        
        System.out.println( "--------------------- Demo 1: Parameter to filter() is an object of type: Filter ------------------" );
        // Here we use a class that implements the interface Filter and pass it to the filter method
        Filter minLength5 = new FilterByMinLength5(); //FilterByMinLength5 is implemented as an inner class below
        String[] result1 = filter( strs, minLength5 );
        
        for ( String string : result1 ) {
            System.out.println( string );
        }
        
        System.out.println( "----------------------------- Demo2: Parameter is anonymous class implementation of Filter interface -------------------------" );
        String[] result2 = filter( strs, new Filter()
        {
            @Override
            public boolean validate( String element )
            {
                //return element.startsWith("Bea");
                return element.endsWith( "les" );
            }
        } );
        
        for ( String string : result2 ) {
            System.out.println( string );
        }
        
        System.out.println( "------------------------------- Demo 3: Parameter is Lambda expression ------------------------------" );
        String[] result3 = filter( strs, ( element ) -> element.contains( "a" ) ); // Lambda
        for ( String string : result3 ) {
            System.out.println( string );
        }
        
        
        //----------------------------------------------------------------------------------------------
        
        
        //Map
        System.out.println( "------------------------------- Demo 4: Mutate to UpperCase ------------------------------" );
        
        String[] resultM1 = map( strs, string -> string.toUpperCase() ); // Lambda
        
        for ( String string : resultM1 ) {
            System.out.println( string );
        }
        
        
        //Map
        System.out.println( "------------------------------- Demo 6: Mutate to LowerCase ------------------------------" );
        
        String[] resultM2 = map( strs, string -> string.toLowerCase() ); // Lambda
        
        for ( String string : resultM2 ) {
            System.out.println( string );
        }
        
        //Map
        System.out.println( "------------------------------- Demo 7: Mutate to LastLetter ------------------------------" );
        
        String[] resultM3 = map( strs, string -> String.valueOf( string.charAt( string.length() - 1 ) ) ); // Lambda
        
        for ( String string : resultM3 ) {
            System.out.println( string );
        }
        
        //Consumer
        System.out.println( "------------------------------- Demo 8: Consume to Print ------------------------------" );
        
        consume( strs, string -> System.out.println( string ) ); // Lambda
        
        //Consumer
        System.out.println( "------------------------------- Demo 9: Consume to errPrint ------------------------------" );
        
        consume( strs, string -> System.err.println( string ) ); // Lambda
        
        
        //Consumer
        System.out.println( "------------------------------- Demo 10: Consume to ReversePrint ------------------------------" );
        
        consume( strs, string -> System.out.println( new StringBuilder( string ).reverse().toString() )); // Lambda
        
    }
    
    
    // Functional interface with a single method
    private static interface Filter
    {
        
        boolean validate( String element );
        
    }
    
    private static interface Mapper
    {
        
        String map( String element );
        
    }
    
    // filter method here uses a functional interface
    private static String[] filter( String[] strs, Filter filterObj )
    {
        List< String > filtered = new ArrayList();
        for ( String str : strs ) {
            if ( filterObj.validate( str ) ) {
                filtered.add( str );
            }
        }
        return filtered.toArray( new String[ 0 ] );
    }
    
    private static String[] map( String[] strs, Mapper mapperObj )
    {
        String[] mapped = new String[ strs.length ];
        
        for ( int i = 0; i < mapped.length; i++ ) {
            mapped[ i ] = mapperObj.map( strs[ i ] );
        }
        
        return mapped;
    }
    
    private static void consume( String[] strs, Consumer< String > consumerObj )
    {
        for ( String str : strs ) {
            consumerObj.accept( str );
        }
    }
    
    private static class FilterByMinLength5 implements Filter
    {
        
        @Override
        public boolean validate( String element )
        {
            return element.length() >= 5;
        }
        
    }
    //Functional interface:
    
}