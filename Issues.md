# Issues #

## Contenido ##

---


Contiene las siguientes User Stories
  * Creación de un tipo de Issue
  * Asignacion de un tipo de Issue a un proyecto
  * Reportar un Issue
  * Modificar estado de un Issue
  * Buscar Issue
  * Buscar Issues por Filtro



---


## User Stories ##


### _Creación de un tipo de Issue_ ###

**Descripción**

'Como usuario administrador de un proyecto deseo poder generar un tipo Issue asociado al mismo'

**Criterios de aceptación**
  1. Mediante una interface podré escribir un nombre que identifique a mi issue
  1. Si el nombre que deseo para mi tipo de Issue existe previamente, podré utilizarlo en mi proyecto
  1. El nombre del tipo de Issue creado estará disponible para ser incorporado a los demás proyectos
  1. Si el nombre se encuentra repetido el sistema me lo indicará, no es necesario generarlo ya que existe dicho nombre
  1. El issue contará con una serie de campos por defecto propios de todos los issues, 'Título' - 'Descripción' - 'Process History' - 'Reporter' - 'Responsable'- 'Estado' - 'Tipo de Issue'
  1. Puedo generar nuevos campos dinamicamente de tipo texto para mis issues
  1. Puedo generar el nombre del campo que deseo en mi Issue o en caso de existir reutilizarlo

**Especificación por Ejemplos.**
  1. Voy a la configuración de mi proyecto y selecciono asignar un tipo de Issue, escribo el issue Bug, el sistema verifica que no existe ese nombre en todos los tipos de Issue creados, lo incorpora a la lista de existentes a mi proyecto y a la lista de los tipos de Issues general.
  1. Edito mi Tipo de Issue incorporado, incorporo la propiedad 'información adicional' y creo una nueva propiedad 'Pre-Condición'

### _Asignación de un tipo de Issue al proyecto_ ###

**Descripción**

'Como usuario administrador de un proyecto deseo poder asignar un tipo de issue a un proyecto'

**Criterios de aceptación**
  1. Si el tipo de issue ya se encuentra asignado a ese proyecto entonces no se debe agregar un duplicado.
  1. Un mismo tipo de issue puede estar asignado a cualquier cantidad de proyectos.

**Especificación por Ejemplos.**
  1. Voy a la configuración de mi proyecto y selecciono asignar un tipo de Issue, Verifico que el tipo de Issue de nombre 'Requerimiento' existe en mi base de datos y lo vuelvo a ingresar, luego verifico que el issue 'Requerimiento' es único

### _Reportar un Issue_ ###

**Descripción**

'Como usuario deseo poder reportar un issue en un proyecto'

**Criterios de aceptación**
  1. Mediante una interface podre levantar un issue y asignarlo a un proyecto al que yo como usuario tenga acceso.
  1. Cada issue puede tener una cantidad diferente de datos dependiendo de las propiedades del mismo pero todos tienen que tener un ID único que lo identifique en el sistema.
  1. Cada proyecto puede tener N issues.
  1. Issues diferentes pueden tener campos repetidos (incluyendo 'Titulo' - 'Descripcion', etc) ya que todos van a tener un ID diferente.
  1. Cada issue consta de un usuario que lo reporta.

**Especificación por Ejemplos.**
  1. Dentro de la vista de un proyecto uso la opción para levantar un issue e ingreso todos los campos necesarios. Verifico que en la lista de issues se encuentre el que acabo de crear.

### _Modificar Estado de un Issue_ ###

**Descripción**

'Como usuario deseo poder cambiar el estado de un issue reportado'

**Criterios de aceptación**
  1. Mediante una interface puedo cambiar el estado actual de un issue.
  1. Los estados posibles son únicamente los estados validos asignados a ese issue dependiendo del workflow.
  1. Cuando cualquier usuario haga un cambio en el estado de un issue el dueño de ese issue debe ser notificado.
  1. Podré registrar una trazabilidad entre el ciclo de vida del issue en donde indique qué usuario realizó un cambio de estado en su workflow.
  1. Será posible insertar comentarios en el issue, la herramienta registrará usuario y fecha en que realizó el comentario.

**Especificación por Ejemplos.**
  1. Desde la vista detallada del issue cambio el estado actual por otro valido. Verifico que en la lista de issues figure con el nuevo estado.
  1. Desde la vista detallada de un issue hago el cambio por un estado diferente al actual.
  1. Tras guardar un cambio de estado podré reflejar el cambio en la tabla process history

### _Buscar Issue_ ###

**Descripción**

'Como usuario deseo poder buscar issues en base a los campos 'Titulo'

**Criterios de aceptación**
  1. Mediante una interface podre ingresar un string de busqueda y obtener una lista de resultados.
  1. Se debera mostrar una lista informacion sobre cada issue que devuelva la busqueda y ademas un link a la vista detallada de cada uno.
  1. La lista de resultados debera contener únicamente issues cuyos campos 'Titulo' contengan la cadena usada para la busqueda.

### _Buscar Issues por Filtro_ ###

**Descripción**

'Como usuario deseo poder realizar busquedas en base a ciertos filtros pre-determinados'

**Criterios de aceptación**
  1. Desde una interface podre seleccionar de una lista el filtro que quiero usar y completar el criterio de busqueda correspondiente.
  1. Las filtros disponibles seran: 'ID Issue', 'Titulo', 'Reportó', 'Dueño','estado actual','tipo de issue','proyecto'. Se podrá combinar mas de un filtro para realizar una búsqueda mas precisa.
  1. Se debera mostrar una lista informacion sobre cada issue que devuelva la busqueda y ademas un link a la vista detallada de cada uno.
  1. La lista de resultados debera contener unicamente issues cuyo campo requerido coincida con los criterios de la busqueda.

**Especificación por Ejemplos.**
  1. Completo el filtro de 'estado actual' y debo obtener los issues que empiecen o contengan en su estado los caracteres del filtro.
  1. Completo el filtro de 'estado actual' y 'Dueño', la busqueda solo me deberá retornar los issues con el estado ingresado y el dueño especificado en el filtro