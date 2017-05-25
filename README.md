<h1>Technologies</h1>
<li>Spring mvc</li>
<li>Angularjs</li>
<li>Jdk 1.8</li>
<h1>Configuration on ubuntu</h1>
<h3>Install jdk1.8</h3>
<code>sudo apt-get update</code>
<code>sudo add-apt-repository ppa:webupd8team/java</code>
<code>sudo apt-get update</code>
<code>sudo apt-get install oracle-java8-installer</code>
<p>To automatically set up the Java 8 environment variables</p>
<code>sudo apt-get install oracle-java8-set-default</code>
<p>Check it</p>
<code>java -version</code>
<h3>Download tomcat 8.5.15<h3>
<p>Download <a href="https://tomcat.apache.org/download-80.cgi">tomcat-8.5.15</a> and extract on your machine</p>
<h3>Download eclipse IDE<h3>
<p>Download <a href="http://www.eclipse.org/downloads/eclipse-packages/">eclipse</a></p>
<h3>Project Configuration</h3>
<p>Clone the project from branch using <br/>
<code>sudo git clone -b master-app-v.0.0 https://github.com/SSPEC7/crackers.git </code><br/>
Now import the project in your eclipse as existing maven project and add your tomcat server in eclipse and enjoy.
</p>
<p><b>Note : </b>If maven not shown in your eclipse to import project then install maven from eclipse help-> Install New Software</p>
