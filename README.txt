### Solución: Administración de Temas de Conferencia ###

Se entregan 2 proyectos, uno con "maven+junit" y otro todo en uno (en caso de falla de ambientación):
De preferencia usar 1, ya que este se encuentra estructurado en paquetes y se agregaron test unitarios.

### Compilación y ejecución ### 
* JDK 8
* Maven 3.x 

1. conf_manager 	(dir con proyecto maven)
	1.1 "mvn clean install" 					(para compilar y correr tests)
	1.2 "java -jar target/conf-manager.jar RELATIVE_PATH_TO_FILE"	(para ejecutar)
	EJ: "java -jar target/conf-manager.jar in.txt"			(ejemplo de ejecucion con in.txt como archivo de entrada, ubicado en dirbase:conf_manager)

2. conf-manager-se 	(dir con 1-clase principal)
	2.1 "javac App.java"			(para compilar)
	2.2 "java App RELATIVE_PATH_TO_FILE" 	(para ejecutar)
	EJ: "java App in.txt"			(ejemplo de ejecucion con in.txt como archivo de entrada, ubicado en dirbase:conf-manager-se)
 

### Suposiciones ###
- Se asume que la entrada contiene el total de charlas a calendarizar (no sobran).
