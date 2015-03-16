# Proyecto #

## Contenido ##

---


Contiene las siguientes User Stories
  * Creación de un nuevo proyecto
  * Asignación de un tipo de Issue al proyecto
  * Asignar usuarios a un proyecto
  * Modificar un proyecto

---


## User Stories ##


### _Creación de un nuevo proyecto_ ###

**Descripción**

'Como usuario administrador de un proyecto deseo poder crearlo en la herramienta y realizar la parametrización del mismo'

**Criterios de aceptación**
  1. Mediante una interface podré escribir el nombre del proyecto en caracteres alfanuméricos

### _Asignación de un tipo de Issue al proyecto_ ###
**Descripción**

'Como usuario administrador de un proyecto deseo poder asignar un tipo de Issue determinado a mi proyecto'

**Criterios de aceptación
  1. podré asignar los tipos de issue que requiera la configuración de mi proyecto. [ver especificación de Issue](https://code.google.com/p/ontrack/wiki/Issues?ts=1354473488&updated=Issues#Creación_de_un_tipo_de_Issue)
  1. No será posible asignar un tipo de Issue ya asignado al proyecto previamente. Deberá indicar que ya existe y permitir ingresar uno nuevo**


### _Asignar usuarios a un proyecto_ ###

**Descripción**
'Como usuario administrador de un proyecto deseo poder asignar usuarios que tendrán visibilidad del mismo

**Criterios de aceptación**
  1. Existirá el rol de usuario dueño del proyecto, será quien lo cree
  1. El usuario dueño del proyecto deberá poseer una interface para invitar a los usuarios
  1. Un usuario puede estar en mas de un proyecto
  1. Los usuarios a invitar serán tomados de la lista de contactos de la cuenta de Gmail del usuario que invite

**Especificación por Ejemplos.**
  1. El usuario @Juan.Perez crea un proyecto nuevo 'A', invita a @Luis.Lopez y a @Jorge.Fernandez,
    * Juan Perez - Dueño 'A'
    * Luis.Lopez - Invitado 'A'
    * Jorge.Fernandez - Invitado 'A'
  1. El usuario @Jorge.Fernandez crea un proyecto nuevo 'B', invita a @Juan.Perez y a @Luis.Lopez
    * Juan Perez - Dueño 'A' / Invitado 'B'
    * Luis.Lopez - Invitado 'A' / Invitado 'B'
    * Jorge.Fernandez - Invitado 'A' / Dueño 'B'

### _Modificar un proyecto_ ###
**Descripción**
'Como usuario Dueño de un proyecto deseo poder editar las propiedades de mi proyecto'

**Criterios de aceptación**
  1. Solo el usuario Dueño del proyecto podrá acceder a la edición de un proyecto
  1. Puedo asignar y eliminar usuarios, la eliminación del usuario imposibilitará que reporte y le sean asignados nuevos issues. Si el usuario eliminado tenía issues asignados estos serán reasignados al administrador al momento de desvincular al usuario del proyecto.

**Especificación por Ejemplos.**
  1. como dueño del proyecto, lo edito y elimino usuarios que se han desvinculado del proyecto, el usuario desvinculado ya no tendrá visibilidad del proyecto, ni del Issue, el usuario administrador del proyecto será el nuevo Owner de los issues asignados al usuario eliminado