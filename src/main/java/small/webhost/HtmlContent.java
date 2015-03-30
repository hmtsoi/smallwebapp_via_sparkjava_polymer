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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

class HtmlContent {

    final static private Charset CHARSET = Charset.forName( "UTF-8" );
    final static private String HTML_DIR = "html_data/";

    final static Logger logger = LoggerFactory.getLogger( HtmlContent.class );
    final static Marker JVM_MARKER = MarkerFactory.getMarker( "JVM" );

    private String html = null;

    HtmlContent( final String reqParam ) {
        this.html = readContent( reqParam );
    }

    private String readContent( final String reqParam ) {
        final Path path = Paths.get( HTML_DIR + reqParam );
        final StringBuilder strBuilder = new StringBuilder();
        try ( BufferedReader reader = Files.newBufferedReader( path, CHARSET ) ) {
            reader.lines().forEach( l -> strBuilder.append( l ) );
        } catch ( IOException e ) {
            logger.warn( JVM_MARKER, e.getMessage(), e );
        }
        return strBuilder.toString();
    }
}
