# Tomcat Properties #

Para poder configurar el tomcat para que tome properties externas hay que hacer lo siguiente.

  * Editar el archivo tomcat/conf/catalina.properties

  * Crear el archivo ontrack.properties (si quieren cambiar el nombre, tienen que editar también el applicationContext.xml de OnTrack-SOA para que lo tome)

  * Resetear el tomcat


En el catalina.properties hay que buscar la key common.loader y agregar la carpeta donde queremos que esten nuestras properties. La carpeta que elegí es /conf, pero puede ser cualquiera.

common.loader=${catalina.base}/lib,${catalina.base}/lib/**.jar,${catalina.home}/lib,${catalina.home}/lib/**.jar,${catalina.home}/conf


El archivo properties tiene las siguientes keys

hibernate.connection.url = jdbc:mysql://localhost:3306/ontrack?autoReconnect=true

hibernate.connection.username = 

hibernate.connection.password = 