/*
 *  Copyright 2015 -  Hung Ming Tsoi
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */
package small.webhost;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.google.gson.Gson;

import spark.Request;

public class Main {

    final static Logger logger = LoggerFactory.getLogger( Main.class );
    final static Marker WEB_MARKER = MarkerFactory.getMarker( "WEB" );
    final static Marker JVM_MARKER = MarkerFactory.getMarker( "JVM" );

    private Main() {
        // Utility class
    }
    
    static {
        port( 8080 );
        externalStaticFileLocation( "public" );
    }

    public static void main( String[] args ) {

        final Gson gson = new Gson();
        logger.info( JVM_MARKER, "Main class successfully loaded." );

        get( "/", ( req, res ) -> {
            logWebRequest( req );
            return null;
        } );

        get( "/data/:name", ( req, res ) -> {
            logGetRequestWithHeader( req );
            return gson.toJson( new HtmlContent( req.params( ":name" ) ) );
        } );
    }

    private static void logGetRequestWithHeader( Request req ) {
        String header = "";
        for ( String s : req.headers() ) {
            header += String.format( "%s\t", req.headers( s ) );
        }
        String logInfo =
                String.format( "%nGET \t %s \t %s", req.ip(), header );
        logger.info( WEB_MARKER, logInfo );
    }

    private static void logWebRequest( final Request req ) {
        String logInfo =
                String.format( "%nREQUEST \t %s \t %s", req.ip(), req.userAgent() );
        logger.info( WEB_MARKER, logInfo );
    }
}
