

cd Laboratorio\Jar
call mvn package -PotherOutputDir
cd ..\..\Archivos
RD /S /Q archive-tmp
RD /S /Q classes
RD /S /Q generated-sources
RD /S /Q maven-archiver
RD /S /Q maven-status
DEL /Q Jar-1.0.jar
cd ..
call ant -f "Laboratorio\\LabWeb\\LabWeb" -Dnb.internal.action.name=build -DforceRedeploy=false "-Dbrowser.context=Laboratorio\\LabWeb\\LabWeb" dist
copy Laboratorio\LabWeb\LabWeb\dist\LaboratorioWeb.war Archivos
copy Laboratorio\LabWeb\LabWeb\dist\LaboratorioWeb.war C:\apache-tomcat-9.0.38\apache-tomcat-9.0.38\webapps
MKDIR %USERPROFILE%\config
echo web.link=http://localhost:9128/publicador > %USERPROFILE%\config\config.properties
echo web.link=http://localhost:9128/publicador > %USERPROFILE%\config\local_config.properties
call javaw -jar Archivos\Jar-1.0-jar-with-dependencies.jar 
